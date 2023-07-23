package com.mashup.twotoo.datasource.remote.challenge.request

import model.challenge.request.CreateChallengeRequestDomainModel

/**
 * @Created by 김현국 2023/07/05
 */
data class CreateChallengeRequest(
    val name: String,
    val description: String,
    val user2Flower: String,
    val startDate: String
)

fun CreateChallengeRequestDomainModel.toDataModel(): CreateChallengeRequest {
    return CreateChallengeRequest(
        name = this.name,
        description = this.description,
        user2Flower = this.user2Flower,
        startDate = this.startDate,
    )
}
