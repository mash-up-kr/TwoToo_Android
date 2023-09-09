package com.mashup.twotoo.util

import util.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.ApiSuccess(body)
        } else {
            NetworkResult.ApiError(code = response.code(), error = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.ApiError(code = e.code(), error = e.message())
    } catch (e: Throwable) {
        NetworkResult.ApiException(e)
    }
}
