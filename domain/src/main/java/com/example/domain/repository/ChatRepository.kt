package com.example.domain.repository

import com.example.domain.entity.ResponseChattingListEntity

class ChatRepository {
    fun ChattingSocket (noticeId: Int) : @NonNull Single<Response<ResponseChattingEntity>> =
        ApiService.getPostDetail(ACCESS_TOKEN, noticeId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}