package com.mashup.twotoo.presenter.designsystem.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.designsystem.theme.Font
import com.mashup.twotoo.presenter.designsystem.theme.TwotooBrown
import com.mashup.twotoo.presenter.designsystem.theme.TwotooGray
import com.mashup.twotoo.presenter.designsystem.theme.White

@Composable
fun TwoTooTextButton(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(57.dp)
        .padding(horizontal = 30.dp),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = if (enabled) {
            ButtonDefaults.buttonColors(containerColor = TwotooBrown)
        } else {
            ButtonDefaults.buttonColors(containerColor = TwotooGray)
        },
        onClick = { onClick() },
    ) {
        Text(
            text = text,
            style = TextStyle(fontFamily = Font.Omyuda, fontSize = 20.sp, color = White),
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TextButtonEnablePreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        TwoTooTextButton(text = "확인", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TextButtonDisabledPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        TwoTooTextButton(
            text = "인증하기",
            enabled = false,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
    }
}
