package com.mashup.twotoo.di

import com.mashup.twotoo.repository.ChallengeRepositoryImpl
import com.mashup.twotoo.repository.CommitRepositoryImpl
import com.mashup.twotoo.repository.UserDataStoreRepositoryImpl
import com.mashup.twotoo.repository.UserRepositoryImpl
import com.mashup.twotoo.repository.ViewRepositoryImpl
import dagger.Binds
import dagger.Module
import repository.ChallengeRepository
import repository.CommitRepository
import repository.UserDataStoreRepository
import repository.UserRepository
import repository.ViewRepository
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

    @Binds
    @Singleton
    abstract fun bindViewRepository(
        viewRepositoryImpl: ViewRepositoryImpl,
    ): ViewRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindCommitRepository(
        commitRepositoryImpl: CommitRepositoryImpl,
    ): CommitRepository
}
