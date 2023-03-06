package khvatid.shop.data.storage.retrofit.models


import com.google.gson.annotations.SerializedName

data class RemoteFlashSaleModel(
    @SerializedName("flash_sale")
    val flashSale: List<FlashSale>
)

data class FlashSale(
    @SerializedName("category")
    val category: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)