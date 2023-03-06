package khvatid.shop.data.storage

import kotlinx.coroutines.flow.Flow
import khvatid.shop.data.storage.room.entity.UserEntity

interface UserStorage {

    fun getUser(email: String, password: String): Flow<List<UserEntity>>
    fun setUser(entity: UserEntity): Throwable?
}