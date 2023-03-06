package khvatid.shop.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import khvatid.shop.R
import khvatid.shop.domain.models.FlashSaleModel
import khvatid.shop.domain.models.LatestModel
import khvatid.shop.ui.components.CategoryItem
import khvatid.shop.ui.components.ContentCard
import khvatid.shop.ui.components.FlashSaleCard
import khvatid.shop.ui.components.LoadingShimmerEffect
import khvatid.shop.ui.components.ShopTextField
import khvatid.shop.ui.events.HomeEvent

data class HomeUiState(
    val categories: List<CategoryItem> = CategoryItemImp.getList(),
    val flashSaleList: List<FlashSaleModel> = emptyList(),
    val latestList: List<LatestModel> = emptyList(),
    val showShimmer: Boolean = true
)

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    HomeUi(state = state, event = viewModel::obtainEvent)


}

@Composable
fun HomeUi(state: HomeUiState, event: (HomeEvent) -> Unit) {
    LaunchedEffect(state) {
        event(HomeEvent.WindowLaunch)
    }
    LazyColumn(modifier = Modifier,
        contentPadding = PaddingValues(bottom = 128.dp, start = 8.dp, end = 8.dp),
        content = {
            item {
                Box(modifier = Modifier.padding(vertical = 32.dp, horizontal = 64.dp)) {
                    ShopTextField(
                        value = "",
                        placeholder = "What are you looking for?",
                        onValueChange = {},
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.search_ic),
                                modifier = Modifier.size(20.dp),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
            item {
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    content = {
                        val modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                        if (state.categories.isNotEmpty()) {
                            items(state.categories) { item ->
                                CategoryItem(modifier = modifier, categoryItem = item, onClick = {})
                            }
                        } else {
                            items(5) {
                                LoadingShimmerEffect(modifier = modifier.clip(CircleShape))
                            }
                        }
                    })
            }


            item {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    val itemModifier: Modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(height = 221.dp, width = 174.dp)
                        .clip(MaterialTheme.shapes.medium)

                    if (state.flashSaleList.isNotEmpty()) {
                        Category<LatestModel>(title = {
                            PrettyTitle(text = "Latest")
                        },
                            tailingIcon = { },
                            models = state.latestList,
                            item = { model ->
                                ContentCard(
                                    model = model,
                                    modifier = itemModifier
                                )
                            })
                    } else {
                        Category(count = 3, item = {
                            LoadingShimmerEffect(
                                modifier = itemModifier
                            )
                        })
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    val itemModifier: Modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(height = 310.dp, width = 210.dp)
                        .clip(MaterialTheme.shapes.medium)

                    if (state.flashSaleList.isNotEmpty()) {
                        Category<FlashSaleModel>(title = {
                            PrettyTitle(text = "Flash sale")
                        },
                            tailingIcon = { },
                            models = state.flashSaleList,
                            item = { model ->
                                FlashSaleCard(
                                    model = model,
                                    modifier = itemModifier
                                )
                            })
                    } else {
                        Category(count = 3, item = {
                            LoadingShimmerEffect(
                                modifier = itemModifier
                            )
                        })
                    }
                }
            }

        })
}

@Composable
private fun PrettyTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge
    )
}


@Composable
private fun <T : Any> Category(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit),
    tailingIcon: @Composable (() -> Unit),
    models: List<T>,
    item: @Composable ((T) -> Unit)
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.8f), contentAlignment = Alignment.CenterStart) {
                title()
            }
            Box(modifier = Modifier.weight(0.2f), contentAlignment = Alignment.CenterEnd) {
                tailingIcon()
            }
        }
        LazyRow(modifier = Modifier, content = {
            items(models) {
                item(it)
            }
        })
    }
}

@Composable
private fun Category(
    modifier: Modifier = Modifier, label: @Composable() (() -> Unit) = {
        LoadingShimmerEffect(
            modifier = Modifier
                .size(width = 256.dp, height = 28.dp)
                .clip(
                    MaterialTheme.shapes.extraLarge
                )
        )
    }, tailingIcon: @Composable() (() -> Unit) = {
        LoadingShimmerEffect(
            modifier = Modifier
                .size(width = 128.dp, height = 24.dp)
                .clip(
                    MaterialTheme.shapes.extraLarge
                )
        )
    }, count: Int, item: @Composable() (() -> Unit)
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.8f), contentAlignment = Alignment.CenterStart) {
                label()
            }
            Box(modifier = Modifier.weight(0.2f), contentAlignment = Alignment.CenterEnd) {
                tailingIcon()
            }
        }
        LazyRow(modifier = Modifier, content = {
            items(count) {
                item()
            }
        })
    }
}


sealed class CategoryItemImp(
    override val label: String, override val icon: Int
) : CategoryItem {
    object Phones : CategoryItemImp(
        label = "Phones", icon = R.drawable.phone_ic
    )

    object Headphones : CategoryItemImp(
        label = "Headphones", icon = R.drawable.headphones_ic
    )

    object Games : CategoryItemImp(
        label = "Games", icon = R.drawable.games_ic
    )

    object Cars : CategoryItemImp(
        label = "Cars", icon = R.drawable.car_ic
    )

    object Furniture : CategoryItemImp(
        label = "Furniture", icon = R.drawable.furniture_ic
    )

    object Kids : CategoryItemImp(
        label = "Kids", icon = R.drawable.bot_ic
    )

    companion object {
        fun getList(): List<CategoryItem> {
            return listOf(Phones, Headphones, Games, Cars, Furniture, Kids)
        }
    }
}