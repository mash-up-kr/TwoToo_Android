package com.mashup.twotoo.presenter.designsystem.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.theme.*

@Composable
fun TwoTooTextButton(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(57.dp)
        .padding(horizontal = 30.dp),
    enabled: Boolean = true,
    shape: Shape = TwoTooTheme.shape.medium,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = if (enabled) {
            ButtonDefaults.buttonColors(containerColor = TwoTooTheme.color.mainBrown)
        } else {
            ButtonDefaults.buttonColors(containerColor = TwoTooTheme.color.gray400)
        },
        onClick = {
            if (enabled) {
                onClick()
            }
        },
    ) {
        Text(
            text = text,
            style = TwoTooTheme.typography.headLineNormal20,
            color = TwoTooTheme.color.mainWhite,
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun TextButtonEnablePreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        TwoTooTextButton(text = "확인", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun TextButtonDisabledPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        TwoTooTextButton(
            text = "인증하기",
            enabled = false,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
    }
}
