package com.mashup.twotoo.di

import com.mashup.twotoo.repository.UserDataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import repository.UserDataStoreRepository
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserDataStoreRepository(
        userDataStoreRepositoryImpl: UserDataStoreRepositoryImpl
    ): UserDataStoreRepository
}
