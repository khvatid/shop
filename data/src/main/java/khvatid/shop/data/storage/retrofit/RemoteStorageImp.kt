package khvatid.shop.data.storage.retrofit

import retrofit2.Response
import khvatid.shop.data.storage.RemoteStorage
import khvatid.shop.data.storage.retrofit.models.RemoteFlashSaleModel
import khvatid.shop.data.storage.retrofit.models.RemoteLatestModel
import khvatid.shop.data.storage.retrofit.models.RemoteMovieModel
import khvatid.shop.data.storage.retrofit.models.RemoteSeasonModel

class RemoteStorageImp(private val service: ApiService) : RemoteStorage {
    /* override suspend fun getMovie(
        search: String,
        field: String,
        isStrict: Boolean,
        page: Int,
        limit: Int,
        onResult: (code: Int, model: RemoteMovieModel?) -> Unit
    ) {

        val response: Response<RemoteMovieModel> = service.execute().getMovies(
            token = TOKEN,
            search = search,
            field = field,
            isStrict = isStrict,
            page = page,
            limit = limit
        )
        if (response.isSuccessful) {
            onResult(response.code(), response.body())
        } else {
            onResult(500, null)
        }
    }

    override suspend fun getSeasons(
        search: String,
        field: String,
        page: Int,
        limit: Int,
        onResult: (code: Int, model: RemoteSeasonModel?) -> Unit
    ) {
        val response: Response<RemoteSeasonModel> = service.execute().getSeasons(
            token = TOKEN, search = search, field = field, page = page, limit = limit
        )
        if (response.isSuccessful && response.code() == 200) {
            var seasonsModel: RemoteSeasonModel = response.body()!!
            if (page < response.body()?.pages!!) {
                seasonsModel = seasonsModel.copy(
                    docs = seasonsModel.docs + getSeason(
                        search = search, field = field, page = page + 1, limit = limit
                    )
                )
            }

            onResult(response.code(), response.body())
        } else {
            onResult(500, null)
        }
    }

    private suspend fun getSeason(
        search: String,
        field: String,
        page: Int,
        limit: Int,
    ): List<RemoteSeasonModel.Doc> {
        val response: Response<RemoteSeasonModel> = service.execute().getSeasons(
            token = TOKEN, search = search, field = field, page = page, limit = limit
        )
        if (response.isSuccessful) {
            val doc: MutableList<RemoteSeasonModel.Doc> = mutableListOf()
            doc.addAll(response.body()?.docs ?: emptyList())
            if (page < response.body()?.pages!!) {
                doc.addAll(
                    getSeason(
                        search = search, field = field, page = page + 1, limit = limit
                    )
                )
            } else {
                return doc
            }
        } else {
            return emptyList()
        }
        return emptyList()
    }

    companion object {
        private const val TOKEN = "ZRHQQQE-MRC4FXZ-Q8SD9KF-97CB2DY"
    }
*/
   /* override suspend fun getLatest(onResult: (code: Int, model: RemoteLatestModel?) -> Unit) {
        val response: Response<RemoteLatestModel> = service.execute().getLatest()
        if (response.isSuccessful) {
            onResult(response.code(), response.body())
        } else {
            onResult(500, null)
        }
    }

    override suspend fun getFlashSale(onResult: (code: Int, model: RemoteFlashSaleModel?) -> Unit) {
        val response: Response<RemoteFlashSaleModel> = service.execute().getFlashSale()
        if (response.isSuccessful) {
            onResult(response.code(), response.body())
        } else {
            onResult(500, null)
        }
    }*/

    override suspend fun getLatest(): RemoteLatestModel? {
        val response: Response<RemoteLatestModel> = service.getLatest()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    override suspend fun getFlashSale(): RemoteFlashSaleModel? {
        val response: Response<RemoteFlashSaleModel> = service.getFlashSale()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

}