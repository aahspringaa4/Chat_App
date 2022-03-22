package data.repository

import androidx.annotation.NonNull
import data.api.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ChatRepository {
    fun ChattingSocket (noticeId: Int) : @NonNull Single<Response<ResponseChattingEntity>> =
        ApiService.getPostDetail(ACCESS_TOKEN, noticeId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}