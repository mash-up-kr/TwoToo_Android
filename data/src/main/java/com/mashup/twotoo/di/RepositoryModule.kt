package com.mashup.twotoo.di

import com.mashup.twotoo.repository.ChallengeRepositoryImpl
import com.mashup.twotoo.repository.UserDataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import repository.ChallengeRepository
import repository.UserDataStoreRepository
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserDataStoreRepository(
        userDataStoreRepositoryImpl: UserDataStoreRepositoryImpl,
    ): UserDataStoreRepository

    @Binds
    @Singleton
    abstract fun bindChallengeRepository(
        challengeRepositoryImpl: ChallengeRepositoryImpl,
    ): ChallengeRepository
}
