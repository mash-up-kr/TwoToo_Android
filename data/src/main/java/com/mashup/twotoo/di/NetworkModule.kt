package com.mashup.twotoo.di

import com.mashup.twotoo.data.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import usecase.token.GetAccessTokenUseCase
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        getAccessTokenUseCase: GetAccessTokenUseCase,
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.apply {
            addInterceptor(
                Interceptor { chain ->
                    val token = runBlocking {
                        runCatching {
                            getAccessTokenUseCase()
                        }.getOrDefault("")
                    }
                    val request = chain.request().newBuilder()
                        .addHeader(AUTHORIZATION, token)
                        .build()

                    chain.proceed(request)
                },
            )
            addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY),
            )
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(httpLoggingInterceptor)
            }
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build(),
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder().baseUrl(URL)
            .client(client).addConverterFactory(
                moshiConverterFactory,
            ).build()
    }

    companion object {
        const val URL = "https://twotoo-node-zmtrd.run.goorm.site/"
        const val AUTHORIZATION = "Authorization"
    }
}
