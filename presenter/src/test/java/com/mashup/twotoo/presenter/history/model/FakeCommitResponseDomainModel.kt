package com.mashup.twotoo.presenter.history.model

import model.commit.response.CommitResponseDomainModel
import java.time.Instant
import java.time.format.DateTimeFormatter

fun generateFakeCommitList(startDate: String, count: Int, userNo: Int): List<CommitResponseDomainModel> {
    val formatter = DateTimeFormatter.ISO_INSTANT
    val startInstant = Instant.parse(startDate)

    return List(count) { index ->
        val createdAt = startInstant.plusSeconds(index.toLong() * 86400)
        CommitResponseDomainModel(
            commitNo = index + 1,
            userNo = userNo, // Example user number
            text = "",
            photoUrl = "",
            createdAt = formatter.format(createdAt),
            partnerComment = "",
        )
    }
}
