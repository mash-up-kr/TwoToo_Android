package com.mashup.twotoo.presenter.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.otherapp.theme.KakaoLoginButtonTheme

@Composable
fun TwoTooIconButton(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = 30.dp),
    @DrawableRes iconId: Int,
    ButtonColor: Color,
    contentTintColor: Color = KakaoLoginButtonTheme.ContentColor,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
        shape = KakaoLoginButtonTheme.Radius,
        onClick = { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = contentTintColor,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                style = TextStyle(color = contentTintColor, fontWeight = FontWeight.Bold),
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun IconButtonPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        TwoTooIconButton(
            KakaoLoginButtonTheme.Text,
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 10.dp),
            R.drawable.kakaotalk,
            KakaoLoginButtonTheme.ContainerColor,
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
    }
}
