package usecase.commit

import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import repository.CommitRepository
import util.NetworkResult
import javax.inject.Inject

class CreateCommitUseCase @Inject constructor(
    private val commitRepository: CommitRepository,
) {
    suspend operator fun invoke(
        commitRequestDomainModel: CommitRequestDomainModel,
    ): NetworkResult<CommitResponseDomainModel> {
        return commitRepository.commit(
            commitRequestDomainModel = commitRequestDomainModel,
        )
    }
}
