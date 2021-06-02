package br.com.brasileiraoapp.network.response

sealed class NetworkResponse<out D, out E> {

    data class Success<D>(val value: D) : NetworkResponse<D, Nothing>()
    data class Error<E>(val value: E) : NetworkResponse<Nothing, E>()

    inline fun handleResult(
        onSuccess: (result: D) -> Unit = {},
        onError: (error: E) -> Unit = {},
        onFinish: (D?) -> Unit = {}
    ): D? = when (this) {
        is Success -> {
            onSuccess(value)
            onFinish(value)
            value
        }
        is Error -> {
            onError(value)
            onFinish(null)
            null
        }
    }

    inline fun <T> mapSuccess(transform: (D) -> T): NetworkResponse<T, E> = when (this) {
        is Success -> Success(transform(value))
        is Error -> Error(value)
    }

    inline fun <T> mapError(transform: (E) -> T): NetworkResponse<D, T> = when (this) {
        is Success -> Success(value)
        is Error -> Error(transform(value))
    }

    inline fun onSuccess(block: (D) -> Unit): NetworkResponse<D, E> {
        if (this is Success) block(value)
        return this
    }

    inline fun onError(block: (E) -> Unit): NetworkResponse<D, E> {
        if (this is Error) block(value)
        return this
    }

}