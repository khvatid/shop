package khvatid.shop.data.storage.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {


    private var instance: ApiService? = null

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun execute(): ApiService {
        synchronized(this) {
            var instance = this.instance
            if (instance == null) {
                instance = retrofit.create(ApiService::class.java)
                this.instance = instance
            }
            return this.instance!!
        }
    }

    companion object {
        private const val BASE_URL = "https://run.mocky.io/v3/"
    }
}