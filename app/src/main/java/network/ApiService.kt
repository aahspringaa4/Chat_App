package network

import model.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    // 로그인
    @POST("api/auth/login")
    fun Login(
        @Body requestLoginDTO: RequestLoginDTO
    ): Call<ResponseLoginDTO>

    // 회원가입
    @POST("api/auth/signup")
    fun Register(
        @Body requestRegister: RequestRegisterDTO
    ): Call<ResponseRegisterDTO>

    // 채팅 리스트 조회
    @GET("api/mypage/chattingroom")
    fun ChattingList(): Call<ResponseChattingListDTO>
}