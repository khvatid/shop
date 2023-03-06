package khvatid.shop.data.storage.retrofit.models


import com.google.gson.annotations.SerializedName

data class RemoteMovieModel(
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
        @SerializedName("alternativeName")
        val alternativeName: String?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("enName")
        val enName: String?,
        @SerializedName("externalId")
        val externalId: ExternalId,
        @SerializedName("id")
        val id: Int,
        @SerializedName("logo")
        val logo: Logo,
        @SerializedName("movieLength")
        val movieLength: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("names")
        val names: List<Name>,
        @SerializedName("poster")
        val poster: Poster?,
        @SerializedName("rating")
        val rating: Rating,
        @SerializedName("releaseYears")
        val releaseYears: List<ReleaseYear>,
        @SerializedName("shortDescription")
        val shortDescription: String?,
        @SerializedName("type")
        val type: String,
        @SerializedName("votes")
        val votes: Votes?,
        @SerializedName("watchability")
        val watchability: Watchability,
        @SerializedName("year")
        val year: String
    ) {
        data class ExternalId(
            @SerializedName("_id")
            val id: String,
            @SerializedName("imdb")
            val imdb: String?,
            @SerializedName("kpHD")
            val kpHD: String?,
            @SerializedName("tmdb")
            val tmdb: Int?
        )

        data class Logo(
            @SerializedName("_id")
            val id: String,
            @SerializedName("url")
            val url: String
        )

        data class Name(
            @SerializedName("_id")
            val id: String,
            @SerializedName("name")
            val name: String
        )

        data class Poster(
            @SerializedName("_id")
            val id: String,
            @SerializedName("previewUrl")
            val previewUrl: String,
            @SerializedName("url")
            val url: String
        )

        data class Rating(
            @SerializedName("await")
            val await: Double?,
            @SerializedName("filmCritics")
            val filmCritics: Double?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("imdb")
            val imdb: Double?,
            @SerializedName("kp")
            val kp: Double?,
            @SerializedName("russianFilmCritics")
            val russianFilmCritics: Double?
        )

        data class ReleaseYear(
            @SerializedName("end")
            val end: String?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("start")
            val start: String?
        )

        data class Votes(
            @SerializedName("await")
            val await: Int?,
            @SerializedName("filmCritics")
            val filmCritics: Int?,
            @SerializedName("_id")
            val id: String?,
            @SerializedName("imdb")
            val imdb: Int?,
            @SerializedName("kp")
            val kp: Int?,
            @SerializedName("russianFilmCritics")
            val russianFilmCritics: Int?
        )

        data class Watchability(
            @SerializedName("_id")
            val id: String,
            @SerializedName("items")
            val items: List<Item>
        ) {
            data class Item(
                @SerializedName("_id")
                val id: String,
                @SerializedName("logo")
                val logo: Logo,
                @SerializedName("name")
                val name: String?,
                @SerializedName("url")
                val url: String?
            ) {
                data class Logo(
                    @SerializedName("_id")
                    val id: String?,
                    @SerializedName("url")
                    val url: String?
                )
            }
        }
    }
}