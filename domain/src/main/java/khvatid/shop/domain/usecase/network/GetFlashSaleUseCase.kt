package khvatid.shop.domain.usecase.network

import khvatid.shop.domain.models.FlashSaleModel
import khvatid.shop.domain.repository.ContentRepository

class GetFlashSaleUseCase(private val repository: ContentRepository) {
    suspend fun execute(): List<FlashSaleModel> {
        return repository.getFlashSaleContent()
    }
}