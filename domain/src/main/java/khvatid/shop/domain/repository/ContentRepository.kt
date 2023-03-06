package khvatid.shop.domain.repository

import khvatid.shop.domain.models.FlashSaleModel
import khvatid.shop.domain.models.LatestModel

interface ContentRepository {

    suspend fun getLatestContent(): List<LatestModel>
    suspend fun getFlashSaleContent(): List<FlashSaleModel>

}