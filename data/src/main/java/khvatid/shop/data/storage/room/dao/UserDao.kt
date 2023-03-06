package khvatid.shop.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import khvatid.shop.data.storage.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun setUser(entity: UserEntity)

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): Flow<List<UserEntity>>

}