package com.mashup.twotoo.di

import com.mashup.twotoo.datasource.remote.challenge.ChallengeApi
import com.mashup.twotoo.datasource.remote.commit.CommitApi
import com.mashup.twotoo.datasource.remote.notification.NotificationApi
import com.mashup.twotoo.datasource.remote.user.UserApi
import com.mashup.twotoo.datasource.remote.view.ViewApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

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

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCommitApi(retrofit: Retrofit): CommitApi {
        return retrofit.create(CommitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNotificationApi(retrofit: Retrofit): NotificationApi {
        return retrofit.create(NotificationApi::class.java)
    }
}
