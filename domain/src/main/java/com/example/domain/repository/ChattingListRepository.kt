package com.example.domain.repository

import com.example.domain.entity.ResponseChattingListEntity

class ChattingListRepository {
    fun getChattingList (chattingRoomId: Int) : @NonNull Single<Response<ResponseChattingListEntity>> =
        ApiService.getPostDetail(ACCESS_TOKEN, chattingRoomId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}