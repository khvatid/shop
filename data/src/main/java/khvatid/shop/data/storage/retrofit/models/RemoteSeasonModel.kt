package khvatid.shop.data.storage.retrofit.models


import com.google.gson.annotations.SerializedName

data class RemoteSeasonModel(
    @SerializedName("docs")
    val docs: List<Doc>,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: Int
) {
    data class Doc(
        @SerializedName("episodes")
        val episodes: List<Episode>,
        @SerializedName("episodesCount")
        val episodesCount: Int,
        @SerializedName("movieId")
        val movieId: Int,
        @SerializedName("number")
        val number: Int
    ) {
        data class Episode(
            @SerializedName("date")
            val date: String?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("enName")
            val enName: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("number")
            val number: Int
        )
    }
}