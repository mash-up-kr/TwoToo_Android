package util

sealed class NetworkResult<T : Any> {
    class ApiSuccess<T : Any>(val data: T) : NetworkResult<T>()
    class ApiError<T : Any>(val code: Int, val error: String) : NetworkResult<T>()
    class ApiException<T : Any>(val e: Throwable) : NetworkResult<T>()

    inline fun <R : Any> map(transform: (T) -> R): NetworkResult<R> {
        return when (this) {
            is ApiSuccess -> ApiSuccess(transform(data))
            is ApiError -> ApiError(code, error)
            is ApiException -> ApiException(e)
        }
    }
}
