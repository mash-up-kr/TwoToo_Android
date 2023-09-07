package com.mashup.twotoo.presenter.repository

import model.challenge.response.HomeViewResponseDomainModel
import repository.ViewRepository

class FakeViewBeforeCreateRepository : ViewRepository {
    override suspend fun getViewHome(): Result<HomeViewResponseDomainModel> {
        return Result.success(HomeViewResponseDomainModel.beforeCreate)
    }
}
class FakeViewBeforeMyApproveRepository : ViewRepository {
    override suspend fun getViewHome(): Result<HomeViewResponseDomainModel> {
        return Result.success(HomeViewResponseDomainModel.beforeMyApprove)
    }
}

class FakeViewBeforePartnerApproveRepository : ViewRepository {
    override suspend fun getViewHome(): Result<HomeViewResponseDomainModel> {
        return Result.success(HomeViewResponseDomainModel.beforePartnerApprove)
    }
}
class FakeViewExpiredByNotApprovedRepository : ViewRepository {
    override suspend fun getViewHome(): Result<HomeViewResponseDomainModel> {
        return Result.success(HomeViewResponseDomainModel.expiredByNotApproved)
    }
}
class FakeViewApprovedButBeforeStartDateRepository : ViewRepository {
    override suspend fun getViewHome(): Result<HomeViewResponseDomainModel> {
        return Result.success(HomeViewResponseDomainModel.approvedButBeforeStartDate)
    }
}
class FakeViewInProgressRepository : ViewRepository {
    override suspend fun getViewHome(): Result<HomeViewResponseDomainModel> {
        return Result.success(HomeViewResponseDomainModel.inProgress)
    }
}
class FakeViewCompleteRepository : ViewRepository {
    override suspend fun getViewHome(): Result<HomeViewResponseDomainModel> {
        return Result.success(HomeViewResponseDomainModel.complete)
    }
}
