package model.challenge.request

data class CreateChallengeRequestDomainModel(
    val name: String = "",
    val description: String = "",
    val user2Flower: String = "",
    val startDate: String = ""
)
