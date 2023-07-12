package usecase.commit

import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import repository.CommitRepository
import javax.inject.Inject

class CreateCommitUseCase @Inject constructor(
    private val commitRepository: CommitRepository,
) {
    suspend operator fun invoke(
        commitRequestDomainModel: CommitRequestDomainModel,
    ): Result<CommitResponseDomainModel> {
        return commitRepository.commit(
            commitRequestDomainModel = commitRequestDomainModel,
        )
    }
}
