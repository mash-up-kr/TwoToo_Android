package repository

interface UserDataStoreRepository {
    suspend fun getAccessToken(): String
    suspend fun setAccessToken(accessToken: String)
    suspend fun setUserNo(userNo: Int)
    suspend fun getUserNo(): Int
    suspend fun setVisibilityCheerDialog(visibility: Boolean)
    suspend fun getVisibilityCheerDialog(): Boolean
    suspend fun setVisibilityCompleteDialog(visibility: Boolean)
    suspend fun getVisibilityCompleteDialog(): Boolean
    suspend fun removeVisibilityCheerDialog()
    suspend fun removeVisibilityCompleteDialog()
}
