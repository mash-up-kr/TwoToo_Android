package com.mashup.twotoo.presenter.createChallenge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink

@Composable
fun SelectFlowerCardRoute(
    onStartButtonClick: () -> Unit
) {
    SelectFlowerCard(onStartButtonClick)
}

@Composable
fun SelectFlowerCard(
    onStartButtonClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { TwoTooBackToolbar(onClickBackIcon = {}) },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .paint(
                    painterResource(id = R.drawable.image_background),
                    contentScale = ContentScale.FillBounds,
                ),
        ) {
            Column {
                SelectFlowerTitle()
                SelectFlowerLazyColumn()
            }
            TwoTooTextButton(
                text = stringResource(id = R.string.challenge_start),
                enabled = false,
                modifier = Modifier
                    .padding(bottom = 55.dp, start = 18.dp, end = 18.dp)
                    .fillMaxWidth()
                    .height(57.dp)
                    .align(Alignment.BottomCenter),
            ) {
                onStartButtonClick()
            }
        }
    }
}

@Composable
fun SelectFlowerTitle() {
    Column(
        modifier = Modifier.padding(horizontal = 18.dp),
    ) {
        Text(
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = TwoTooTheme.typography.headLineNormal28.fontFamily,
                        fontSize = TwoTooTheme.typography.headLineNormal28.fontSize,
                        color = TwoTooTheme.color.mainBrown,
                    ),
                ) {
                    append(stringResource(id = R.string.during_challenge))
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = TwoTooTheme.typography.headLineNormal28.fontFamily,
                        fontSize = TwoTooTheme.typography.headLineNormal28.fontSize,
                        color = TwotooPink,
                    ),
                ) {
                    append(stringResource(id = R.string.pair))
                }
                withStyle(
                    style = SpanStyle(
                        fontFamily = TwoTooTheme.typography.headLineNormal28.fontFamily,
                        fontSize = TwoTooTheme.typography.headLineNormal28.fontSize,
                        color = TwoTooTheme.color.mainBrown,
                    ),
                ) {
                    append(stringResource(id = R.string.select_flower))
                }
            },
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
            text = stringResource(id = R.string.select_flower_desc),
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.gray600,
        )
    }
}

@Preview
@Composable
private fun PreviewSelectFlowerCard() {
    SelectFlowerCard()
}
