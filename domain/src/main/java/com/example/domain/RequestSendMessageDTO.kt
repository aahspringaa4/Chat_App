package com.example.remote

import ui.activity.ChattingActivity

data class RequestSendMessageDTO (
    val chattingRoomId: String = "adfbeefc-3307-4ccd-8dbf-3aa2401e4781",
    val messages: String
)