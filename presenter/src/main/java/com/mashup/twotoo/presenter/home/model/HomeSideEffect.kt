package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/07/05
 */
sealed class HomeSideEffect {
    data class Toast(val text: ToastText) : HomeSideEffect()
    object OpenToShotBottomSheet : HomeSideEffect()
    object OpenToAuthBottomSheet : HomeSideEffect()
    data class NavigateToChallengeDetail(val challengeNo: Int, val from: String) : HomeSideEffect()
    object OpenToCheerBottomSheet : HomeSideEffect()
    object OpenToHelp : HomeSideEffect()
    data class NavigationToCreateChallenge(val homeState: BeforeChallengeState, val challengeInfo: HomeChallengeInfoModel) : HomeSideEffect()
    data class NavigateToGarden(val isCompleted: Boolean) : HomeSideEffect()
    object DismissBottomSheet : HomeSideEffect()
    data class OpenHomeDialog(val type: HomeDialogType) : HomeSideEffect()
    object RemoveVisibilityCheerDialog : HomeSideEffect()
    object RemoveVisibilityCompleteDialog : HomeSideEffect()
    object SetInVisibleCheerDialog : HomeSideEffect()
    object SetInVisibleCompleteDialog : HomeSideEffect()
    object CallViewHomeApi : HomeSideEffect()

}

enum class HomeDialogType {
    Cheer, Bloom, DoNotBloom
}
enum class ToastText {

    CommitSuccess, CommitFail, ShotSuccess, CheerSuccess, CheerFail, LoadHomeFail, FinishFail, ShotFail, ShotInvalid
}
