package com.mashup.twotoo.mapper

import android.content.Context
import android.net.Uri
import com.mashup.twotoo.datasource.remote.commit.request.CommitNoRequest
import com.mashup.twotoo.datasource.remote.commit.request.CommitRequest
import com.mashup.twotoo.datasource.remote.commit.response.Commit
import com.mashup.twotoo.util.ContentUriRequestBody
import model.commit.request.CommitNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import okhttp3.MultipartBody

// TODO replace mapper in model
fun Commit.toDomainModel(): CommitResponseDomainModel {
    return CommitResponseDomainModel(
        commitNo = this.commitNo,
        userNo = this.userNo,
        text = this.text,
        createdAt = this.createdAt,
        photoUrl = this.photoUrl,
        partnerComment = this.partnerComment,
    )
}

fun CommitRequestDomainModel.toDataModel(context: Context): CommitRequest {
    val textMultiPart = MultipartBody.Part.createFormData("text", this.text)
    val challengeNoMultiPart = MultipartBody.Part.createFormData("challengeNo", this.challengeNo)
    val requestBody = ContentUriRequestBody(context = context, Uri.parse(this.img))
    val imgMultiPart = MultipartBody.Part.createFormData("img", requestBody.getFileName(), requestBody)
    return CommitRequest(
        text = textMultiPart,
        challengeNo = challengeNoMultiPart,
        img = imgMultiPart,
    )
}

fun CommitNoRequestDomainModel.toDataModel(): CommitNoRequest {
    return CommitNoRequest(
        commitNo = this.commitNo,
    )
}
