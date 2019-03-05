package id.aryo.binarapp.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


object PreferenceHelper {

    internal val KEY_EMAIL_TEREGISTER = "rmail"
    internal val KEY_PASS_TEREGISTER = "pass"
    internal val KEY_USER_TEREGISTER = "user"
    internal val KEY_DOMAIN_TEREGISTER = "domain"
    internal val KEY_EMAIL_SEDANG_LOGIN = "Username_logged_in"
    internal val KEY_STATUS_SEDANG_LOGIN = "Status_logged_in"

    fun getSharedPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setRegisteredEmail(context: Context, email: String) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_EMAIL_TEREGISTER, email)
        editor.apply()
    }

    fun getRegisteredEmail(context: Context): String? {
        return getSharedPreference(context).getString(KEY_EMAIL_TEREGISTER, "")
    }

    fun setRegisteredUser(context: Context, user: String) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_USER_TEREGISTER, user)
        editor.apply()
    }

    fun getRegisteredUser(context: Context): String? {
        return getSharedPreference(context).getString(KEY_USER_TEREGISTER, "")
    }

    fun setRegisteredDomain(context: Context, domain: String) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_DOMAIN_TEREGISTER, domain)
        editor.apply()
    }

    fun getRegisteredDomain(context: Context): String? {
        return getSharedPreference(context).getString(KEY_DOMAIN_TEREGISTER, "")
    }

    fun setRegisteredPass(context: Context, password: String) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_PASS_TEREGISTER, password)
        editor.apply()
    }

    fun getRegisteredPass(context: Context): String? {
        return getSharedPreference(context).getString(KEY_PASS_TEREGISTER, "")
    }

    fun setLoggedInUser(context: Context, email: String?) {
        val editor = getSharedPreference(context).edit()
        editor.putString(KEY_EMAIL_SEDANG_LOGIN, email)
        editor.apply()
    }

    fun getLoggedInUser(context: Context): String? {
        return getSharedPreference(context).getString(KEY_EMAIL_SEDANG_LOGIN, "")
    }

    fun setLoggedInStatus(context: Context, status: Boolean) {
        val editor = getSharedPreference(context).edit()
        editor.putBoolean(KEY_STATUS_SEDANG_LOGIN, status)
        editor.apply()
    }

    fun getLoggedInStatus(context: Context): Boolean {
        return getSharedPreference(context).getBoolean(KEY_STATUS_SEDANG_LOGIN, false)
    }

    fun clearLoggedInUser(context: Context) {
        val editor = getSharedPreference(context).edit()
        editor.remove(KEY_EMAIL_SEDANG_LOGIN)
        editor.remove(KEY_STATUS_SEDANG_LOGIN)
        editor.apply()
    }
}

