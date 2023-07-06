package com.mashup.twotoo.presenter.createChallenge

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.createChallenge.model.ChallengeInfoModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CreateChallengeViewModel : ViewModel(), ContainerHost<CreateChallengeState, Unit> {
    override val container = container<CreateChallengeState, Unit>(CreateChallengeState())

    fun setChallengeInfo(challengeInfoModel: ChallengeInfoModel) = intent {
        reduce {
            state.copy(challengeInfoModel = challengeInfoModel)
        }
    }
}
