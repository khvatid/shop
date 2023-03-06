package khvatid.shop.ui.events

import android.net.Uri
import khvatid.shop.ui.components.ProfileItem

sealed class ProfileEvents() : Event {
    data class MenuClick(
        val item: ProfileItem,
        /**
         * @param result use for navigate
        */
        val result: (String) -> Unit
    ) : ProfileEvents()
    data class ChangePhoto(val uri: Uri?) : ProfileEvents()
}


