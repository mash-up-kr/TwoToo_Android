package com.mashup.twotoo.presenter.garden

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.garden.mapper.toUiModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.GetChallengeHistoriesUseCase
import util.onError
import util.onSuccess

class GardenViewModel(
    private val getChallengeHistoriesUseCase: GetChallengeHistoriesUseCase,
) : ContainerHost<GardenState, GardenSideEffect>, ViewModel() {
    override val container: Container<GardenState, GardenSideEffect> = container(GardenState())

    fun getAllChallenge() = intent {
        reduce {
            state.copy(loadingIndicatorState = true)
        }
        delay(300)
        getChallengeHistoriesUseCase().onSuccess { challenges ->
            val challengeCardInfos = challenges.mapIndexed { index, challengeResponseDomainModel ->
                challengeResponseDomainModel.toUiModel(index = index)
            }.run {
                reversed()
            }
            reduce {
                state.copy(
                    challengeCardInfos = challengeCardInfos.toImmutableList(),
                    loadingIndicatorState = false,
                    hasNotRealChallenge = challengeCardInfos.isEmpty(),
                )
            }
        }.onError { code, message ->
            Log.e(TAG, "$message 실패!!")
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
