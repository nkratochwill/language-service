package com.nkratochwill.language_service

import com.github.pemistahl.lingua.api.Language
import com.github.pemistahl.lingua.api.LanguageDetector
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder

fun languagecheck(text: String): Json {
    val detector: LanguageDetector = LanguageDetectorBuilder.fromAllBuiltInLanguages().withMapDBCache().build()
    val detectedLanguage: Language = detector.detectLanguageOf(text)
    return Json(true, detectedLanguage.name, detectedLanguage.isoCode, 100)

}