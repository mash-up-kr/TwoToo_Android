package com.mashup.twotoo.presenter.createChallenge

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.createChallenge.model.ChallengeInfoModel
import model.challenge.request.CreateChallengeRequestDomainModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.CreateChallengeUseCase
import javax.inject.Inject

class CreateChallengeViewModel@Inject constructor(
    private val createChallengeUseCase: CreateChallengeUseCase
) : ViewModel(), ContainerHost<CreateChallengeState, CreateChallengeSideEffect> {
    override val container = container<CreateChallengeState, CreateChallengeSideEffect>(CreateChallengeState())

    fun setChallengeInfo(challengeInfoModel: ChallengeInfoModel) = intent {
        reduce {
            state.copy(challengeInfoModel = challengeInfoModel)
        }
    }

    fun createChallenge() = intent {
        createChallengeUseCase(CreateChallengeRequestDomainModel()).onSuccess {
            postSideEffect(CreateChallengeSideEffect.NavigateToSuccessCreate)
        }.onFailure {
            postSideEffect(CreateChallengeSideEffect.ToastMessage(""))
        }
    }
}
