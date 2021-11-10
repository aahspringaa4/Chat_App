package model.dto

data class RequestRegisterDTO(
    val birth: String,
    val phone: String,
    val gender: String,
    val name: String,
    val id: String,
    val password: String
)