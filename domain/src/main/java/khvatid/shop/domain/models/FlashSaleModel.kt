package khvatid.shop.domain.models


data class FlashSaleModel(
    val category: String,
    val discount: Int,
    val imageUrl: String,
    val name: String,
    val price: Double
)