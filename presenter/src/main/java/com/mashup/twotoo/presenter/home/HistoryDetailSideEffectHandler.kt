package com.mashup.twotoo.presenter.home

import android.content.Context
import androidx.compose.material.SnackbarHostState
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
import com.mashup.twotoo.presenter.home.model.ToastTextForHistoryDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberHistoryDetailSideEffectHandler(
    context: Context = LocalContext.current,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): HistoryDetailSideEffectHandler {
    return remember(
        context,
        snackbarHostState,
        coroutineScope,
    ) {
        HistoryDetailSideEffectHandler(
            context = context,
            snackbarHostState = snackbarHostState,
            coroutineScope = coroutineScope,
        )
    }
}

@Stable
class HistoryDetailSideEffectHandler(
    val context: Context,
    val snackbarHostState: SnackbarHostState,
    val coroutineScope: CoroutineScope,
) {
    var isBottomSheetVisible by mutableStateOf(false)
    var bottomSheetType by mutableStateOf<BottomSheetType>(BottomSheetType.Authenticate())

    fun handleSideEffect(sideEffect: HomeSideEffect) {
        when (sideEffect) {
            is HomeSideEffect.ToastForHistoryDetail -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        when (sideEffect.text) {
                            ToastTextForHistoryDetail.CheerSuccess -> {
                                context.getString(R.string.toast_message_cheer_success)
                            }
                            ToastTextForHistoryDetail.CheerFail -> {
                                context.getString(R.string.toast_message_cheer_fail)
                            }
                        },
                    )
                }
            }
            is HomeSideEffect.OpenToCheerBottomSheet -> {
                bottomSheetType = BottomSheetType.SendType.Cheer()
                isBottomSheetVisible = !isBottomSheetVisible
            }
            is HomeSideEffect.DismissBottomSheet -> {
                onDismiss()
            }
            else -> {}
        }
    }
    fun onDismiss() {
        isBottomSheetVisible = false
    }
}
