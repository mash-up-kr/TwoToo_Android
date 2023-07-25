package com.mashup.twotoo.presenter.mypage

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.home.model.HomeGoalCountUiModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.user.GetUserInfoUseCase
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : ContainerHost<UserState, Nothing>, ViewModel() {
    override val container: Container<UserState, Nothing> = container(
        UserState(),
    )

    fun getUserInfo() = intent {
        getUserInfoUseCase().onSuccess { userInfo ->
            reduce {
                state.copy(
                    homeGoalCountUiModel = HomeGoalCountUiModel(
                        userInfo.partnerNickname,
                        userInfo.nickname,
                        userInfo.totalChallengeCount,
                    ),
                )
            }
        }
    }
}
