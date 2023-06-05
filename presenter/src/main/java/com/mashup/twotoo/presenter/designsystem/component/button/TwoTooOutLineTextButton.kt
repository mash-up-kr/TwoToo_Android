package com.mashup.twotoo.presenter.designsystem.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.designsystem.theme.Font
import com.mashup.twotoo.presenter.designsystem.theme.FontBlack
import com.mashup.twotoo.presenter.designsystem.theme.TwotooBrown

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
        shape = RoundedCornerShape(20.dp),
        onClick = { onClick() },
        border = BorderStroke(
            width = 1.dp,
            color = FontBlack,
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color(0x00000000),
        ),
    ) {
        Text(
            text = text,
            style = TextStyle(fontFamily = Font.Omyuda, fontSize = 20.sp, color = TwotooBrown),
        )
    }
}

@Preview
@Composable
fun OutLineTextButtonPreview() {
    TwoTooOutLineTextButton(text = "인증하기")
}
