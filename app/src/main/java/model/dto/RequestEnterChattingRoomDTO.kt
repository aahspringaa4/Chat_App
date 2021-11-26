package model.dto

data class RequestEnterChattingRoomDTO(
    val chattingRoomId: String,
    val chatCategory: String,
    val friendPk: String
)