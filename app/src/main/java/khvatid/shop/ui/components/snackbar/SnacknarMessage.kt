package khvatid.shop.ui.components.snackbar

import android.content.res.Resources
import androidx.annotation.StringRes
import khvatid.shop.R.string as AppText

sealed class SnackbarMessage{
    class StringSnackbar(val massage:String):SnackbarMessage()
    class ResourceSnackbar(@StringRes val message: Int):SnackbarMessage()

    companion object{
        fun SnackbarMessage.toMessage(resources: Resources): String {
            return when (this) {
                is StringSnackbar -> this.massage
                is ResourceSnackbar -> resources.getString(this.message)
            }
        }

        fun Throwable.snackbarMessage(): SnackbarMessage {
            val message = this.message.orEmpty()
            return if (message.isNotBlank()) StringSnackbar(message)
            else ResourceSnackbar(AppText.app_name)
        }
    }
}