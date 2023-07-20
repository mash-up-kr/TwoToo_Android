package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageViewWithSetter
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.Authenticate
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.textfield.TwoTooTextField
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.util.addFocusCleaner
import com.mashup.twotoo.presenter.util.keyboardAsState

@Composable
fun AuthenticateContent(
    type: Authenticate,
    onClickPlusButton: () -> Unit,
    onClickButton: (BottomSheetData) -> Unit,
    imageUri: Uri? = null,
) {
    var animateSwitch by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyBoardOpenState by keyboardAsState()
    LaunchedEffect(keyBoardOpenState) {
        if (!keyBoardOpenState) {
            animateSwitch = false
            focusManager.clearFocus()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth().fillMaxHeight(0.8f).addFocusCleaner(focusManager).padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(
            titleText = stringResource(id = type.title),
        )
        AnimatedVisibility(
            visible = !animateSwitch,
            exit = fadeOut() + shrinkVertically(
                targetHeight = { 0 },
            ),
        ) {
            TwoTooImageViewWithSetter(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(
                        TwoTooTheme.shape.extraSmall,
                    ),
                imageUri = { imageUri ?: R.drawable.empty_image_color_placeholder },
                onClickPlusButton = {
                    onClickPlusButton()
                },
                previewPlaceholder = R.drawable.empty_image_color_placeholder,
                failurePlaceHolder = {},
                loadingPlaceHolder = {},
            )
        }

        var textFieldState by rememberSaveable {
            mutableStateOf("")
        }
        TextWrapper(
            modifier = Modifier
                .fillMaxWidth().height(85.dp),
            isFocus = {
                animateSwitch = it
            },
            text = { textFieldState },
            textHint = stringResource(id = type.textHint),
            updateText = {
                if (it.length <= 100) {
                    textFieldState = it
                }
            },
        )
        TwoTooTextButton(
            modifier = Modifier
                .fillMaxWidth().height(57.dp),
            text = stringResource(id = R.string.bottomSheetAuthenticateButtonText),
            onClick = {
                imageUri?.let { uri ->
                    onClickButton(
                        BottomSheetData.AuthenticateData(
                            image = uri,
                            text = textFieldState,
                        ),
                    )
                }
            },
        )
    }
}

@Composable
fun TextWrapper(
    isFocus: (Boolean) -> Unit,
    text: () -> String,
    textHint: String,
    updateText: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TwoTooTextField(
        modifier = modifier.onFocusChanged {
            isFocus(it.isFocused)
        },
        text = text.invoke(),
        textHint = textHint,
        updateText = {
            updateText(it)
        },
    )
}
