package com.mashup.twotoo.presenter.createChallenge

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.createChallenge.model.ChallengeInfoModel
import com.mashup.twotoo.presenter.createChallenge.model.toDomainModel
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.HomeChallengeInfoModel
import com.mashup.twotoo.presenter.util.DateFormatter
import com.mashup.twotoo.presenter.util.DateFormatter.convertIsoTimeToString
import kotlinx.coroutines.delay
import model.challenge.request.ApproveChallengeRequestDomainModel
import model.challenge.request.ChallengeNoRequestDomainModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.ApproveChallengeUseCase
import usecase.challenge.CreateChallengeUseCase
import usecase.challenge.QuiteChallengeUseCase
import javax.inject.Inject

class CreateChallengeViewModel@Inject constructor(
    private val approveChallengeUseCase: ApproveChallengeUseCase,
    private val createChallengeUseCase: CreateChallengeUseCase,
    private val quiteChallengeUseCase: QuiteChallengeUseCase,
) : ViewModel(), ContainerHost<ChallengeInfoModel, CreateChallengeSideEffect> {
    override val container = container<ChallengeInfoModel, CreateChallengeSideEffect>(ChallengeInfoModel())

    fun setIsBackState() = intent {
        reduce {
            state.copy(isBack = true)
        }
    }

    fun initChallengeStep(challengeInfo: HomeChallengeInfoModel) = intent {
        if (state.isBack) {
            return@intent
        }
        reduce {
            val step = when (state.homeState) {
                BeforeChallengeState.EMPTY.name, BeforeChallengeState.TERMINATION.name -> {
                    1
                }
                else -> {
                    setHomeChallengeInfo(challengeInfo)
                    3
                }
            }
            state.copy(challengeNo = challengeInfo.challengeNo, currentStep = step)
        }
    }

    fun updateCurrentStep(num: Int) = intent {
        reduce {
            state.copy(
                currentStep = state.currentStep + num,
            )
        }
    }

    fun setCreateChallengeInfo(challengeInfo: ChallengeInfoModel) = intent {
        reduce {
            when (state.currentStep) {
                1 -> { state.copy(
                    challengeName = challengeInfo.challengeName,
                    startDate = challengeInfo.startDate,
                    endDate = challengeInfo.endDate,
                    period = challengeInfo.period,
                )
                }
                2 -> {
                    state.copy(
                        challengeInfo = challengeInfo.challengeInfo,
                    )
                }
                else -> {
                    state.copy(
                        selectFlowerName = challengeInfo.selectFlowerName,
                    )
                }
            }
        }
    }

    private fun setHomeChallengeInfo(homeChallengeInfoModel: HomeChallengeInfoModel) = intent {
        Log.d(TAG, "setHomeChallengeInfo: $homeChallengeInfoModel")
        reduce {
            state.copy(
                challengeName = homeChallengeInfoModel.name,
                challengeInfo = homeChallengeInfoModel.description,
                period = DateFormatter.formatDateRange(
                    convertIsoTimeToString(homeChallengeInfoModel.startDate) ?: "",
                    convertIsoTimeToString(homeChallengeInfoModel.endDate) ?: "",
                ),
            )
        }
    }

    fun createChallenge() = intent {
        createChallengeUseCase(state.toDomainModel()).onSuccess {
            postSideEffect(CreateChallengeSideEffect.NavigateToSuccessCreate)
        }.onFailure {
            postSideEffect(CreateChallengeSideEffect.ToastMessage("챌린지 생성에 실패했어요ㅠ"))
        }
    }

    fun approveChallenge(challengeNo: Int, selectFlower: String) = intent {
        approveChallengeUseCase(
            ChallengeNoRequestDomainModel(challengeNo),
            ApproveChallengeRequestDomainModel(selectFlower),
        ).onSuccess {
            postSideEffect(CreateChallengeSideEffect.NavigateToHome)
        }.onFailure {
        }
    }

    fun deleteChallenge(challengeNo: Int) = intent {
        quiteChallengeUseCase(
            ChallengeNoRequestDomainModel(challengeNo = challengeNo),
        ).onSuccess {
            postSideEffect(CreateChallengeSideEffect.DismissDialog)
            delay(100)
            postSideEffect(CreateChallengeSideEffect.NavigateToHome)
        }.onFailure {
            postSideEffect(CreateChallengeSideEffect.DismissDialog)
            delay(100)
            postSideEffect(CreateChallengeSideEffect.ToastMessage("챌린지 삭제에 실패했어요ㅠ"))
        }
    }

    fun setHomeState(homeState: String) = intent {
        reduce {
            state.copy(
                homeState = homeState,
            )
        }
    }

    fun setDialogVisibility(visibility: Boolean) = intent {
        reduce {
            state.copy(
                dialogVisibility = visibility,
            )
        }
    }
}
