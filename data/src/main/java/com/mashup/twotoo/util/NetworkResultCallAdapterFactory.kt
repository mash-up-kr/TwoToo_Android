package com.mashup.twotoo.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import util.NetworkResult
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResultCallAdapterFactory private constructor(
    private val coroutineScope: CoroutineScope
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != NetworkResult::class.java) {
            return null
        }

        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkResultCallAdapter(resultType, coroutineScope)
    }

    companion object {
        fun create(coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)): NetworkResultCallAdapterFactory =
            NetworkResultCallAdapterFactory(coroutineScope)
    }
}
