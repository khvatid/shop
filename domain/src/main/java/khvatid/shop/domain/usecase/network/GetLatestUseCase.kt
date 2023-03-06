package khvatid.shop.domain.usecase.network

import khvatid.shop.domain.models.LatestModel
import khvatid.shop.domain.repository.ContentRepository

class GetLatestUseCase(private val repository: ContentRepository) {
    suspend fun execute(): List<LatestModel>{
        return repository.getLatestContent()
    }
}