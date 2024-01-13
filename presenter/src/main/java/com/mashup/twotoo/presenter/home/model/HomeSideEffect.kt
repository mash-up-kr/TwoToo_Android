package com.mashup.twotoo.presenter.home.model

import com.mashup.twotoo.presenter.model.FlowerName

/**
 * @Created by 김현국 2023/07/05
 */
sealed class HomeSideEffect {
    data class Toast(val text: ToastText) : HomeSideEffect()

    data class ToastForHistoryDetail(val text: ToastTextForHistoryDetail) : HomeSideEffect()
    object OpenToShotBottomSheet : HomeSideEffect()
    object OpenToAuthBottomSheet : HomeSideEffect()
    data class NavigateToChallengeDetail(val challengeNo: Int, val from: String) : HomeSideEffect()

    object NavigateToHistoryDetailWithHomeViewModel : HomeSideEffect()
    object OpenToCheerBottomSheet : HomeSideEffect()
    object OpenToHelp : HomeSideEffect()
    data class NavigationToCreateChallenge(val homeState: BeforeChallengeState, val challengeInfo: HomeChallengeInfoModel) : HomeSideEffect()
    data class NavigateToGarden(val isCompleted: Boolean) : HomeSideEffect()
    object DismissBottomSheet : HomeSideEffect()
    data class OpenHomeDialog(val type: HomeDialogType) : HomeSideEffect()
    object RemoveVisibilityCompleteDialog : HomeSideEffect()
    object SetInVisibleCompleteDialog : HomeSideEffect()
    object CallViewHomeApi : HomeSideEffect()
    data class OpenToFlowerLanguageDialog(val challengeNo: Int, val flowerName: FlowerName) : HomeSideEffect()
    data class OpenToCompleteChallengeDialog(val challengeInfo: HomeChallengeCompleteUiModel): HomeSideEffect()
}

enum class HomeDialogType {
    Cheer, DoNotBloom
}
enum class ToastText {
    CommitSuccess, CommitFail, ShotSuccess, LoadHomeFail, FinishFail, ShotFail, ShotInvalid
}

enum class ToastTextForHistoryDetail {
    CheerSuccess, CheerFail
}
