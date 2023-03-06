package khvatid.shop.data.storage.pref

import android.content.Context
import android.content.SharedPreferences
import khvatid.shop.data.storage.ShortUserStorage
import khvatid.shop.data.storage.model.ShortUserStorageModel


class ShortUserStoragePref(context: Context) : ShortUserStorage {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun get(): ShortUserStorageModel = ShortUserStorageModel(
        email = sharedPreferences.getString(KEY_EMAIL, null),
        password = sharedPreferences.getString(KEY_PASSWORD, null)
    )


    override fun set(shortUserStorageModel: ShortUserStorageModel): Throwable? {
        return try {
            sharedPreferences.edit()
                .putString(KEY_EMAIL, shortUserStorageModel.email)
                .putString(KEY_PASSWORD, shortUserStorageModel.password)
                .apply()
            null
        } catch (e: Exception) {
            e
        }
    }


    companion object {
        private const val SHARED_PREF_NAME = "khvatid_shop_pref"
        private const val KEY_PASSWORD = "${SHARED_PREF_NAME}_key_password"
        private const val KEY_EMAIL = "${SHARED_PREF_NAME}_key_email"
    }
}