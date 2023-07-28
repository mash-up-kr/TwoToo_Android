package com.mashup.twotoo.presenter.home

import android.content.Context
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType
import com.mashup.twotoo.presenter.designsystem.component.dialog.DialogContent
import com.mashup.twotoo.presenter.home.model.HomeDialogType
import com.mashup.twotoo.presenter.home.model.HomeSideEffect
import com.mashup.twotoo.presenter.home.model.ToastText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberHomeSideEffectHandler(
    context: Context = LocalContext.current,
    bottomSheetState: SheetState = rememberModalBottomSheetState(true),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navigateToHistory: (Int) -> Unit,
    navigateToCreateChallenge: () -> Unit,
    openCheerBottomSheet: () -> Unit,
    onClickCompleteDialogConfirmButton: () -> Unit,
    onClickCheerDialogNegativeButton: () -> Unit,
    removeVisibilityCheerDialog: () -> Unit,
    removeVisibilityCompleteDialog: () -> Unit,
    callViewHomeApi: () -> Unit,
    setInvisibleCheerDialog: () -> Unit,
    setInvisibleCompleteDialog: () -> Unit,
): HomeSideEffectHandler {
    return remember(
        context,
        bottomSheetState,
        snackbarHostState,
        coroutineScope,
    ) {
        HomeSideEffectHandler(
            context = context,
            bottomSheetState = bottomSheetState,
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope,
            navigateToHistory = navigateToHistory,
            navigateToCreateChallenge = navigateToCreateChallenge,
            openCheerBottomSheet = openCheerBottomSheet,
            onClickCompleteDialogConfirmButton = onClickCompleteDialogConfirmButton,
            onClickCheerDialogNegativeButton = onClickCheerDialogNegativeButton,
            removeVisibilityCheerDialog = removeVisibilityCheerDialog,
            removeVisibilityCompleteDialog = removeVisibilityCompleteDialog,
            callViewHomeApi = callViewHomeApi,
            setInvisibleCheerDialog = setInvisibleCheerDialog,
            setInvisibleCompleteDialog = setInvisibleCompleteDialog,
        )
    }
}

@Stable
@OptIn(ExperimentalMaterial3Api::class)
class HomeSideEffectHandler(
    val context: Context,
    val bottomSheetState: SheetState,
    val snackbarHostState: SnackbarHostState,
    val coroutineScope: CoroutineScope,
    private val navigateToHistory: (Int) -> Unit,
    private val navigateToCreateChallenge: () -> Unit,
    private val openCheerBottomSheet: () -> Unit,
    private val onClickCompleteDialogConfirmButton: () -> Unit,
    private val onClickCheerDialogNegativeButton: () -> Unit,
    private val removeVisibilityCheerDialog: () -> Unit,
    private val removeVisibilityCompleteDialog: () -> Unit,
    private val callViewHomeApi: () -> Unit,
    private val setInvisibleCheerDialog: () -> Unit,
    private val setInvisibleCompleteDialog: () -> Unit,
) {
    var isBottomSheetVisible by mutableStateOf(false)
    var bottomSheetType by mutableStateOf<BottomSheetType>(BottomSheetType.Authenticate())

    var isHomeDialogVisible by mutableStateOf(false)
    var homeDialogType by mutableStateOf(DialogContent.default)

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
                navigateToHistory(sideEffect.challengeNo)
            }
            is HomeSideEffect.NavigationToCreateChallenge -> {
                navigateToCreateChallenge()
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
            is HomeSideEffect.SetInVisibleCheerDialog -> {
                setInvisibleCheerDialog()
            }
            is HomeSideEffect.SetInVisibleCompleteDialog -> {
                setInvisibleCompleteDialog()
            }
        }
    }

    fun onDismiss() {
        isBottomSheetVisible = false
    }

    private fun handleDialog(type: HomeDialogType) {
        when (type) {
            HomeDialogType.Cheer -> {
                homeDialogType = DialogContent.createHomeBothAuthDialogContent(
                    negativeAction = {
                        onClickCheerDialogNegativeButton()
                        onDismissHomeDialog()
                    },
                    positiveAction = {
                        onDismissHomeDialog()
                        openCheerBottomSheet()
                    },
                )
                isHomeDialogVisible = true
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
}
