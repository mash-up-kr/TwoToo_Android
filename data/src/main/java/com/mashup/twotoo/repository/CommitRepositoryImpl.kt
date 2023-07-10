package com.mashup.twotoo.repository

import com.mashup.twotoo.datasource.remote.commit.CommitDataSource
import com.mashup.twotoo.datasource.remote.commit.request.CommitRequest
import com.mashup.twotoo.mapper.toDomainModel
import model.commit.CommitDomainModel
import repository.CommitRepository
import javax.inject.Inject

/**
 * @Created by 김현국 2023/07/10
 */
class CommitRepositoryImpl @Inject constructor(
    private val commitDataSource: CommitDataSource,
) : CommitRepository {
    override suspend fun commit(text: String, img: String): CommitDomainModel {
        return commitDataSource.commit(
            commitRequest = CommitRequest(
                text = text,
                img = img,
            ),
        ).toDomainModel()
    }

    override suspend fun cheer(commitNo: String): CommitDomainModel {
        return commitDataSource.cheerByNo(
            commitNo = commitNo,
        ).toDomainModel()
    }

    override suspend fun getCommit(commitNo: String): CommitDomainModel {
        return commitDataSource.getCommitByNo(
            commitNo = commitNo,
        ).toDomainModel()
    }
}
