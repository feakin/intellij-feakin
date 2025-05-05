import org.gradle.api.JavaVersion.VERSION_17
import org.jetbrains.changelog.Changelog
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.intellij.platform.gradle.extensions.IntelliJPlatformDependenciesExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = providers.gradleProperty(key)
fun prop(name: String): String =
    extra.properties[name] as? String ?: error("Property `$name` is not defined in gradle.properties")

plugins {
    idea
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij.platform") version "2.5.0"
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "2.2.1"
    // Gradle Qodana Plugin
    id("org.jetbrains.qodana") version "2024.2.3"

    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

kotlin {
    jvmToolchain(17)
}

// Configure project's dependencies
repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
        jetbrainsRuntime()
    }
}

intellijPlatform {
    instrumentCode = true
    buildSearchableOptions = false

    pluginConfiguration {
        id = prop("pluginGroup")
        name = prop("pluginName")
        version = prop("pluginVersion")

        vendor {
            name = "Phodal Huang"
        }

        ideaVersion {
            sinceBuild = prop("pluginSinceBuild")
            untilBuild = prop("pluginUntilBuild")
        }
    }
}

dependencies {
    intellijPlatform {
        intellijIde(prop("ideaVersion"))

        instrumentationTools()
        pluginVerifier()

        testFramework(TestFrameworkType.Platform)
        testFramework(TestFrameworkType.Bundled)
    }
}

// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    version.set(properties("pluginVersion"))
    groups.empty()
    path.set(rootProject.file("CHANGELOG.md").toString())
    repositoryUrl.set(properties("pluginRepositoryUrl"))
    itemPrefix.set("*")
}

// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    version.set(properties("pluginVersion"))
    groups.set(emptyList())
    path.set(rootProject.file("CHANGELOG.md").toString())
    repositoryUrl.set(properties("pluginRepositoryUrl"))
}

idea {
    module {
        generatedSourceDirs.add(file("src/gen"))
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

sourceSets {
    main {
        java.srcDirs("src", "src/gen")
    }
    test {
        resources.srcDirs("src/test/resources")
    }
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDirs("src/main/kotlin")
        }
        test {
            kotlin.srcDirs("src/test/kotlin")
        }
    }
}

intellijPlatformTesting {
    runIde {
        register("runIdeForUiTests") {
            task {
                jvmArgumentProviders += CommandLineArgumentProvider {
                    listOf(
                        "-Drobot-server.port=8082",
                        "-Dide.mac.message.dialogs.as.sheets=false",
                        "-Djb.privacy.policy.text=<!--999.999-->",
                        "-Djb.consents.confirmation.enabled=false",
                    )
                }
            }

            plugins {
                robotServerPlugin()
            }
        }
    }
}

tasks {
    generateLexer {
        sourceFile.set(file("src/main/grammars/FeakinLexer.flex"))
        targetOutputDir.set(file("src/gen/com/feakin/intellij/lexer"))
        purgeOldFiles.set(true)
    }

    generateParser {
        sourceFile.set(file("src/main/grammars/FeakinParser.bnf"))
        targetRootOutputDir.set(file("src/gen"))
        pathToParser.set("com/feakin/intellij/parser/FeakinParser.java")
        pathToPsiRoot.set("com/feakin/intellij/psi")
        purgeOldFiles.set(true)
    }

    withType<KotlinCompile> {
        dependsOn(generateLexer, generateParser)
    }

    patchPluginXml {
        pluginDescription.set(provider { file("src/description.html").readText() })

        changelog {
            version.set(prop("pluginVersion"))
            groups.empty()
            path.set(rootProject.file("CHANGELOG.md").toString())
            repositoryUrl.set(prop("pluginRepositoryUrl"))
        }

        val changelog = project.changelog
        // Get the latest available change notes from the changelog file
        changeNotes.set(properties("pluginVersion").map { pluginVersion ->
            with(changelog) {
                renderItem(
                    (getOrNull(pluginVersion) ?: getUnreleased())
                        .withHeader(false)
                        .withEmptySections(false),

                    Changelog.OutputType.HTML,
                )
            }
        })
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    val newName = "intellij-feakin-" + prop("ideaVersion") + "-" + prop("pluginVersion")
    buildPlugin {
        archiveBaseName.convention(newName)
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        channels.set(properties("pluginVersion").map {
            listOf(it.split('-').getOrElse(1) { "default" }.split('.').first())
        })
    }
}

fun IntelliJPlatformDependenciesExtension.intellijIde(versionWithCode: String) {
    val (type, version) = versionWithCode.toTypeWithVersion()
    create(type, version, useInstaller = false)
}

data class TypeWithVersion(val type: IntelliJPlatformType, val version: String)

fun String.toTypeWithVersion(): TypeWithVersion {
    val (code, version) = split("-", limit = 2)
    return TypeWithVersion(IntelliJPlatformType.fromCode(code), version)
}