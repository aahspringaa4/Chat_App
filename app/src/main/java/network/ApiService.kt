package network

import android.widget.EditText
import model.dto.*
import retrofit2.Call
import retrofit2.http.*


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
        // Id 전송
        @Path("memberId") memberId: String
    ): Call<RequestFriendListDTO> // 오류 방지

    @GET("api/friend")
    fun FriendList(
        @Query("size") size : Int?,
        @Query("page") page : Int?
    ): Call<ResponseFriendListDTO>

    @GET("api/friend/chattingRoom/list")
    fun ChatRoomList(

    )
}