package com.mashup.twotoo.presenter.mypage

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.home.model.HomeGoalCountUiModel
import com.mashup.twotoo.presenter.mypage.model.MyPageItem
import util.onSuccess
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.user.DeletePartnerUseCase
import usecase.user.GetUserInfoUseCase
import usecase.user.RemoveUserInfoUseCase
import usecase.user.SignOutUseCase
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val deletePartnerUseCase: DeletePartnerUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val removeUserInfoUseCase: RemoveUserInfoUseCase
) : ContainerHost<UserState, UserSideEffect>, ViewModel() {
    override val container: Container<UserState, UserSideEffect> = container(
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

    fun deletePartner() = intent {
        deletePartnerUseCase().onSuccess { isSuccess ->
            if (isSuccess) {
                navigateToRoute(MyPageItem.DeletePartner.route)
            }
        }.onFailure {
        }
    }

    fun signOut() = intent {
        signOutUseCase().onSuccess { isSuccess ->
            if (isSuccess) {
                removeUserInfoUseCase()
                postSideEffect(UserSideEffect.OpenSignOutSuccessDialog)
            }
        }.onFailure {
        }
    }

    fun openSignOutConfirmDialog() = intent {
        postSideEffect(UserSideEffect.OpenSignOutConfirmDialog)
    }

    fun openDeletePartnerConfirmDialog() = intent {
        postSideEffect(UserSideEffect.OpenDeletePartnerConfirmDialog)
    }

    fun navigateToRoute(route: String) = intent {
        postSideEffect(UserSideEffect.NavigateToRoute(route))
    }
}
