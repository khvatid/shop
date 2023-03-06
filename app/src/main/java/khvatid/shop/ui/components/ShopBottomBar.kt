package khvatid.shop.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ShopBottomBar(
    bottomTabs: List<BottomTab>,
    currentRoute: String?,
    onClick: (String) -> Unit
) {
    if (currentRoute != null) {
        NavigationBar(
           // windowInsets = ,
            modifier = Modifier.clip(
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomEnd = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                )
            )
        ) {
            bottomTabs.forEach { tab ->
                val selected: Boolean = tab.route == (currentRoute)
                NavigationBarItem(
                    modifier = Modifier,
                    selected = selected,
                    onClick = { onClick(tab.route) },
                    icon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = tab.icon),
                            contentDescription = stringResource(id = tab.label)
                        )
                    }
                )
            }
        }

    }
}

@Composable
private fun getStyle(selected: Boolean): TextStyle {
    return when (selected) {
        true -> {
            MaterialTheme.typography.titleSmall
        }

        false -> {
            MaterialTheme.typography.bodySmall
        }
    }
}

interface BottomTab {
    val route: String

    @get:StringRes
    val label: Int

    @get:DrawableRes
    val icon: Int
}



