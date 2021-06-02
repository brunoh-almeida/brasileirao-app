package br.com.brasileiraoapp.network.response

data class BaseResultResponse<T>(
    val content: T?
)