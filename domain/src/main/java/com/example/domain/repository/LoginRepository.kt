package com.example.domain.repository

import javax.security.auth.login.LoginException

class LoginRepository {
    fun PostLogin () : @NonNull Single<Response<LoginException>> =
        ApiService.getPostDetail(ACCESS_TOKEN)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}