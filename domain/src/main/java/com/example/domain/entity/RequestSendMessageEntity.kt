package com.example.domain.entity

import ui.activity.ChattingActivity

data class RequestSendMessageEntity (
    val chattingRoomId: String = "adfbeefc-3307-4ccd-8dbf-3aa2401e4781",
    val messages: String
)