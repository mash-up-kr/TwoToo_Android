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
import util.NetworkResult
import javax.inject.Inject

class CommitRepositoryImpl @Inject constructor(
    private val commitDataSource: CommitDataSource,
    private val context: Context,
) : CommitRepository {
    override suspend fun commit(
        commitRequestDomainModel: CommitRequestDomainModel,
    ): NetworkResult<CommitResponseDomainModel> {
        return commitDataSource.commit(
            commitRequest = commitRequestDomainModel.toDataModel(context = context),
        ).map { commit ->
            commit.toDomainModel()
        }
    }

    override suspend fun cheer(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
        cheerRequestDomainModel: CheerRequestDomainModel,
    ): NetworkResult<CommitResponseDomainModel> {
        return commitDataSource.cheerByNo(
            commitNoRequest = commitNoRequestDomainModel.toDataModel(),
            cheerRequest = cheerRequestDomainModel.toDataModel(),
        ).map { commit ->
            commit.toDomainModel()
        }
    }

    override suspend fun getCommit(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): NetworkResult<CommitResponseDomainModel> {
        return commitDataSource.getCommitByNo(
            commitNoRequest = commitNoRequestDomainModel.toDataModel(),
        ).map { commit ->
            commit.toDomainModel()
        }
    }
}
