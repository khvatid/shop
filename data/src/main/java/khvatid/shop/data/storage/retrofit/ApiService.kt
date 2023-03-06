package khvatid.shop.data.storage.retrofit

import khvatid.shop.data.storage.retrofit.models.RemoteFlashSaleModel
import khvatid.shop.data.storage.retrofit.models.RemoteLatestModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import khvatid.shop.data.storage.retrofit.models.RemoteMovieModel
import khvatid.shop.data.storage.retrofit.models.RemoteSeasonModel

interface ApiService {

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): Response<RemoteLatestModel>

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSale(): Response<RemoteFlashSaleModel>

}