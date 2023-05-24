package com.mashup.twotoo.presenter.designsystem.component.button

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.designsystem.theme.Gray
import com.mashup.twotoo.presenter.designsystem.theme.SelectedIconColor
import com.mashup.twotoo.presenter.designsystem.theme.White

@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = 30.dp),
    round: Int = 8,
    color: Color = SelectedIconColor,
    textStyle: TextStyle = TextStyle(fontSize = 18.sp, color = White),
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(round.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        onClick = { onClick() }
    ) {
        Text(text = text, style =textStyle)
    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TextButtonPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        TextButton(text = "확인", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TextButtonGrayPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp),
            round = 0,
            text = "다음",
            color = Gray
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
    }
}
