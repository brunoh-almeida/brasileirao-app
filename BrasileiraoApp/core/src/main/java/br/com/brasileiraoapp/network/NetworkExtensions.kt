package br.com.brasileiraoapp.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import retrofit2.HttpException
import java.net.HttpURLConnection

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): NetworkResponse<T, NetworkError> {
    return withContext(dispatcher) {
        try {
            NetworkResponse.Success(apiCall())
        } catch (exception: Exception) {
            when (exception) {
                is HttpException -> mapHttpExceptionToResultError(exception)
                else -> mapGenericExceptionToResultError(exception)
            }
        }
    }
}

private fun mapHttpExceptionToResultError(
    httpException: HttpException
) = try {
    NetworkResponse.Error(
        NetworkError(
            httpCode = httpException.code(),
            message = httpException.message() ?: "Ocorreu algum erro, tente novamente mais tarde"
        )
    )
} catch (exception: Exception) {
    mapGenericExceptionToResultError(exception)
}

private fun shouldDeserialize(
    errorBody: String?,
    statusCode: Int
) = !errorBody.isNullOrEmpty() &&
        statusCode != HttpURLConnection.HTTP_FORBIDDEN &&
        statusCode !in HttpURLConnection.HTTP_INTERNAL_ERROR..600

private fun mapGenericExceptionToResultError(
    exception: Exception
) = NetworkResponse.Error(
    NetworkError(
        httpCode = null,
        message = exception.message.toString()
    )
)
