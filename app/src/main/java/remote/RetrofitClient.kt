package remote

import ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private var instance: RetrofitClient? = null
    private var ApiService: ApiService? = null

    //사용하고 있는 서버 BASE 주소
    private const val baseUrl = "http://54.180.98.98:9094/"

    private fun RetrofitClient() {

        //retrofit 설정
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        ApiService = retrofit.create(ApiService!!::class.java)
    }

    fun getInstance(): RetrofitClient? {
        if (instance == null) {
            instance = RetrofitClient
        }
        return instance
    }

    fun getRetrofitInterface(): ApiService? {
        return ApiService
    }
}