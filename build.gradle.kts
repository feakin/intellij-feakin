import org.jetbrains.changelog.markdownToHTML
import org.gradle.api.JavaVersion.VERSION_17
import org.jetbrains.changelog.Changelog
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    idea
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij") version "1.15.0"
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "2.1.2"
    // Gradle Qodana Plugin
    id("org.jetbrains.qodana") version "0.1.13"

    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

// Configure project's dependencies
repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin - read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

configure<JavaPluginExtension> {
    sourceCompatibility = VERSION_17
    targetCompatibility = VERSION_17
}

// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    version.set(properties("pluginVersion"))
    groups.set(emptyList())
    path.set(rootProject.file("CHANGELOG.md").toString())
    repositoryUrl.set(properties("pluginRepositoryUrl"))
}

// Configure Gradle Qodana Plugin - read more: https://github.com/JetBrains/gradle-qodana-plugin
qodana {
    cachePath.set(projectDir.resolve(".qodana").canonicalPath)
    reportPath.set(projectDir.resolve("build/reports/inspections").canonicalPath)
    saveReport.set(true)
    showReport.set(System.getenv("QODANA_SHOW_REPORT")?.toBoolean() ?: false)
}

idea {
    module {
        generatedSourceDirs.add(file("src/gen"))
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

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = VERSION_17.toString()
            languageVersion = "1.8"
            // see https://plugins.jetbrains.com/docs/intellij/using-kotlin.html#kotlin-standard-library
            apiVersion = "1.7"
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }

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
        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))
        untilBuild.set(properties("pluginUntilBuild"))

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription.set(
            projectDir.resolve("README.md").readText().lines().run {
                val start = "<!-- Plugin description -->"
                val end = "<!-- Plugin description end -->"

                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end))
            }.joinToString("\n").run { markdownToHTML(this) }
        )

        // Get the latest available change notes from the changelog file
        changeNotes.set(provider {
            changelog.renderItem(changelog.run {
                getOrNull(properties("pluginVersion")) ?: getLatest()
            }, Changelog.OutputType.HTML)
        })
    }

    // Configure UI tests plugin
    // Read more: https://github.com/JetBrains/intellij-ui-test-robot
    runIdeForUiTests {
        systemProperty("robot-server.port", "8082")
        systemProperty("ide.mac.message.dialogs.as.sheets", "false")
        systemProperty("jb.privacy.policy.text", "<!--999.999-->")
        systemProperty("jb.consents.confirmation.enabled", "false")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        // pluginVersion is based on the SemVer (https://semver.org) and supports pre-release labels, like 2.1.7-alpha.3
        // Specify pre-release label to publish the plugin in a custom Release Channel automatically. Read more:
        // https://plugins.jetbrains.com/docs/intellij/deployment.html#specifying-a-release-channel
        val pluginVersion = properties("pluginVersion")
        val channel: String = pluginVersion.split('-').getOrElse(1) { "default" }.split('.')[0]
        val channelProvider: Provider<List<String>> = providers.provider { listOf(channel) }

        channels.set(channelProvider)
    }
}
