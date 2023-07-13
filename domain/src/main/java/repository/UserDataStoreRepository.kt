package repository

interface UserDataStoreRepository {
    suspend fun getAccessToken(): String
    suspend fun setAccessToken(accessToken: String)
    suspend fun setUserNo(userNo: Int)
    suspend fun getUserNo(): Int
}
