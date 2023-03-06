package khvatid.shop.domain.usecase.database

import khvatid.shop.domain.models.ShortUserModel
import khvatid.shop.domain.repository.UserRepository

class GetShortUserUseCase(private val repository: UserRepository) {
    suspend fun execute (): ShortUserModel{
        return repository.getShortUser()
    }
}