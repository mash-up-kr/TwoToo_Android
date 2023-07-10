package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.commit.CommitDataSource
import com.mashup.twotoo.mapper.toDataModel
import com.mashup.twotoo.mapper.toDomainModel
import model.commit.request.CommitNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import repository.CommitRepository
import javax.inject.Inject

class CommitRepositoryImpl @Inject constructor(
    private val commitDataSource: CommitDataSource,
) : CommitRepository {
    override suspend fun commit(
        commitRequestDomainModel: CommitRequestDomainModel,
    ): CommitResponseDomainModel {
        return commitDataSource.commit(
            commitRequest = commitRequestDomainModel.toDataModel(),
        ).toDomainModel()
    }

    override suspend fun cheer(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): CommitResponseDomainModel {
        return commitDataSource.cheerByNo(
            commitNoRequest = commitNoRequestDomainModel.toDataModel(),
        ).toDomainModel()
    }

    override suspend fun getCommit(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): CommitResponseDomainModel {
        return commitDataSource.getCommitByNo(
            commitNoRequest = commitNoRequestDomainModel.toDataModel(),
        ).toDomainModel()
    }
}
