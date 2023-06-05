package com.mashup.twotoo.presenter.designsystem.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooBlack

@Composable
fun TwoTooOutLineTextButton(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(57.dp)
        .padding(horizontal = 30.dp),
    onClick: () -> Unit = {},

) {
    OutlinedButton(
        modifier = modifier,
        shape = TwoTooTheme.shape.medium,
        onClick = { onClick() },
        border = BorderStroke(
            width = 1.dp,
            color = TwotooBlack,
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color(0x00000000),
        ),
    ) {
        Text(
            text = text,
            style = TwoTooTheme.typography.headLineNormal20,
            color = TwoTooTheme.color.mainBrown,
        )
    }
}

@Preview
@Composable
private fun OutLineTextButtonPreview() {
    TwoTooOutLineTextButton(text = "인증하기")
}
