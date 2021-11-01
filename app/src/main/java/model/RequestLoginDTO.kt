package model

public class RequestLoginDTO(username: String, password: String) {
    var username: String? = null
    var password: String? = null

    fun RequestLogin(username: String?, password: String?) {
        this.username = username
        this.password = password
    }

    @JvmName("getUsername1")
    fun getUsername(): String? {
        return username
    }

    @JvmName("setUsername1")
    fun setUsername(username: String?) {
        this.username = username
    }

    @JvmName("getPassword1")
    fun getPassword(): String? {
        return password
    }

    @JvmName("setPassword1")
    fun setPassword(password: String?) {
        this.password = password
    }
}