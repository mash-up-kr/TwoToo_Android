package com.mashup.twotoo.di

import com.mashup.twotoo.datasource.remote.challenge.ChallengeApi
import com.mashup.twotoo.datasource.remote.view.ViewApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @Created by 김현국 2023/07/04
 */

@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideChallengeApi(retrofit: Retrofit): ChallengeApi {
        return retrofit.create(ChallengeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideViewApi(retrofit: Retrofit): ViewApi {
        return retrofit.create(ViewApi::class.java)
    }
}
