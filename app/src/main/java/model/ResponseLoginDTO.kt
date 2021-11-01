package model

import com.google.gson.annotations.SerializedName

public class ResponseLoginDTO {
    @SerializedName("Bearer-Token")
    var Token: String? = null

    @SerializedName("User-Id")
    var user_id = 0

    @JvmName("getUser_id1")
    fun getUser_id(): Int {
        return user_id
    }

    @JvmName("setUser_id1")
    fun setUser_id(user_id: Int) {
        this.user_id = user_id
    }

    @JvmName("getToken1")
    fun getToken(): String? {
        return Token
    }

    @JvmName("setToken1")
    fun setToken(token: String?) {
        Token = token
    }
}