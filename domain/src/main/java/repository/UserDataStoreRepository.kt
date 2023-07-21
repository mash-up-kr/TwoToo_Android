package repository

import model.user.UserAuthResponseDomainModel

interface UserDataStoreRepository {
    suspend fun setVisibilityCheerDialog(visibility: Boolean)
    suspend fun getVisibilityCheerDialog(): Boolean
    suspend fun setVisibilityCompleteDialog(visibility: Boolean)
    suspend fun getVisibilityCompleteDialog(): Boolean
    suspend fun removeVisibilityCheerDialog()
    suspend fun removeVisibilityCompleteDialog()
    suspend fun setUserInfo(userAuthResponseDomainModel: UserAuthResponseDomainModel)
    suspend fun getUserInfo(): UserAuthResponseDomainModel
    suspend fun getIsSendInvitation(): Boolean
    suspend fun setIsSendInvitation(isSend: Boolean)
}
