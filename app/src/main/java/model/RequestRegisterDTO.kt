package model

data class RequestRegisterDTO(
    val birth: String,
    val phone: Int,
    val gender: String,
    val name: String,
    val id: String,
    val password: String
)