package com.mashup.twotoo.mapper

import com.mashup.twotoo.datasource.remote.commit.request.CommitNoRequest
import com.mashup.twotoo.datasource.remote.commit.request.CommitRequest
import com.mashup.twotoo.datasource.remote.commit.response.Commit
import model.commit.request.CommitNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel

// TODO replace mapper in model
fun Commit.toDomainModel(): CommitResponseDomainModel {
    return CommitResponseDomainModel(
        commitNo = this.commitNo,
        userNo = this.userNo,
        text = this.text,
        photoUrl = this.photoUrl,
        partnerComment = this.partnerComment,
    )
}

fun CommitRequestDomainModel.toDataModel(): CommitRequest {
    return CommitRequest(
        text = this.text,
        img = this.img,
    )
}

fun CommitNoRequestDomainModel.toDataModel(): CommitNoRequest {
    return CommitNoRequest(
        commitNo = this.commitNo,
    )
}
