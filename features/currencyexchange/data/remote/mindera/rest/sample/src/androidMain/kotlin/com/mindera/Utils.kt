package com.mindera

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import java.io.File
import kotlin.math.roundToInt

const val spName = "currencyExchange"
const val spDark = "dark"
@Suppress("MagicNumber")
fun getAppSize(context: Context): String {
    return try {
        val pm: PackageManager = context.packageManager
        val applicationInfo = pm.getApplicationInfo(context.packageName, 0)
        val file = File(applicationInfo.publicSourceDir)
        val size = (file.length() / 1e+6).roundToInt()
        "$size MB"
    } catch (e: PackageManager.NameNotFoundException) {
        Log.e("getAppSize Error", ">> $e")
        "0 MB"
    }
}

fun getAppVersion(context: Context): String {
    return try {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val version = pInfo.versionName
        "v$version"
    } catch (e: PackageManager.NameNotFoundException) {
        Log.e("getAppVersion Error", ">> $e")
        "v1.0.0"
    }
}

fun isDarkMode(context: Context): Boolean {
    val sharedPref = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    return sharedPref.getBoolean(spDark, false)
}

fun darkMode(context: Context) {
    val sharedPref = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    val currentNightMode =  sharedPref.getBoolean(spDark, false)
    with(sharedPref.edit()) {
        when (currentNightMode) {
            false -> {
                putBoolean(spDark, true)
            }
            true -> {
                putBoolean(spDark, false)
            }
        }

        apply()
    }

}
