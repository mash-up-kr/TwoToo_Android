package usecase.commit

import model.commit.request.CommitNoRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import repository.CommitRepository
import javax.inject.Inject

class CreateCheerUseCase @Inject constructor(
    private val commitRepository: CommitRepository,
) {
    suspend operator fun invoke(
        commitNoRequestDomainModel: CommitNoRequestDomainModel,
    ): Result<CommitResponseDomainModel> {
        return commitRepository.cheer(
            commitNoRequestDomainModel = commitNoRequestDomainModel,
        )
    }
}
