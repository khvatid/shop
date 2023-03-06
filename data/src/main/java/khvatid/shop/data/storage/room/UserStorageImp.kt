package khvatid.shop.data.storage.room

import android.database.sqlite.SQLiteConstraintException
import kotlinx.coroutines.flow.Flow
import khvatid.shop.data.storage.UserStorage
import khvatid.shop.data.storage.room.dao.MovieDao
import khvatid.shop.data.storage.room.dao.SeasonDao
import khvatid.shop.data.storage.room.dao.UserDao
import khvatid.shop.data.storage.room.entity.UserEntity

class UserStorageImp(
    private val userDao: UserDao
) :
    UserStorage {

    /*  fun getMovies(): Flow<List<MovieEntity>> = movieDao.getAll()
      fun getMovies(status: String): Flow<List<MovieEntity>> =
          movieDao.getMoviesWithStatus(status)

      fun getSeasons(movieId: Int): List<SeasonEntity> {
          return seasonDao.getToMovieId(movieId = movieId)
      }

      fun deleteMovie(id: Int) {
          movieDao.delete(id)
      }

      fun saveMovie(movie: MovieEntity) {
          movieDao.insert(movie)
      }

      fun saveMovie(movie: MovieEntity, seasons: List<SeasonEntity>) {
          movieDao.insert(movie)
          seasons.forEach {
              seasonDao.insert(it)
          }
      }

      fun updateMovie(movie: MovieEntity) {
          movieDao.update(movie)
      }*/

    override fun getUser(email: String, password: String): Flow<List<UserEntity>> =
        userDao.getUser(email, password)

    override fun setUser(entity: UserEntity): Throwable? {
        return try {
            userDao.setUser(entity)
            null
        } catch (e: SQLiteConstraintException) {
            e
        }
    }
}