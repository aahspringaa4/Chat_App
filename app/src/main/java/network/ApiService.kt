package network

import model.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ui.fragment.FriendList


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

    // 친구 추가
    @POST("api/friend/apply/{memberId}")
    fun FriendApply(
        @Query("memberId") memberId : String
    )

    @GET("api/friend")
    fun FriendList(

    )

    @GET("api/friend/chattingRoom/list")
    fun ChatRoomList(

    )
}