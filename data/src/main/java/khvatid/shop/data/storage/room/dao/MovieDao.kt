package khvatid.shop.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import khvatid.shop.data.storage.room.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert
    fun insert(movie: MovieEntity)

    @Update
    fun update(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE id = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM movies")
    fun getAll(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE status = :status")
    fun getMoviesWithStatus(status: String): Flow<List<MovieEntity>>
}