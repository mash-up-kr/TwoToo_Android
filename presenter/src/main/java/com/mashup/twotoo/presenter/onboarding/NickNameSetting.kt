package com.mashup.twotoo.presenter.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.textfield.TwoTooTextField
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.MainYellow
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink

@Composable
fun NickNameSetting(
    otherNickName: String?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TwoTooTheme.color.backgroundYellow),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TwoTooMainToolbar()
        Spacer(modifier = Modifier.height(35.dp))

        otherNickName?.let { nickname ->
            InviteGuide(nickname)
        }
        Spacer(modifier = Modifier.height(78.dp))
        Text(
            text = stringResource(id = R.string.nickname_setting),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.headLineNormal28,
            color = TwoTooTheme.color.mainBrown,
        )
        Spacer(modifier = Modifier.height(32.dp))

        InputUserNickName()
        Spacer(modifier = Modifier.weight(1f))
        TwoTooTextButton(
            text = stringResource(id = R.string.button_confirm),
            enabled = false,
        ) {}
        Spacer(modifier = Modifier.height(55.dp))
    }
}

@Composable
fun InputUserNickName() {
    var text by remember { mutableStateOf("") }
    val nickNameMaxLength = 4

    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
    ) {
        Text(
            text = stringResource(id = R.string.nickname),
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.mainBrown,
        )
        Spacer(modifier = Modifier.height(8.dp))

        TwoTooTextField(
            modifier = Modifier.fillMaxWidth().height(46.dp),
            text = text,
            textHint = stringResource(id = R.string.nickname_setting_hint),
            updateText = { if(it.length <= nickNameMaxLength) text = it },
        )
    }
}

@Composable
fun InviteGuide(otherNickName: String) {
    Text(
        modifier = Modifier
            .drawBehind {
                drawRoundRect(
                    color = MainYellow,
                    cornerRadius = CornerRadius(10.dp.toPx()),
                )
            }
            .padding(10.dp),
        textAlign = TextAlign.Center,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontFamily = TwoTooTheme.typography.bodyNormal16.fontFamily,
                    color = TwotooPink,
                ),
            ) {
                append(stringResource(id = R.string.other, otherNickName))
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = TwoTooTheme.typography.bodyNormal16.fontFamily,
                    color = TwoTooTheme.color.mainBrown,
                ),
            ) {
                append(stringResource(id = R.string.invite_someone))
            }
        },
    )
}

@Preview
@Composable
private fun InviteGuidePreview() {
    InviteGuide("공주")
}

@Preview
@Composable
private fun NickNameSettingPreview() {
    NickNameSetting("공주")
}
