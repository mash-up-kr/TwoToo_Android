package com.mashup.twotoo.presenter.otherapp.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R

object KakaoLoginButtonTheme {
    val ContainerColor = Color(0xFFFEE500)
    val ContentColor = Color(0xFF000000)
    val Radius = RoundedCornerShape(12.dp)
    val Text = "카카오 로그인"

    @DrawableRes val iconId: Int = R.drawable.kakaotalk
}
