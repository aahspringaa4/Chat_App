package model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ResponseLoginDTO (
    val count: String,
    val data: Data
    )