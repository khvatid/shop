package khvatid.shop.data.repository

import khvatid.shop.data.storage.RemoteStorage
import khvatid.shop.data.storage.retrofit.models.FlashSale
import khvatid.shop.data.storage.retrofit.models.Latest
import khvatid.shop.domain.models.FlashSaleModel
import khvatid.shop.domain.models.LatestModel
import khvatid.shop.domain.repository.ContentRepository

class ContentRepositoryImp(private val storage: RemoteStorage) : ContentRepository {
    override suspend fun getLatestContent(): List<LatestModel> {
        val model = storage.getLatest()
        return model?.latest?.map { model ->
            model.toLatestModel()
        } ?: emptyList()
    }

    override suspend fun getFlashSaleContent(): List<FlashSaleModel> {
        val model = storage.getFlashSale()
        return model?.flashSale?.map { model ->
            model.toFlashSaleModel()
        } ?: emptyList()
    }


    private fun Latest.toLatestModel(): LatestModel {
        return LatestModel(
            category = this.category,
            imageUrl = this.imageUrl,
            name = this.name,
            price = this.price
        )
    }

    private fun FlashSale.toFlashSaleModel(): FlashSaleModel {
        return FlashSaleModel(
            category = this.category,
            discount = this.discount,
            imageUrl = this.imageUrl,
            name = this.name,
            price = this.price
        )
    }
}