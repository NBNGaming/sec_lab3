package com.example.inventory.data

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object Settings {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var masterKey: MasterKey

    fun init(context: Application) {
        masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        sharedPreferences = EncryptedSharedPreferences.create(
            context,
            SETTINGS_PREFERENCES_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    var defaultSupplierName: String
        get() = sharedPreferences.getString(DEF_NAME_KEY, "")!!
        set(value) {
            sharedPreferences.edit().putString(DEF_NAME_KEY, value).apply()
        }

    var defaultSupplierNumber: String
        get() = sharedPreferences.getString(DEF_NUMBER_KEY, "")!!
        set(value) {
            sharedPreferences.edit().putString(DEF_NUMBER_KEY, value).apply()
        }

    var defaultSupplierEmail: String
        get() = sharedPreferences.getString(DEF_EMAIL_KEY, "")!!
        set(value) {
            sharedPreferences.edit().putString(DEF_EMAIL_KEY, value).apply()
        }

    var enableDefaultFields: Boolean
        get() = sharedPreferences.getBoolean(ENABLE_DEF_FIELDS_KEY, false)
        set(value) {
            sharedPreferences.edit().putBoolean(ENABLE_DEF_FIELDS_KEY, value).apply()
        }

    var hideSensitiveData: Boolean
        get() = sharedPreferences.getBoolean(HIDE_SENSITIVE_DATA_KEY, false)
        set(value) {
            sharedPreferences.edit().putBoolean(HIDE_SENSITIVE_DATA_KEY, value).apply()
        }

    var disableSharing: Boolean
        get() = sharedPreferences.getBoolean(DISABLE_SHARING_KEY, false)
        set(value) {
            sharedPreferences.edit().putBoolean(DISABLE_SHARING_KEY, value).apply()
        }

    private const val SETTINGS_PREFERENCES_NAME = "app_settings"
    private const val DEF_NAME_KEY = "default_supplier_name"
    private const val DEF_NUMBER_KEY = "default_supplier_number"
    private const val DEF_EMAIL_KEY = "default_supplier_email"
    private const val ENABLE_DEF_FIELDS_KEY = "enable_def_fields"
    private const val HIDE_SENSITIVE_DATA_KEY = "hide_sensitive_data"
    private const val DISABLE_SHARING_KEY = "disable_sharing"
}