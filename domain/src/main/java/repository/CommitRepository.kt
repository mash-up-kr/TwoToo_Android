package repository

import model.commit.request.CommitNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel

interface CommitRepository {

    suspend fun commit(
        commitRequestDomainModel: CommitRequestDomainModel,
    ): CommitResponseDomainModel

    suspend fun cheer(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): CommitResponseDomainModel

    suspend fun getCommit(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): CommitResponseDomainModel
}
