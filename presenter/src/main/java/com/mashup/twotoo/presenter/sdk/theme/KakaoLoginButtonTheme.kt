package com.mashup.twotoo.presenter.sdk.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R

object KakaoLoginButtonTheme {
    val ContainerColor = Color(0xFFFEE500)
    val ContentColor = Color(0xFF000000)
    val Radius = RoundedCornerShape(12.dp)

    @StringRes val TextId: Int = R.string.kakaoLogin

    @DrawableRes val IconId: Int = R.drawable.kakaotalk
}
