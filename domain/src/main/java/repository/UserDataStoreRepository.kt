package repository

interface UserDataStoreRepository {
    suspend fun getAccessToken(): String
    suspend fun setAccessToken(accessToken: String)
    suspend fun getKaKaoAccessToken(): String
    suspend fun setKaKaoAccessToken(kaKaoAccessToken: String)
    suspend fun getKaKaoRefreshToken(): String
    suspend fun setKaKaoRefreshToken(kaKaoRefreshToken: String)
}
