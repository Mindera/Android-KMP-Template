package com.mindera.kmpexample.launches.language

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import com.mindera.spName
import java.util.Locale


object LocaleHelper {

    const val spLanguage = "language"

    fun setLocale(context: Context, language: String): Context? {
        setLanguageSharedPreference(context, language)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language)
        }
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

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}
