package khvatid.shop.domain.usecase.database

import khvatid.shop.domain.models.ShortUserModel
import khvatid.shop.domain.models.UserModel
import khvatid.shop.domain.repository.UserRepository

class SignupUserUseCase(private val repository: UserRepository) {
    suspend fun execute(userModel: UserModel, onError: (Throwable?) -> Unit) {
        repository.insertUser(userModel = userModel, onError = { error ->
            if (error == null) {
                    repository.insertShortUser(ShortUserModel(userModel.email, userModel.password))
                onError(error)
            } else {
                onError(error)
            }
        })
    }
}