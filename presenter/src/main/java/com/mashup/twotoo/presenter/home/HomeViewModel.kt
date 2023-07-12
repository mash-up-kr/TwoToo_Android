package com.mashup.twotoo.presenter.home

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetData
import com.mashup.twotoo.presenter.home.di.HomeScope
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.model.HomeSideEffect
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ToastText
import model.commit.request.CommitRequestDomainModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import usecase.commit.CreateCommitUseCase
import usecase.view.GetViewHomeUseCase
import javax.inject.Inject

/**
 * @Created by 김현국 2023/06/23
 */

@HomeScope
class HomeViewModel @Inject constructor(
    private val getHomeViewUseCase: GetViewHomeUseCase,
    private val createCommitUseCase: CreateCommitUseCase,
) : ViewModel(), ContainerHost<ChallengeStateTypeUiModel, HomeSideEffect> {

    override val container: Container<ChallengeStateTypeUiModel, HomeSideEffect> = container(OngoingChallengeUiModel.default)

    fun getHomeViewChallenge() = intent {
        getHomeViewUseCase().onSuccess {
        }.onFailure {
            postSideEffect(
                HomeSideEffect.Toast(
                    ToastText.LoadHomeFail,
                ),
            )
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
                createCommitUseCase(
                    commitRequestDomainModel = CommitRequestDomainModel(
                        text = bottomSheetData.text,
                        img = bottomSheetData.image.toString(),
                    ),
                ).onSuccess { domainModel ->
                    postSideEffect(
                        HomeSideEffect.DismissBottomSheet,
                    )
                    postSideEffect(
                        HomeSideEffect.Toast(
                            ToastText.CommitSuccess,
                        ),
                    )
                    // TODO GET VIEW API 재호출
                }
                    .onFailure {
                        postSideEffect(
                            HomeSideEffect.DismissBottomSheet,
                        )
                        postSideEffect(
                            HomeSideEffect.Toast(
                                ToastText.CommitFail,
                            ),
                        )
                    }
            }
            is BottomSheetData.ShotData -> {
                // 서버 데이터 전송

                // toast sideEffect
                postSideEffect(
                    HomeSideEffect.Toast(
                        ToastText.ShotSuccess,
                    ),
                )
            }
            is BottomSheetData.CheerData -> {
                // 서버 데이터 전송

                // toast sideEffect
                postSideEffect(
                    HomeSideEffect.Toast(
                        ToastText.CheerSuccess,
                    ),
                )
            }
        }
    }
}
