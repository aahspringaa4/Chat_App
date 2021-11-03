package network

import model.RequestLoginDTO
import model.RequestRegisterDTO
import model.ResponseLoginDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    // 로그인
    @POST("api/auth/login")
    fun Login(
        @Body requestLoginDTO: RequestLoginDTO
    ): Call<ResponseLoginDTO>

    @POST("api/auth/signup")
    fun Register(
        @Body requestRegister: RequestRegisterDTO
    ): Call<ResponseLoginDTO>

    @POST("api/auth/phone")
    fun Phone(
        @Body requestRegister: RequestRegisterDTO
    ): Call<ResponseLoginDTO>
}