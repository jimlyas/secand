package developer.jimlyas.secand.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

/**
 * @author Jimly A.
 * @since 04-Sep-20.
 */

@SuppressLint("all")
fun getDeviceId(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}