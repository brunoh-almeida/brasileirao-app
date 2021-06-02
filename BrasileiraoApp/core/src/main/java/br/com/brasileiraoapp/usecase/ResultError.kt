package br.com.brasileiraoapp.usecase

sealed class ResultError(val msg: String?) {
    class UnavailableNetworkConnectionError : ResultError("Connection unavailable")
    class UnauthorizedError : ResultError("User not authorized")
   data class UnknownError(val message: String? = null) : ResultError(message)
}