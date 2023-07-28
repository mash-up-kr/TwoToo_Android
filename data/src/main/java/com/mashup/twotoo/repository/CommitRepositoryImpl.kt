package com.mashup.twotoo.repository

import android.content.Context
import com.mashup.twotoo.datasource.remote.commit.CommitDataSource
import com.mashup.twotoo.mapper.toDataModel
import com.mashup.twotoo.mapper.toDomainModel
import model.commit.request.CheerRequestDomainModel
import model.commit.request.CommitNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import repository.CommitRepository
import javax.inject.Inject

class CommitRepositoryImpl @Inject constructor(
    private val commitDataSource: CommitDataSource,
    private val context: Context,
) : CommitRepository {
    override suspend fun commit(
        commitRequestDomainModel: CommitRequestDomainModel,
    ): Result<CommitResponseDomainModel> {
        return runCatching {
            commitDataSource.commit(
                commitRequest = commitRequestDomainModel.toDataModel(context = context),
            ).toDomainModel()
        }
    }

    override suspend fun cheer(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
        cheerRequestDomainModel: CheerRequestDomainModel,
    ): Result<CommitResponseDomainModel> {
        return runCatching {
            commitDataSource.cheerByNo(
                commitNoRequest = commitNoRequestDomainModel.toDataModel(),
                cheerRequest = cheerRequestDomainModel.toDataModel(),
            ).toDomainModel()
        }
    }

    override suspend fun getCommit(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): CommitResponseDomainModel {
        return commitDataSource.getCommitByNo(
            commitNoRequest = commitNoRequestDomainModel.toDataModel(),
        ).toDomainModel()
    }
}
