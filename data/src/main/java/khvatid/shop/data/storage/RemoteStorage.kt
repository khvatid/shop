package khvatid.shop.data.storage

import khvatid.shop.data.storage.retrofit.models.RemoteFlashSaleModel
import khvatid.shop.data.storage.retrofit.models.RemoteLatestModel
import khvatid.shop.data.storage.retrofit.models.RemoteMovieModel
import khvatid.shop.data.storage.retrofit.models.RemoteSeasonModel

interface RemoteStorage {

    suspend fun getLatest(): RemoteLatestModel?


    suspend fun getFlashSale(): RemoteFlashSaleModel?

}