package com.example.domain

data class RequestRegisterEntity(
    val birth: String,
    val phone: String,
    val gender: String,
    val name: String,
    val id: String,
    val password: String
)