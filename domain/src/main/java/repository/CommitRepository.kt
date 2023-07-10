package repository

import model.commit.CommitDomainModel


interface CommitRepository {

    suspend fun commit(
        text: String,
        img: String,
    ): CommitDomainModel

    suspend fun cheer(
        commitNo: String,
    ): CommitDomainModel

    suspend fun getCommit(
        commitNo: String,
    ): CommitDomainModel
}
