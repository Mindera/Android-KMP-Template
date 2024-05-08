package com.mindera.kmpexample.currencyexchange.language

import android.content.Context
import com.mindera.spName
import java.util.Locale


object LocaleHelper {

    const val spLanguage = "language"

    fun setLocale(context: Context, language: String): Context {
        setLanguageSharedPreference(context, language)
        return updateResourcesLegacy(context, language)
    }

    private fun setLanguageSharedPreference(context: Context, language: String) {
        val sharedPref = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(spLanguage, language)
            apply()
        }
    }

    fun getLanguageSharedPreference(context: Context): String {
        val sharedPref = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        return if (sharedPref.getString(spLanguage, "en").isNullOrBlank()) {
            "en"
        } else {
            sharedPref.getString(spLanguage, "en")!!
        }
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }
}
