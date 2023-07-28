package com.mashup.twotoo.presenter.createChallenge

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.createChallenge.model.ChallengeInfoModel
import com.mashup.twotoo.presenter.createChallenge.model.toDomainModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.CreateChallengeUseCase
import javax.inject.Inject

class CreateChallengeViewModel@Inject constructor(
    private val createChallengeUseCase: CreateChallengeUseCase
) : ViewModel(), ContainerHost<ChallengeInfoModel, CreateChallengeSideEffect> {
    override val container = container<ChallengeInfoModel, CreateChallengeSideEffect>(ChallengeInfoModel())

    fun setChallengeInfo(challengeInfo: ChallengeInfoModel, step: Int) = intent {
        reduce {
            when (step) {
                1 -> { state.copy(
                    challengeName = challengeInfo.challengeName,
                    startDate = challengeInfo.startDate,
                    endDate = challengeInfo.endDate,
                    period = challengeInfo.period,
                )
                }
                2 -> { state.copy(
                    challengeInfo = challengeInfo.challengeInfo,
                )
                }
                else -> { state.copy(
                    selectFlowerName = challengeInfo.selectFlowerName,
                )
                }
            }
        }
    }

    fun createChallenge() = intent {
        createChallengeUseCase(state.toDomainModel()).onSuccess {
            postSideEffect(CreateChallengeSideEffect.NavigateToSuccessCreate)
        }.onFailure {
            Log.d(TAG, "fail:${it.message}")
            it.message
            postSideEffect(CreateChallengeSideEffect.ToastMessage(""))
        }
    }
}