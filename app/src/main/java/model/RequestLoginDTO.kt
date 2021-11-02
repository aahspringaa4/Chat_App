package model

import com.google.gson.annotations.SerializedName

data class RequestLoginDTO(
    val id: String,
    val password: String
)