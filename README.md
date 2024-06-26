# Feakin - Architecture DSL

![Build](https://github.com/feakin/intellij-feakin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/20026-feakin.svg)](https://plugins.jetbrains.com/plugin/20026-feakin)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/20026-feakin.svg)](https://plugins.jetbrains.com/plugin/20026-feakin)

<!-- Plugin description -->
Feakin is an architecture DSL for design, governance and visualization, based on Diagram-as-code. It can highlight code, generate structure, and others.

Fklang 是一个基于软件开发工业化思想设计的架构设计 DSL。通过显性化软件架构设计，以确保软件系统描述与实现的一致性。并在工作流中，内嵌对于 AI 代码生成软件的支持，以构筑完整的开发者体验。

<!-- Plugin description end -->

Features:

- [x] Lexer and Parser Definition
- [x] Highlight code
- [x] Line Marker Provider
  - [x] LayeredGuarding
  - [ ] Endpoint Run
  - [ ] Endpoint Test
  - [x] GenImpl
- [x] Completion Contributor
- [x] Structure View
- [x] Folding
- [x] Brace Matching
- [x] Block Selection
  - [x] Block
  - [x] Flow Entry
- [x] Code Formatting
- [ ] Code Style Settings
- [x] Reference Contributor
  - [x] ContextName
  - [x] File Importer
  - [ ] Others
- [x] Commenter
- [ ] Rename Refactoring
- [ ] Quick Fix

## Installation

- Using IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "Feakin"</kbd> >
  <kbd>Install Plugin</kbd>

## License

inspired and based on [https://github.com/intellij-rust/intellij-rust](https://github.com/intellij-rust/intellij-rust)

- MIT license. Copyright (c) 2015 Aleksey Kladov, Evgeny Kurbatsky, Alexey Kudinkin and contributors
- MIT license. Copyright (c) 2016 JetBrains

@2022 This code is distributed under the MPL license. See `LICENSE` in this directory.
