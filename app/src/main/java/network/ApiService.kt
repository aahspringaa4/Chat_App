package network

import model.DTO.RequestLoginDTO
import model.DTO.RequestRegisterDTO
import model.DTO.ResponseLoginDTO
import model.DTO.ResponseRegisterDTO
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
    ): Call<ResponseRegisterDTO>
}