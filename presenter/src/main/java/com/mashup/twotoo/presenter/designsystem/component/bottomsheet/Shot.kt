package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ShotList(
    titleText: String,
    textHint: String,
    button: @Composable (Modifier) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(titleText = titleText)
        Spacer(modifier = Modifier.height(20.5.dp))

        Spacer(modifier = Modifier.height(24.5.dp))
        button(Modifier)
    }
}

@Preview
@Composable
fun PreviewShotList() {
    TwoTooTheme {
        ShotList(
            titleText = stringResource(id = R.string.bottomSheetShot),
            textList = listOf(
                "언제까지 쉬고 있을 거야? 얼른 해야지?",
                "잘하구 있어 좀만 더 화이팅하자!!!",
                "이거 보면 빨리 한다! 실시!",
            ).toImmutableList(),
            button = {},
        )
    }
}
