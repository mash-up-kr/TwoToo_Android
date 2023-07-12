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
    navigateToHistory: () -> Unit,
    navigateToCreateChallenge: () -> Unit,
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
        )
    }
}

@Stable
@OptIn(ExperimentalMaterial3Api::class)
class HomeSideEffectHandler(
    val context: Context,
    val bottomSheetState: SheetState,
    val snackbarHostState: SnackbarHostState,
    private val coroutineScope: CoroutineScope,
    private val navigateToHistory: () -> Unit,
    private val navigateToCreateChallenge: () -> Unit,
) {
    var isBottomSheetVisible by mutableStateOf(false)
    var bottomSheetType by mutableStateOf<BottomSheetType>(BottomSheetType.Authenticate())

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
                            ToastText.LoadHomeFail->{
                                context.getString(R.string.toast_message_load_home_fail)
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
            is HomeSideEffect.OpenToHelp -> {
            }
            is HomeSideEffect.NavigateToChallengeDetail -> {
                navigateToHistory()
            }
            is HomeSideEffect.NavigationToCreateChallenge -> {
                navigateToCreateChallenge()
            }
            is HomeSideEffect.DismissBottomSheet -> {
                isBottomSheetVisible = !isBottomSheetVisible
            }
        }
    }

    fun onDismiss() {
        coroutineScope.launch {
            bottomSheetState.hide()
        }.invokeOnCompletion {
            if (!bottomSheetState.isVisible) {
                isBottomSheetVisible = !isBottomSheetVisible
            }
        }
    }
}
