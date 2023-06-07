package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetData.CheerData
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetData.ShotData
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.SendType
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.SendType.Cheer
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.SendType.Shot
import com.mashup.twotoo.presenter.designsystem.component.textfield.TwoTooTextField
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun SendMsgBottomSheetContent(
    type: SendType,
    button: @Composable (Modifier, BottomSheetData) -> Unit,
    modifier: Modifier = Modifier,
) {
    val titleText = stringResource(id = type.title)
    val textHint = stringResource(id = type.textHint)

    var textFieldState by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = modifier.fillMaxWidth().imePadding().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(titleText = titleText)
        Spacer(modifier = Modifier.height(20.5.dp))
        TwoTooTextField(
            text = textFieldState,
            updateText = { textFieldState = it },
            textHint = textHint,
        )
        if (type is Cheer) {
            Spacer(modifier = Modifier.height(9.dp))
            Text(text = stringResource(id = R.string.bottomSheetCheerWarningHint))
            Spacer(modifier = Modifier.height(9.dp))
        } else {
            Spacer(modifier = Modifier.height(24.5.dp))
        }
        button(
            Modifier,
            when (type) {
                is Shot -> {
                    ShotData(text = textFieldState)
                }
                is Cheer -> {
                    CheerData(text = textFieldState)
                }
            },

        )
    }
}

@Preview
@Composable
fun PreviewShotList() {
    TwoTooTheme {
        SendMsgBottomSheetContent(
            type = Shot(),
            button = { _, _ -> },
        )
    }
}
