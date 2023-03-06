package khvatid.shop.data.repository

import khvatid.shop.data.storage.ShortUserStorage
import khvatid.shop.data.storage.UserStorage
import khvatid.shop.data.storage.model.ShortUserStorageModel
import khvatid.shop.data.storage.room.entity.UserEntity
import khvatid.shop.domain.models.ShortUserModel
import khvatid.shop.domain.models.UserModel
import khvatid.shop.domain.repository.UserRepository
import kotlinx.coroutines.flow.collect

class UserRepositoryImp(
    private val userStorage: UserStorage, private val shortUserStorage: ShortUserStorage
) : UserRepository {
    override suspend fun getUser(
        shortUserModel: ShortUserModel, result: (UserModel?) -> Unit
    ): Throwable? {
        return userStorage.getUser(email = shortUserModel.email, password = shortUserModel.password)
            .runCatching {
                collect {
                    if (it.isEmpty()) result(null)
                    else {
                        result(it.first().toModel())
                    }
                }
            }.exceptionOrNull()
    }

    override suspend fun insertUser(userModel: UserModel, onError: (Throwable?) -> Unit) {
        onError(userStorage.setUser(userModel.toEntity()))
    }

    override fun insertShortUser(
        shortUserModel: ShortUserModel
    ): Throwable? {
        return shortUserStorage.set(shortUserModel.toStorageModel())
    }

    override fun getShortUser(): ShortUserModel {
        val model = shortUserStorage.get()
        return if (model.email == null || model.password == null) ShortUserModel(
            "default@gmail.com",
            "qwerty123"
        )
        else ShortUserModel(email = model.email, password = model.password)
    }

    private fun UserEntity.toModel(): UserModel {
        return UserModel(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            password = this.password,
            balance = this.balance
        )
    }

    private fun UserModel.toEntity(): UserEntity {
        return UserEntity(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            password = this.password,
            balance = this.balance
        )
    }

    private fun ShortUserModel.toStorageModel(): ShortUserStorageModel {
        return ShortUserStorageModel(
            email = this.email, password = this.password
        )
    }

}