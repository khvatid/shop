package khvatid.shop.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

interface CategoryItem {
    @get:DrawableRes
    val icon: Int
    val label: String
}


@Composable
fun CategoryItem(modifier: Modifier, categoryItem: CategoryItem, onClick: () -> Unit) {
    Box(modifier = modifier, contentAlignment = Alignment.Center,){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .clickable { onClick() }) {
            ShopIconButton(icon = categoryItem.icon)
            Text(
                text = categoryItem.label,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}