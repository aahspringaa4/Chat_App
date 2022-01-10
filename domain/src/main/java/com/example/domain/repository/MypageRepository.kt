package com.example.domain.repository

class MypageRepository {
    fun getMypage(mypageId: Int) : @NonNull Single<Response<ResponseMypageEntity>> =
        ApiService.getPostDetail(ACCESS_TOKEN, mypageId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}