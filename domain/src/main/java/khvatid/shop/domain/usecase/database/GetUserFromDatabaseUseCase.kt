package khvatid.shop.domain.usecase.database

import khvatid.shop.domain.models.ShortUserModel
import khvatid.shop.domain.models.UserModel
import khvatid.shop.domain.repository.UserRepository

class GetUserFromDatabaseUseCase(private val repository: UserRepository) {
    suspend fun execute(model: ShortUserModel,result: (UserModel?)->Unit): Throwable? {
        return repository.getUser(shortUserModel = model, result = result)
    }
}