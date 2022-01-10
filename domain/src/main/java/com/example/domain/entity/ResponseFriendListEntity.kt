package com.example.domain.entity

import com.google.gson.JsonObject

class ResponseFriendListEntity {
    var boardInfos: List<JsonObject?>? = null

    @JvmName("getBoardInfos1")
    fun getBoardInfos(): List<JsonObject?>? {
        return boardInfos
    }
}