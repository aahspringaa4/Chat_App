package data.repository

import androidx.annotation.NonNull
import data.api.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.security.auth.login.LoginException

class LoginRepository {
    fun PostLogin () : @NonNull Single<Response<LoginException>> =
        ApiService.getPostDetail(ACCESS_TOKEN)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}