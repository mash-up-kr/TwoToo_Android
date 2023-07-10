package repository

import model.commit.CommitDomainModel

/**
 * @Created by 김현국 2023/07/10
 */
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
