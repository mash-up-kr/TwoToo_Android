package com.mashup.twotoo.presenter.home.repository

import repository.UserDataStoreRepository

class FakeUserDataStoreRepository : UserDataStoreRepository {
    override suspend fun getAccessToken(): String {
        TODO("Not yet implemented")
    }

    override suspend fun setAccessToken(accessToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setUserNo(userNo: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserNo(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun setVisibilityCheerDialog(visibility: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getVisibilityCheerDialog(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setVisibilityCompleteDialog(visibility: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun getVisibilityCompleteDialog(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun removeVisibilityCheerDialog() {
        TODO("Not yet implemented")
    }

    override suspend fun removeVisibilityCompleteDialog() {
        TODO("Not yet implemented")
    }
}
