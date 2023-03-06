package khvatid.shop.data.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import khvatid.shop.data.storage.room.entity.MovieEntity

@Entity(
    tableName = "seasons",
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)
data class SeasonEntity(
    @ColumnInfo(name = "movieId", index = true)
    val movieId: Int,
    @ColumnInfo(name = "number")
    val number: Int,
    @ColumnInfo(name = "episodesCount")
    val episodesCount: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
