package khvatid.shop.data.storage.retrofit.models

import com.google.gson.annotations.SerializedName

data class RemoteLatestModel(
    @SerializedName("latest")
    val latest: List<Latest>
)

data class Latest(
    @SerializedName("category")
    val category: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)