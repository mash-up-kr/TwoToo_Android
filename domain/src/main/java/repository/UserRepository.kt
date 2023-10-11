package repository

import model.user.PartnerInfoDomainModel
import model.user.UserAuthRequestDomainModel
import model.user.UserAuthResponseDomainModel
import model.user.UserInfoDomainModel
import model.user.UserNickNameDomainModel
import util.NetworkResult

interface UserRepository {
    suspend fun userAuthorize(userAuthRequestDomainModel: UserAuthRequestDomainModel): NetworkResult<UserAuthResponseDomainModel>
    suspend fun setUserNickName(userNickNameDomainModel: UserNickNameDomainModel): NetworkResult<UserInfoDomainModel>
    suspend fun getPartnerInfo(): NetworkResult<PartnerInfoDomainModel>
    suspend fun getUserInfo(): NetworkResult<UserInfoDomainModel>
    suspend fun deletePartner(): NetworkResult<Boolean>
    suspend fun signOut(): NetworkResult<Boolean>
    suspend fun changeNickname(userNickNameDomainModel: UserNickNameDomainModel): NetworkResult<UserInfoDomainModel>
}
