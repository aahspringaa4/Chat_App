package model.dto

data class RequestSendMessageDTO (
    val chattingRoomId: String,
    val message: String
)