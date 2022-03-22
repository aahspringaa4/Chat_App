package data.repository

import com.example.domain.entity.ResponseFriendListEntity

class FriendListRepository {
    fun getFriendList (friendListId: Int) : @NonNull Single<Response<ResponseFriendListEntity>> =
        ApiService.getPostDetail(ACCESS_TOKEN, friendListId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}