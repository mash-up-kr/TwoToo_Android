package util
suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.ApiSuccess<T>) {
        executable(data)
    }
}

suspend fun <T : Any> NetworkResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.ApiError<T>) {
        executable(code, error)
    }
}

suspend fun <T : Any> NetworkResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.ApiException<T>) {
        executable(e)
    }
}
