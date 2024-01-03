package com.mashup.twotoo.presenter.home

import android.content.Context
import android.util.Log
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType
import com.mashup.twotoo.presenter.designsystem.component.dialog.DialogContent
import com.mashup.twotoo.presenter.home.model.*
import com.mashup.twotoo.presenter.model.FlowerName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberHomeSideEffectHandler(
    context: Context = LocalContext.current,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navigateToHistory: (Int, String) -> Unit,
    navigateToStandAloneHistoryDetail: (Int) -> Unit,
    navigateToCreateChallenge: (BeforeChallengeState, HomeChallengeInfoModel) -> Unit,
    onClickCompleteDialogConfirmButton: () -> Unit,
    removeVisibilityCheerDialog: () -> Unit,
    removeVisibilityCompleteDialog: () -> Unit,
    callViewHomeApi: () -> Unit,
    setInvisibleCompleteDialog: () -> Unit,
    navigateToGarden: (Boolean) -> Unit,
    openToFlowerLanguageDialog: (Int, FlowerName) -> Unit,
): HomeSideEffectHandler {
    return remember(
        context,
        snackbarHostState,
        coroutineScope,
    ) {
        HomeSideEffectHandler(
            context = context,
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope,
            navigateToHistory = navigateToHistory,
            navigateToStandAloneHistoryDetail = navigateToStandAloneHistoryDetail,
            navigateToCreateChallenge = navigateToCreateChallenge,
            onClickCompleteDialogConfirmButton = onClickCompleteDialogConfirmButton,
            removeVisibilityCheerDialog = removeVisibilityCheerDialog,
            removeVisibilityCompleteDialog = removeVisibilityCompleteDialog,
            callViewHomeApi = callViewHomeApi,
            setInvisibleCompleteDialog = setInvisibleCompleteDialog,
            navigateToGarden = navigateToGarden,
            openToFlowerLanguageDialog = openToFlowerLanguageDialog,
        )
    }
}

@Stable
class HomeSideEffectHandler(
    val context: Context,
    val snackbarHostState: SnackbarHostState,
    val coroutineScope: CoroutineScope,
    private val navigateToStandAloneHistoryDetail: (Int) -> Unit,
    private val navigateToHistory: (Int, from: String) -> Unit,
    private val navigateToCreateChallenge: (BeforeChallengeState, HomeChallengeInfoModel) -> Unit,
    private val onClickCompleteDialogConfirmButton: () -> Unit,
    private val removeVisibilityCheerDialog: () -> Unit,
    private val removeVisibilityCompleteDialog: () -> Unit,
    private val callViewHomeApi: () -> Unit,
    private val setInvisibleCompleteDialog: () -> Unit,
    private val navigateToGarden: (Boolean) -> Unit,
    private val openToFlowerLanguageDialog: (Int, FlowerName) -> Unit,
) {
    var isBottomSheetVisible by mutableStateOf(false)
    var bottomSheetType by mutableStateOf<BottomSheetType>(BottomSheetType.Authenticate())

    var isHomeDialogVisible by mutableStateOf(false)
    var isFlowerLangDialogVisible by mutableStateOf(false)
    var homeDialogType by mutableStateOf(DialogContent.default)
    var flowerLanguageModel by mutableStateOf(FlowerLanguageUiModel())

    fun handleSideEffect(sideEffect: HomeSideEffect) {
        when (sideEffect) {
            is HomeSideEffect.Toast -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        when (sideEffect.text) {
                            ToastText.CommitSuccess -> {
                                context.getString(R.string.toast_message_commit_success)
                            }

                            ToastText.CommitFail -> {
                                context.getString(R.string.toast_message_commit_fail)
                            }

                            ToastText.ShotSuccess -> {
                                context.getString(R.string.toast_message_shot_success)
                            }

                            ToastText.CheerSuccess -> {
                                context.getString(R.string.toast_message_cheer_success)
                            }

                            ToastText.LoadHomeFail -> {
                                context.getString(R.string.toast_message_load_home_fail)
                            }

                            ToastText.FinishFail -> {
                                context.getString(R.string.toast_message_finish_challenge_fail)
                            }

                            ToastText.CheerFail -> {
                                context.getString(R.string.toast_message_cheer_fail)
                            }

                            ToastText.ShotFail -> {
                                context.getString(R.string.toast_message_shot_fail)
                            }

                            ToastText.ShotInvalid -> {
                                context.getString(R.string.toast_message_shot_invalid)
                            }
                        },
                    )
                }
            }

            is HomeSideEffect.NavigateToStandAloneHistoryDetail -> {
                navigateToStandAloneHistoryDetail(sideEffect.commitNo)
            }

            is HomeSideEffect.OpenToShotBottomSheet -> {
                bottomSheetType = BottomSheetType.SendType.Shot()
                isBottomSheetVisible = !isBottomSheetVisible
            }

            is HomeSideEffect.OpenToCheerBottomSheet -> {
                bottomSheetType = BottomSheetType.SendType.Cheer()
                isBottomSheetVisible = !isBottomSheetVisible
            }

            is HomeSideEffect.OpenToAuthBottomSheet -> {
                bottomSheetType = BottomSheetType.Authenticate()
                isBottomSheetVisible = !isBottomSheetVisible
            }

            is HomeSideEffect.OpenHomeDialog -> {
                handleDialog(sideEffect.type)
            }

            is HomeSideEffect.OpenToHelp -> {
            }

            is HomeSideEffect.NavigateToChallengeDetail -> {
                navigateToHistory(sideEffect.challengeNo, sideEffect.from)
            }

            is HomeSideEffect.NavigationToCreateChallenge -> {
                Log.d(TAG, "handleSideEffect:${sideEffect.challengeInfo} ")
                navigateToCreateChallenge(sideEffect.homeState, sideEffect.challengeInfo)
            }

            is HomeSideEffect.DismissBottomSheet -> {
                onDismiss()
            }

            is HomeSideEffect.RemoveVisibilityCompleteDialog -> {
                removeVisibilityCompleteDialog()
            }

            is HomeSideEffect.RemoveVisibilityCheerDialog -> {
                removeVisibilityCheerDialog()
            }

            is HomeSideEffect.CallViewHomeApi -> {
                callViewHomeApi()
            }

            is HomeSideEffect.SetInVisibleCompleteDialog -> {
                setInvisibleCompleteDialog()
            }

            is HomeSideEffect.NavigateToGarden -> {
                navigateToGarden(sideEffect.isCompleted)
            }

            is HomeSideEffect.OpenToFlowerLanguageDialog -> {
                flowerLanguageModel = FlowerLanguageUiModel(
                    challengeNo = sideEffect.challengeNo,
                    flowerName = sideEffect.flowerName,
                )
                isFlowerLangDialogVisible = true
            }
        }
    }

    fun onDismiss() {
        isBottomSheetVisible = false
    }

    private fun handleDialog(type: HomeDialogType) {
        when (type) {
            HomeDialogType.Cheer -> {
            }
            HomeDialogType.Bloom -> {
                homeDialogType = DialogContent.createHomeBloomBothDialogContent(
                    onConfirm = {
                        onClickCompleteDialogConfirmButton()
                        onDismissHomeDialog()
                    },
                )
                isHomeDialogVisible = true
            }

            HomeDialogType.DoNotBloom -> {
                homeDialogType = DialogContent.createHomeDoNotBloomBothDialogContent(
                    onConfirm = {
                        onClickCompleteDialogConfirmButton()
                        onDismissHomeDialog()
                    },
                )
                isHomeDialogVisible = true
            }
        }
    }

    private fun onDismissHomeDialog() {
        isHomeDialogVisible = false
    }

    fun onDismissFlowerLangDialog() {
        isFlowerLangDialogVisible = false
    }
}
