package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageViewWithSetter
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.Authenticate
import com.mashup.twotoo.presenter.designsystem.component.textfield.TwoTooTextField
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.util.addFocusCleaner
import com.mashup.twotoo.presenter.util.keyboardAsState

@OptIn(ExperimentalMotionApi::class)
@Composable
fun AuthenticateContent(
    type: Authenticate,
    onClickPlusButton: () -> Unit,
    button: @Composable (Modifier, BottomSheetData) -> Unit,
    imageUri: Uri? = null,
) {
    val titleText = stringResource(id = type.title)
    val textHint = stringResource(id = type.textHint)
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.bottom_sheet)
            .readBytes()
            .decodeToString()
    }

    var animateSwitch by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (animateSwitch) 1f else 0f,
        animationSpec = tween(),
        label = stringResource(id = R.string.bottomSheetAuthenticateAnimation),
    )
    val focusRequester by remember { mutableStateOf(FocusRequester()) }
    val focusManager = LocalFocusManager.current

    val keyBoardOpenState by keyboardAsState()
    LaunchedEffect(keyBoardOpenState) {
        if (!keyBoardOpenState) {
            animateSwitch = false
            focusManager.clearFocus()
        }
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .imePadding().addFocusCleaner(focusManager),
    ) {
        Header(
            modifier = Modifier.layoutId("headerText"),
            titleText = titleText,
        )
        TwoTooImageViewWithSetter(
            modifier = Modifier
                .layoutId("imageView")
                .aspectRatio(1f)
                .clip(
                    TwoTooTheme.shape.extraSmall,
                ),
            imageUri = imageUri,
            onClickPlusButton = {
                onClickPlusButton()
            },
            previewPlaceholder = R.drawable.empty_image_color_placeholder,
            failurePlaceHolder = {},
            loadingPlaceHolder = {},
        )

        var textFieldState by rememberSaveable {
            mutableStateOf("")
        }
        TwoTooTextField(
            modifier = Modifier.layoutId("textField").focusRequester(
                focusRequester = focusRequester,
            ).onFocusChanged {
                animateSwitch = it.isFocused
            },
            text = textFieldState,
            textHint = textHint,
            updateText = { textFieldState = it },
        )
        button(
            Modifier.layoutId("button"),
            BottomSheetData.AuthenticateData(
                image = imageUri,
                text = textFieldState,
            ),
        )
    }
}
