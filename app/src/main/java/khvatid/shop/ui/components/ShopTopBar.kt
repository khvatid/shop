package khvatid.shop.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import khvatid.shop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontStyle = MaterialTheme.typography.displaySmall.fontStyle,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                ) {
                    append("Trade by ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append("bata")
                    }
                }
            })
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.padding(horizontal = 8.dp),
                painter = painterResource(id = R.drawable.menu_ic),
                contentDescription = null
            )
        },
        actions = {
            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "https://sun9-68.userapi.com/impg/c858336/v858336796/1550a2/u0w7_QtRo1E.jpg?size=1620x2160&quality=96&sign=0c7f4056ca61aebfc33496b4a313890c&type=album",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        )

                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "location",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down_ic),
                        contentDescription = null
                    )
                }
            }
        }
    )
}