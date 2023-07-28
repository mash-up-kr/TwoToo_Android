package repository

import model.commit.request.CheerRequestDomainModel
import model.commit.request.CommitNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel

interface CommitRepository {

    suspend fun commit(
        commitRequestDomainModel: CommitRequestDomainModel,
    ): Result<CommitResponseDomainModel>

    suspend fun cheer(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
        cheerRequestDomainModel: CheerRequestDomainModel,
    ): Result<CommitResponseDomainModel>

    suspend fun getCommit(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): CommitResponseDomainModel
}
