package com.example.remote

import com.google.gson.JsonObject

class ResponseFriendListDTO {
    var boardInfos: List<JsonObject?>? = null

    @JvmName("getBoardInfos1")
    fun getBoardInfos(): List<JsonObject?>? {
        return boardInfos
    }
}