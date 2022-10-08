# intellij-feakin

![Build](https://github.com/feakin/intellij-feakin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/20026-feakin.svg)](https://plugins.jetbrains.com/plugin/20026-feakin)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/20026-feakin.svg)](https://plugins.jetbrains.com/plugin/20026-feakin)

<!-- Plugin description -->
Feakin is an architecture design and visualization tool, based on Diagram-as-code. It can highlight code, generate structure, and others.

Fklang 是一个架构设计 DSL，通过显性化软件架构设计，以确保软件系统描述与实现的一致性。并在工作流中，内嵌对于 AI 代码生成软件的支持，以构筑完整的开发者体验。

<!-- Plugin description end -->

Features:

- [x] Lexer and Parser Definition
- [x] Highlight code
- [x] Line Marker Provider
- [x] Completion Contributor
- [x] Structure View
- [x] Folding
- [x] Brace Matching
- [x] Block Selection
- [x] Code Formatting
- [ ] Code Style Settings
- [x] Reference Contributor
  - [x] ContextName
  - [ ] Others
- [x] Commenter
- [ ] Rename Refactoring
- [ ] Quick Fix

## Installation

- Using IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "
  intellij-feakin"</kbd> >
  <kbd>Install Plugin</kbd>

- Manually:

  Download the [latest release](https://github.com/feakin/intellij-feakin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

## License

inspired and based on [https://github.com/intellij-rust/intellij-rust](https://github.com/intellij-rust/intellij-rust)

- MIT license. Copyright (c) 2015 Aleksey Kladov, Evgeny Kurbatsky, Alexey Kudinkin and contributors
- MIT license. Copyright (c) 2016 JetBrains

@2022 This code is distributed under the MPL license. See `LICENSE` in this directory.
