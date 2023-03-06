package khvatid.shop.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import khvatid.shop.R
import khvatid.shop.domain.models.FlashSaleModel
import khvatid.shop.domain.models.LatestModel


@Composable
fun ContentCard(model: LatestModel , modifier : Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
           // modifier = modifier,
            model = model.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                PrettyText(value = model.category)
                Text(text = model.name)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "$ ${model.price}")
                    Spacer(modifier = Modifier.weight(1f))
                    ShopIconButton(icon = R.drawable.add_ic, size = 24.dp)
                }
            }
        }
    }
}

@Composable
fun FlashSaleCard(model: FlashSaleModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
           // modifier = modifier,
            model = model.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f),
            ) {
                AsyncImage(
                    model = "https://sun9-68.userapi.com/impg/c858336/v858336796/1550a2/u0w7_QtRo1E.jpg?size=1620x2160&quality=96&sign=0c7f4056ca61aebfc33496b4a313890c&type=album",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(0.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        )
                )
                Spacer(modifier = Modifier.weight(1f))
                PrettyText(
                    value = "${model.discount}% off",
                    color = Color(0xFFF93A3A),
                    paddingValues = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                PrettyText(value = model.category)

                Text(text = model.name)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "$ ${model.price}")
                    Spacer(modifier = Modifier.weight(1f))
                    ShopIconButton(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        icon = R.drawable.heart_ic
                    )
                    ShopIconButton(icon = R.drawable.add_ic, size = 32.dp)
                }
            }
        }
    }
}

@Composable
private fun PrettyText(
    value: String,
    color: Color = Color(0xD8C4C4C4),
    paddingValues: PaddingValues = PaddingValues(
        vertical = 2.dp,
        horizontal = 8.dp
    )
) {
    Text(
        text = value,
        modifier = Modifier
            .background(
                color = color,
                shape = MaterialTheme.shapes.extraLarge
            )
            .padding(paddingValues),
        style = MaterialTheme.typography.labelMedium
    )
}