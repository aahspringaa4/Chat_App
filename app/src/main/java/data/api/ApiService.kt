package data.api

import io.reactivex.Single
import model.ChattingData
import data.dto.request.RequestChattingListDTO
import data.dto.request.RequestFriendListDTO
import data.dto.request.RequestLoginDTO
import data.dto.request.RequestRegisterDTO
import data.dto.response.ResponseChattingListDTO
import data.dto.response.ResponseFriendListDTO
import data.dto.response.ResponseLoginDTO
import data.dto.response.ResponseRegisterDTO
import retrofit2.Call
import retrofit2.Response
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

    @GET("api/mypage/chattingroom")
    fun ChatRoomList(
        @Body chattingListDTO: RequestChattingListDTO
    ): Call<ResponseChattingListDTO>

    @GET("api/message/{chattingRoomId}/{count}")
    fun getChatting(
        @Path("chattingRoomId") chattingRoomId:String,
        @Path("count") count: Int
    ): Single<Response<ArrayList<ChattingData>>>
}