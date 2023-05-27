package com.mashup.twotoo.presenter.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.twotoo.presenter.R

@Composable
fun CommDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        content
    }
}

@Composable
fun CommDialogScreen(
    @StringRes title: Int,
    @StringRes desc: Int,
    @StringRes btnOk: Int,
    @StringRes btnCancel: Int = 0,
) {
    Column(
        modifier = Modifier
            .background(
                Color.White,
                shape = RoundedCornerShape(17.dp),
            )
            .padding(horizontal = 30.dp, vertical = 24.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = title),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = desc),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
        )
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            if (btnCancel != 0) {
                TextButton(
                    onClick = { },
                    contentPadding = PaddingValues(12.dp),
                ) {
                    Text(
                        text = stringResource(id = btnCancel),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            TextButton(
                onClick = { },
                contentPadding = PaddingValues(12.dp),
            ) {
                Text(
                    text = stringResource(id = btnOk),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    CommDialogScreen(
        R.string.all_auth_title,
        R.string.all_auth_desc,
        R.string.cheer_up,
        R.string.cheer_up_cancel,
    )
}
