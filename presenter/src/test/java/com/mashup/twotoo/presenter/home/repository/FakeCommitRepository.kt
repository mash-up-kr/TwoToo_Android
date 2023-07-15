package com.mashup.twotoo.presenter.home.repository

import model.commit.request.CommitNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import repository.CommitRepository

class FakeCommitRepository : CommitRepository {
    override suspend fun commit(commitRequestDomainModel: CommitRequestDomainModel): Result<CommitResponseDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun cheer(commitNoRequestDomainModel: CommitNoRequestDomainModel): CommitResponseDomainModel {
        TODO("Not yet implemented")
    }

    override suspend fun getCommit(commitNoRequestDomainModel: CommitNoRequestDomainModel): CommitResponseDomainModel {
        TODO("Not yet implemented")
    }
}
