package com.mashup.twotoo.presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetData
import com.mashup.twotoo.presenter.home.di.HomeScope
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.model.HomeSideEffect
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import usecase.view.GetViewHomeUseCase
import javax.inject.Inject

/**
 * @Created by 김현국 2023/06/23
 */

@HomeScope
class HomeViewModel @Inject constructor(
    private val getHomeViewUseCase: GetViewHomeUseCase,
) : ViewModel(), ContainerHost<ChallengeStateTypeUiModel, HomeSideEffect> {

    override val container: Container<ChallengeStateTypeUiModel, HomeSideEffect> = container(OngoingChallengeUiModel.default)

    fun getHomeViewChallenge() {
        viewModelScope.launch {
            getHomeViewUseCase()
        }
    }
    fun navigateToHistory() = intent {
        postSideEffect(HomeSideEffect.NavigateToChallengeDetail)
    }

    fun openToShotBottomSheet() = intent {
        postSideEffect(HomeSideEffect.OpenToShotBottomSheet)
    }

    fun openToAuthBottomSheet() = intent {
        postSideEffect(HomeSideEffect.OpenToAuthBottomSheet)
    }

    fun onClickBeforeChallengeTextButton(beforeChallengeState: BeforeChallengeState) = intent {
        // TODO create Challenge navigation 연결
        when (beforeChallengeState) {
            BeforeChallengeState.EMPTY -> {
                postSideEffect(HomeSideEffect.NavigationToCreateChallenge)
            }
            BeforeChallengeState.REQUEST -> {
                // Todo 챌린지 확인 페이지 이동
            }
            BeforeChallengeState.RESPONSE -> {
                // Todo 챌린지 확인 페이지 이동 Step3
            }
            BeforeChallengeState.WAIT -> {
                // Todo 챌린지 확인 페이지 이동
            }
            BeforeChallengeState.TERMINATION -> {
                postSideEffect(HomeSideEffect.NavigationToCreateChallenge)
            }
        }
    }

    fun onClickSendBottomSheetDataButton(bottomSheetData: BottomSheetData) = intent {
        when (bottomSheetData) {
            is BottomSheetData.AuthenticateData -> {
                // 서버 데이터 전송 sideEffect

                // toast sideEffect
                // Todo 텍스트 string resource로 변경
                HomeSideEffect.Toast(
                    "오늘의 인증을 완료했습니다.",
                )
            }
            is BottomSheetData.ShotData -> {
                // 서버 데이터 전송

                // toast sideEffect
                postSideEffect(
                    HomeSideEffect.Toast(
                        "상대방에게 찌르기 문구가 보내졌습니다.",
                    ),
                )
            }
            is BottomSheetData.CheerData -> {
                // 서버 데이터 전송

                // toast sideEffect
                postSideEffect(
                    HomeSideEffect.Toast(
                        "칭찬 한마디가 등록되었습니다.",
                    ),
                )
            }
        }
    }
}
