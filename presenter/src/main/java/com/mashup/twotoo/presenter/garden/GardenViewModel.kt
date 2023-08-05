package com.mashup.twotoo.presenter.garden

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.garden.mapper.toUiModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.GetAllChallengeUseCase

class GardenViewModel(
    private val getAllChallengeUseCase: GetAllChallengeUseCase,
) : ContainerHost<GardenState, GardenSideEffect>, ViewModel() {
    override val container: Container<GardenState, GardenSideEffect> = container(GardenState())

    fun getAllChallenge() = intent {
        reduce {
            state.copy(loadingIndicatorState = true)
        }
        delay(300)
        getAllChallengeUseCase().onSuccess { challenges ->
            val challengeCardInfos = challenges.filter { it.isFinished }.mapIndexed { index, challengeResponseDomainModel ->
                challengeResponseDomainModel.toUiModel(index)
            }.run {
                reversed()
            }
            reduce {
                state.copy(
                    challengeCardInfos = challengeCardInfos,
                    loadingIndicatorState = false,
                    hasNotRealChallenge = challengeCardInfos.isEmpty(),
                )
            }
        }.onFailure {
            Log.e(TAG, "${it.message} 실패!!")
            reduce {
                state.copy(
                    loadingIndicatorState = false,
                )
            }
            postSideEffect(
                GardenSideEffect.Toast(
                    "정원 정보를 읽어오는데 실패했습니다ㅜ_ㅠ",
                ),
            )
        }
    }

    companion object {
        const val TAG = "GardenViewModel"
    }
}
