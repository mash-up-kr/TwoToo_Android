package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/07/05
 */
sealed class HomeSideEffect {
    data class Toast(val text: ToastText) : HomeSideEffect()
    object OpenToShotBottomSheet : HomeSideEffect()
    object OpenToAuthBottomSheet : HomeSideEffect()
    data class NavigateToChallengeDetail(val challengeNo: Int) : HomeSideEffect()
    object OpenToCheerBottomSheet : HomeSideEffect()
    object OpenToHelp : HomeSideEffect()
    object NavigationToCreateChallenge : HomeSideEffect()
    object DismissBottomSheet : HomeSideEffect()
    data class OpenHomeDialog(val type: HomeDialogType) : HomeSideEffect()
    object RemoveVisibilityCheerDialog : HomeSideEffect()
    object RemoveVisibilityCompleteDialog : HomeSideEffect()
    object CallViewHomeApi : HomeSideEffect()
}

enum class HomeDialogType {
    Cheer, Bloom, DoNotBloom
}
enum class ToastText {

    CommitSuccess, CommitFail, ShotSuccess, CheerSuccess, CheerFail, LoadHomeFail, FinishFail, ShotFail, ShotInvalid
}
