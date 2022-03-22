package data.repository

import androidx.annotation.NonNull
import io.reactivex.Single

class MypageRepository {
    fun getMypage(mypageId: Int) : @NonNull Single<Response<ResponseMypageDTO>> =
        ApiService.getPostDetail(ACCESS_TOKEN, mypageId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}