package com.mashup.twotoo.presenter.nickname

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.textfield.TwoTooTextField
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.MainYellow
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun NickNameSettingRoute(
    nickNameViewModel: NickNameViewModel,
    onLoginSuccess: (String) -> Unit
) {
    val state = nickNameViewModel.collectAsState().value
    NickNameSetting(state, onNextButtonClick = { nickName ->
        nickNameViewModel.setUserNickName(nickName)
    })

    nickNameViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is NickNameSideEffect.NavigateToHome -> {
                onLoginSuccess(NavigationRoute.HomeGraph.HomeScreen.route)
            }
            is NickNameSideEffect.NavigateToSendInvitation -> {
                onLoginSuccess(NavigationRoute.InvitationGraph.SendInvitationScreen.route)
            }
        }
    }
}

@Composable
fun NickNameSetting(
    state: NickNameState,
    onNextButtonClick: (String) -> Unit,
    otherNickName: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TwoTooMainToolbar()
        if (otherNickName != null) {
            TwoTooImageView(
                modifier = Modifier.size(97.dp, 85.dp),
                previewPlaceholder = R.drawable.img_nickname_mate,
                model = R.drawable.img_nickname_mate,
                contentScale = ContentScale.Crop,
            )
            InviteGuide(state.partnerNickName)
        } else {
            TwoTooImageView(
                modifier = Modifier.size(149.dp, 129.dp),
                previewPlaceholder = R.drawable.img_nicknam_my,
                model = R.drawable.img_nicknam_my,
            )
        }
        Text(
            modifier = Modifier.padding(top = 78.dp),
            text = stringResource(id = R.string.nickname_setting),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.headLineNormal28,
            color = TwoTooTheme.color.mainBrown,
        )
        var nickName by remember { mutableStateOf("") }
        InputUserNickName(nickName, onTextValueChanged = { nickName = it })
        Spacer(modifier = Modifier.weight(1f))
        TwoTooTextButton(
            text = stringResource(id = R.string.button_confirm),
            enabled = true,
        ) {
            onNextButtonClick(nickName)
        }
        Spacer(modifier = Modifier.height(55.dp))
    }
}

@Composable
fun InputUserNickName(
    nickName: String,
    onTextValueChanged: (String) -> Unit
) {
    val nickNameMaxLength = 4

    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 32.dp),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(id = R.string.nickname),
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.mainBrown,
        )

        TwoTooTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            text = nickName,
            textHint = stringResource(id = R.string.nickname_setting_hint),
            updateText = { text ->
                if (text.length <= nickNameMaxLength) onTextValueChanged(text)
            },
        )
    }
}

@Composable
fun InviteGuide(partnerNickName: String) {
    Text(
        modifier = Modifier
            .padding(top = 27.dp)
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
                append(stringResource(id = R.string.other, partnerNickName))
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
    NickNameSetting(NickNameState(), {}, "공주")
}
