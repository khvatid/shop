package khvatid.shop.data.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("idKpHd")
    val idKpHd: String?,

    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name ="enName")
    val enName: String?,
    @ColumnInfo(name="alternativeName")
    val alternativeName: String?,

    @ColumnInfo(name="posterPreviewUrl")
    val posterPreviewUrl: String?,
    @ColumnInfo(name="posterUrl")
    val posterUrl: String?,

    @ColumnInfo(name ="description")
    val description: String?,
    @ColumnInfo(name ="shortDescription")
    val shortDescription: String?,

    @ColumnInfo(name="movieLength")
    val movieLength: Int,

    @ColumnInfo(name="ratingImdb")
    val ratingImdb: Double?,
    @ColumnInfo(name = "ratingKp")
    val ratingKp: Double?,

    @ColumnInfo(name = "status")
    val status : String,

    @ColumnInfo(name ="type")
    val type: String,

    @ColumnInfo(name = "currentSeason")
    val currentSeason: Int?,
    @ColumnInfo(name = "currentEpisode")
    val currentEpisode: Int?
)