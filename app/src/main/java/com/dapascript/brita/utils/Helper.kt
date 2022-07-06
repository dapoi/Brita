package com.dapascript.brita.utils

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.dapascript.brita.R

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 06/07/2022 17.53
 */
object Helper {
    fun moveToChrome(context: Context, news: String?) {
        val packageManager = "com.android.chrome"
        val builder = CustomTabsIntent.Builder()
        val params = CustomTabColorSchemeParams.Builder()
        params.setToolbarColor(ContextCompat.getColor(context, R.color.white))
        builder.apply {
            setDefaultColorSchemeParams(params.build())
            setShowTitle(true)
            setShareState(CustomTabsIntent.SHARE_STATE_ON)
            setInstantAppsEnabled(true)
        }

        val customBuilder = builder.build()
        if (context.isPackageInstalled(packageManager)) {
            customBuilder.intent.setPackage(packageManager)
            customBuilder.launchUrl(context, Uri.parse(news))
        } else {
            customBuilder.launchUrl(context, Uri.parse(news))
        }
    }

    private fun Context.isPackageInstalled(packageName: String): Boolean {
        return try {
            packageManager!!.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}