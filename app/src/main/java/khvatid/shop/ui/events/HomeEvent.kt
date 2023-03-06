package khvatid.shop.ui.events

import khvatid.shop.ui.components.CategoryItem

sealed class HomeEvent : Event {
    object WindowLaunch : HomeEvent()
    data class CategoryClick(val categoryItem: CategoryItem) : HomeEvent()
    object ItemClick : HomeEvent()
}
