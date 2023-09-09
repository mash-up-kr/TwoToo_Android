package repository

import model.commit.request.CheerRequestDomainModel
import model.commit.request.CommitNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import util.NetworkResult

interface CommitRepository {

    suspend fun commit(
        commitRequestDomainModel: CommitRequestDomainModel,
    ): NetworkResult<CommitResponseDomainModel>

    suspend fun cheer(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
        cheerRequestDomainModel: CheerRequestDomainModel,
    ): NetworkResult<CommitResponseDomainModel>

    suspend fun getCommit(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): NetworkResult<CommitResponseDomainModel>
}
