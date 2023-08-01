package com.mashup.twotoo.presenter.garden

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.garden.mapper.toUiModel
import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
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
                val startAnimation: Pair<Boolean, Int> = getStartAnimation(state.challengeCardInfos, challengeCardInfos)
                state.copy(
                    startAnimation = startAnimation,
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

    fun stopAnimation() = intent {
        reduce {
            state.copy(
                startAnimation = Pair(false, 0),
            )
        }
    }

    private fun getStartAnimation(
        curChallengeCardInfos: List<ChallengeCardInfoUiModel>,
        challengeCardInfos: List<ChallengeCardInfoUiModel>,
    ): Pair<Boolean, Int> {
        if (curChallengeCardInfos.isNotEmpty() && challengeCardInfos.isNotEmpty()) {
            if (curChallengeCardInfos.first().challengeNo != challengeCardInfos.first().challengeNo) {
                return (true to challengeCardInfos.first().challengeNo)
            }
        }
        return (false to 0)
    }

    companion object {
        const val TAG = "GardenViewModel"
    }
}
