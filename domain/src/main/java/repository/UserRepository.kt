package repository

import model.user.PartnerInfoDomainModel
import model.user.UserAuthRequestDomainModel
import model.user.UserAuthResponseDomainModel
import model.user.UserInfoDomainModel
import model.user.UserNickNameDomainModel

interface UserRepository {
    suspend fun userAuthorize(userAuthRequestDomainModel: UserAuthRequestDomainModel): Result<UserAuthResponseDomainModel>
    suspend fun setUserNickName(userNickNameDomainModel: UserNickNameDomainModel): Result<UserInfoDomainModel>
    suspend fun getPartnerInfo(): Result<PartnerInfoDomainModel>
    suspend fun getUserInfo(): Result<UserInfoDomainModel>
}
