import model.RequestLoginDTO
import model.ResponseLoginDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface ApiService {
    @POST("api/auth/login")
    public fun  // 로그인
            Login(@Body requestLoginDTO: RequestLoginDTO?): Call<ResponseLoginDTO?>?

}