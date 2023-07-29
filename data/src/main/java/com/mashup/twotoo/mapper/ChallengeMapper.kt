package com.mashup.twotoo.mapper

import com.mashup.twotoo.datasource.remote.challenge.request.ApproveChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.request.ChallengeNoRequest
import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import com.mashup.twotoo.datasource.remote.challenge.response.User
import com.mashup.twotoo.datasource.remote.challenge.response.UserCommit
import com.mashup.twotoo.datasource.remote.view.response.ViewHomeResponse
import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import model.challenge.response.ChallengeResponseDomainModel
import model.challenge.response.HomeViewResponseDomainModel
import model.challenge.response.UserCommitResponseDomainModel
import model.challenge.response.UserResponseDomainModel

fun ViewHomeResponse.toDomainModel(): HomeViewResponseDomainModel {
    return HomeViewResponseDomainModel(
        viewState = viewState,
        challengeTotal = challengeTotal,
        onGoingChallenge = onGoingChallenge?.toDomainModel(),
        myInfo = myInfo.toDomainModel(),
        myCommit = myCommit?.toDomainModel(),
        partnerInfo = partnerInfo.toDomainModel(),
        partnerCommit = partnerCommit?.toDomainModel(),
        myStingCnt = myStingCnt,
    )
}

fun Challenge.toDomainModel(): ChallengeResponseDomainModel {
    return ChallengeResponseDomainModel(
        challengeNo = this.challengeNo,
        endDate = this.endDate,
        isApproved = this.isApproved,
        isFinished = this.isFinished,
        name = this.name,
        startDate = this.startDate,
        user1 = this.user1.toDomainModel(),
        user1CommitCnt = this.user1CommitCnt,
        user1Flower = this.user1Flower,
        user2 = this.user2.toDomainModel(),
        user2CommitCnt = this.user2CommitCnt,
        user2Flower = this.user2Flower,
        description = this.description ?: ""
    )
}

fun ChallengeNoRequestDomainModel.toDataModel(): ChallengeNoRequest {
    return ChallengeNoRequest(
        challengeNo = this.challengeNo,
    )
}

fun ApproveChallengeRequestDomainModel.toDataModel(): ApproveChallengeRequest {
    return ApproveChallengeRequest(
        user1Flower = this.user1Flower,
    )
}

fun User.toDomainModel(): UserResponseDomainModel {
    return UserResponseDomainModel(
        nickname = this.nickname,
        partnerNo = this.partnerNo,
        userNo = this.userNo,
    )
}

fun UserCommit.toDomainModel(): UserCommitResponseDomainModel {
    return UserCommitResponseDomainModel(
        commitNo = this.commitNo,
        partnerComment = this.partnerComment,
        photoUrl = this.photoUrl,
        text = this.text,
        userNo = this.userNo,
    )
}
