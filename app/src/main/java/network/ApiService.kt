package network

import model.RequestLoginDTO
import model.ResponseLoginDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    // 로그인
    @POST("api/auth/login")
    fun Login(@Body requestLoginDTO: RequestLoginDTO?): Call<ResponseLoginDTO?>?

}