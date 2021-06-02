package br.com.brasileiraoapp.network.response

data class NetworkError(
    val message: String?,
    val httpCode: Int?
)