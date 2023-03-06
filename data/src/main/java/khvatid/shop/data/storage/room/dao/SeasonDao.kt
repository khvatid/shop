package khvatid.shop.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import khvatid.shop.data.storage.room.entity.SeasonEntity

@Dao
interface SeasonDao {

    @Insert
    fun insert(season: SeasonEntity)

    @Query("SELECT * FROM seasons WHERE movieId = :movieId")
    fun getToMovieId(movieId: Int):List<SeasonEntity>

}