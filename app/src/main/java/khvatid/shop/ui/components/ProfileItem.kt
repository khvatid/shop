package khvatid.shop.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import khvatid.shop.ui.events.ProfileEvents

interface ProfileItem {
    val label: String

    @get:DrawableRes
    val leadingIcon: Int
    val tailingIcon: @Composable() (() -> Unit)?
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileItem(item: ProfileItem, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable {
            onClick()
        },
        leadingContent = {
            ShopIconButton(icon = item.leadingIcon)
        },
        headlineText = { Text(text = item.label) },
        trailingContent = item.tailingIcon
    )
}