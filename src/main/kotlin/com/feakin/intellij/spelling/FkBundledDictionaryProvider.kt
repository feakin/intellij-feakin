package com.feakin.intellij.spelling

import com.intellij.spellchecker.BundledDictionaryProvider

class FkBundledDictionaryProvider : BundledDictionaryProvider {
    override fun getBundledDictionaries(): Array<String> = arrayOf("/dictionary/fklang.dic")
}