package com.mashup.twotoo.presenter.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooBlack

@Composable
fun TwoTooIconButton(
    text: String,
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int?,
    buttonColor: Color,
    onClick: () -> Unit,
) {
    TwoTooIconButtonImpl(
        text = {
            Text(
                text = text,
                style = TwoTooTheme.typography.headLineNormal18,
                color = TwotooBlack,
            )
        },
        modifier = modifier,
        iconId = iconId,
        buttonColor = buttonColor,
        buttonRadius = TwoTooTheme.shape.large,
    ) {
        onClick()
    }
}

@Composable
fun TwoTooIconButtonImpl(
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int?,
    buttonColor: Color,
    buttonRadius: CornerBasedShape,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.then(
            Modifier.fillMaxWidth()
                .height(42.dp)
                .padding(horizontal = 30.dp),
        ),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        shape = buttonRadius,
        onClick = { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            iconId?.let {
                Icon(
                    painter = painterResource(id = iconId),
                    tint = Color.Unspecified,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            text()
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun IconButtonPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        TwoTooIconButton(
            text = "매일 운동하기",
            modifier = Modifier.fillMaxWidth()
                .height(42.dp)
                .padding(horizontal = 50.dp),
            iconId = R.drawable.kakaotalk,
            buttonColor = TwoTooTheme.color.mainYellow,
            onClick = {},
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}
