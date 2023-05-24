package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.Authenticate
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.Shot
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoTooBottomSheet(
    type: BottomSheetType,
    button: @Composable (Modifier) -> Unit,
    onDismiss: () -> Unit,
    bottomSheetState: SheetState = rememberModalBottomSheetState(),
) {
    TwoTooBottomSheetImpl(
        bottomSheetState = bottomSheetState,
        type = type,
        button = button,
        onDismiss = onDismiss,
    )
}

@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun TwoTooBottomSheetImpl(
    bottomSheetState: SheetState,
    type: BottomSheetType,
    onDismiss: () -> Unit,
    button: @Composable (Modifier) -> Unit,
) {
    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = onDismiss,
    ) {
        when (type) {
            is Authenticate -> {
                val titleText = stringResource(id = type.title)
                AuthenticateContent(titleText = titleText, button = button)
            }
            is Shot -> {
                val titleText = stringResource(id = type.title)
                ShotList(
                    titleText = titleText,
                    button = button,
                    textList = type.shotTextList,
                )
            }
        }
    }
}

@Composable
fun TestButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text("버튼입니다.")
    }
}

/**
 * 직접 실행하거나, Interactive Mode에서 확인이 가능합니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "인증하기 프리뷰")
@Composable
fun AuthenticateSheet() {
    TwoTooTheme {
        TwoTooBottomSheet(
            type = Authenticate(title = R.string.bottomSheetAuthenticate),
            button = {
                TestButton(Modifier, {})
            },
            onDismiss = {},
        )
    }
}

/**
 * 직접 실행하거나, Interactive Mode에서 확인이 가능합니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "찌르기 프리뷰")
@Composable
fun ShotSheet() {
    TwoTooTheme {
        TwoTooBottomSheet(
            type = Shot(
                title = R.string.bottomSheetShot,
                shotTextList = persistentListOf(
                    "언제까지 쉬고 있을 거야? 얼른 해야지?",
                    "잘하구 있어 좀만 더 화이팅하자!!!",
                    "이거 보면 빨리 한다! 실시!",
                ),
            ),
            button = {
                TestButton(Modifier, {})
            },
            onDismiss = {},
        )
    }
}

/**
 * 직접 실행하거나, Interactive Mode에서 확인이 가능합니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OpenAuthenticate() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            var isBottomSheetVisible by rememberSaveable {
                mutableStateOf(false)
            }
            val bottomSheetState = rememberModalBottomSheetState(true)
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {
                    isBottomSheetVisible = !isBottomSheetVisible
                },
            ) {
                Text("열기 / 닫기")
            }
            if (isBottomSheetVisible) {
                TwoTooBottomSheet(
                    bottomSheetState = bottomSheetState,
                    button = { modifier ->
                        TestButton(modifier = Modifier.then(modifier), {})
                    },
                    type = Authenticate(title = R.string.bottomSheetAuthenticate),
                    onDismiss = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                isBottomSheetVisible = !isBottomSheetVisible
                            }
                        }
                    },
                )
            }
        }
    }
}

/**
 * 직접 실행하거나, Interactive Mode에서 확인이 가능합니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OpenShot() {
    TwoTooTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            var isBottomSheetVisible by rememberSaveable {
                mutableStateOf(false)
            }
            val bottomSheetState = rememberModalBottomSheetState(true)
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {
                    isBottomSheetVisible = !isBottomSheetVisible
                },
            ) {
                Text("열기 / 닫기")
            }
            if (isBottomSheetVisible) {
                TwoTooBottomSheet(
                    bottomSheetState = bottomSheetState,
                    button = {
                        /*
                        Custom Button Composable이 들어갑니다.
                         */
                        TestButton(modifier = Modifier, {})
                    },
                    type = Shot(
                        title = R.string.bottomSheetShot,
                        shotTextList = persistentListOf(
                            "언제까지 쉬고 있을 거야? 얼른 해야지?",
                            "잘하구 있어 좀만 더 화이팅하자!!!",
                            "이거 보면 빨리 한다! 실시!",
                        ),
                    ),
                    onDismiss = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                isBottomSheetVisible = !isBottomSheetVisible
                            }
                        }
                    },
                )
            }
        }
    }
}
