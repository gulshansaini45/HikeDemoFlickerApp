package `in`.gulshan.data.local.sharedpref


import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleDemoSharedPref @Inject constructor(private val preference: SharedPreferences) {

    fun getBoolean(key: String): Boolean {
        return preference.getBoolean(key, false)
    }

    fun putBoolean(key: String, value: Boolean) {
        preference.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String): String {
        return preference.getString(key, "") ?: ""
    }

    fun putString(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }

    fun getFloat(key: String): Float {
        return preference.getFloat(key, -1f)
    }

    fun putFloat(key: String, value: Float) {
        preference.edit().putFloat(key, value).apply()
    }

    fun getInt(key: String): Int {
        return preference.getInt(key, -1)
    }

    fun putInt(key: String, value: Int) {
        preference.edit().putInt(key, value).apply()
    }

    fun getLong(key: String): Long {
        return preference.getLong(key, -1L)
    }

    fun putLong(key: String, value: Long) {
        preference.edit().putLong(key, value).apply()
    }

    fun clear() = preference.edit().clear().apply()

    companion object {
        const val PREF_LOGGED_IN = "user_logged_in"
        const val PREF_EMAIL = "user_email"
        const val PREF_PHONE = "user_phone"
        const val PREF_NAME = "user_name"
        const val PREF_TOKEN = "user_token"
        const val PREF_USER_ID = "user_id"
        const val PREF_ADDRESS= "user_address"

    }
}