package com.mindera.kmpexample.currencyexchange.language

import android.app.Application
import android.content.Context

open class LanguageApp : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(base,
            LocaleHelper.getLanguageSharedPreference(base)
        ))
    }
}
