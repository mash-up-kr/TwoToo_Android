package com.mashup.twotoo.presenter.home.repository

import model.user.UserAuthResponseDomainModel
import repository.UserDataStoreRepository

class FakeUserDataStoreRepository : UserDataStoreRepository {

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

    override suspend fun setUserInfo(userAuthResponseDomainModel: UserAuthResponseDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(): UserAuthResponseDomainModel? {
        TODO("Not yet implemented")
    }

    override suspend fun getIsSendInvitation(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun setIsSendInvitation(isSend: Boolean) {
        TODO("Not yet implemented")
    }
}
