package com.mashup.twotoo.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.NetworkResult

class NetworkResultCall<T : Any>(
    private val proxy: Call<T>,
    private val coroutineScope: CoroutineScope,
) : Call<NetworkResult<T>> {

    override fun execute(): Response<NetworkResult<T>> = throw NotImplementedError()
    override fun enqueue(callback: Callback<NetworkResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                coroutineScope.launch {
                    val networkResult = handleApi { response }
                    callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResult = NetworkResult.ApiException<T>(t)
                callback.onResponse(this@NetworkResultCall, Response.success(networkResult))
            }
        })
    }

    override fun clone(): Call<NetworkResult<T>> = NetworkResultCall(proxy.clone(), coroutineScope)
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() { proxy.cancel() }
}
