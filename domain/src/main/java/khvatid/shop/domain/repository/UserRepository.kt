package khvatid.shop.domain.repository

import khvatid.shop.domain.models.ShortUserModel
import khvatid.shop.domain.models.UserModel

interface UserRepository {

    suspend fun getUser(shortUserModel: ShortUserModel, result: (UserModel?) -> Unit): Throwable?
    suspend fun insertUser(userModel: UserModel, onError: (Throwable?) -> Unit)

    fun insertShortUser(shortUserModel: ShortUserModel): Throwable?
    fun getShortUser():ShortUserModel
}