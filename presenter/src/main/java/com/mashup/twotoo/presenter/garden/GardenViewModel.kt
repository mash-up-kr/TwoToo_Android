package com.mashup.twotoo.presenter.garden

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.garden.mapper.toUiModel
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
        getAllChallengeUseCase().onSuccess { challenges ->
            val challengeCardInfos = challenges.mapIndexed { index, challengeResponseDomainModel ->
                challengeResponseDomainModel.toUiModel(index + 1)
            }
            reduce {
                state.copy(challengeCardInfos = challengeCardInfos)
            }
        }.onFailure {
            Log.e(TAG, "${it.message} 실패!!")
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
