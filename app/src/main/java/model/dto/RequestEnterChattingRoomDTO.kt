package model.dto

data class RequestEnterRoomDTO(
    val chattingRoomId: String,
    val chatCategory: String,
    val friendPk: String
)