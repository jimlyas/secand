package developer.jimlyas.secand

import android.content.Context
import at.favre.lib.armadillo.Armadillo
import at.favre.lib.armadillo.ArmadilloSharedPreferences
import com.google.gson.Gson
import developer.jimlyas.secand.utils.getDeviceId

/**
 * @author Jimly A.
 * @since 04-Sep-20.
 */

class PreferenceManager(
    private val context: Context,
    private val prefName: String,
    private val gson: Gson
) {

    private val mPreferences: ArmadilloSharedPreferences by lazy {
        Armadillo.create(context, prefName)
            .encryptionFingerprint(context, getDeviceId(context))
            .enableKitKatSupport(true)
            .build()
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return mPreferences.getBoolean(key, default)
    }

    fun saveBoolean(key: String, value: Boolean) {
        mPreferences.edit().putBoolean(key, value).apply()
    }

    fun getInt(key: String, default: Int): Int {
        return mPreferences.getInt(key, default)
    }

    fun saveInt(key: String, value: Int) {
        mPreferences.edit().putInt(key, value).apply()
    }

    fun getString(key: String, default: String): String {
        return mPreferences.getString(key, default)!!
    }

    fun saveString(key: String, value: String) {
        mPreferences.edit().putString(key, value).apply()
    }

    fun getLong(key: String, default: Long): Long {
        return mPreferences.getLong(key, default)
    }

    fun saveLong(key: String, value: Long) {
        mPreferences.edit().putLong(key, value).apply()
    }

    fun getFloat(key: String, default: Float): Float {
        return mPreferences.getFloat(key, default)
    }

    fun saveFloat(key: String, value: Float) {
        mPreferences.edit().putFloat(key, value).apply()
    }

    fun <T> getObject(key: String, type: Class<T>): T? {
        val json = getString(key, "")

        return if (json.isNotEmpty())
            try {
                gson.fromJson(json, type)
            } catch (exception: Exception) {
                null
            }
        else
            null
    }

    fun saveObject(key: String, value: Any) {
        val json = gson.toJson(value)
        mPreferences.edit().putString(key, json).apply()
    }

    fun clean() {
        mPreferences.edit().clear().apply()
    }
}
