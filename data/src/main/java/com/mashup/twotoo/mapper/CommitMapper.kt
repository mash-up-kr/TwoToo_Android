package com.mashup.twotoo.mapper

import com.mashup.twotoo.datasource.remote.commit.response.Commit
import model.commit.CommitDomainModel

/**
 * @Created by 김현국 2023/07/10
 */

fun Commit.toDomainModel(): CommitDomainModel {
    return CommitDomainModel(
        commitNo = this.commitNo,
        userNo = this.userNo,
        text = this.text,
        photoUrl = this.photoUrl,
        partnerComment = this.partnerComment,
    )
}
