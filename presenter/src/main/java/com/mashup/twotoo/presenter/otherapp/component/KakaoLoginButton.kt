package com.mashup.twotoo.presenter.otherapp.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooIconButtonImpl
import com.mashup.twotoo.presenter.otherapp.theme.KakaoLoginButtonTheme

@Composable
fun KakaoLoginButton(onClick: () -> Unit) {
    TwoTooIconButtonImpl(
        text = { Text(KakaoLoginButtonTheme.Text, color = KakaoLoginButtonTheme.ContentColor) },
        iconId = KakaoLoginButtonTheme.iconId,
        buttonColor = KakaoLoginButtonTheme.ContainerColor,
        buttonRadius = KakaoLoginButtonTheme.Radius,
    ) { onClick() }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun KakaoLoginButtonPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        KakaoLoginButton(onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
    }
}
