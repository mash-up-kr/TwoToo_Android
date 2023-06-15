package com.mashup.twotoo.presenter.createChallenge

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.createChallenge.recommendChallenge.RecommendChallengeBottomSheet
import com.mashup.twotoo.presenter.designsystem.component.textfield.TwoTooTextField
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import kotlinx.coroutines.launch

@Composable
fun CreateChallengeOneStep() {
    Column(
        modifier = Modifier.padding(top = 12.dp),
    ) {
        InputChallengeName()
        RecommendChallengeButton()

        Row {
            SettingChallengeDate(title = R.string.challenge_start_date)
            Spacer(modifier = Modifier.width(32.dp))
            SettingChallengeDate(title = R.string.challenge_end_date)
        }
    }
}

@Composable
fun InputChallengeName() {
    var text by remember { mutableStateOf("") }

    Column {
        Text(
            text = stringResource(id = R.string.challenge_name),
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.mainBrown,
            modifier = Modifier.padding(bottom = 8.dp),
        )

        TwoTooTextField(
            modifier = Modifier.fillMaxWidth().height(46.dp),
            text = text,
            textHint = stringResource(id = R.string.input_challenge_name_placeholder),
            updateText = { text = it },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendChallengeButton() {
    var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()
    val closeSheet: () -> Unit = { scope.launch { sheetState.hide() } }

    if (isBottomSheetVisible) {
        RecommendChallengeBottomSheet(
            sheetState = sheetState,
            onDismiss = {
                closeSheet.invoke()
                if (!sheetState.isVisible) {
                    isBottomSheetVisible = !isBottomSheetVisible
                }
            },
        )
    }

    Button(
        modifier = Modifier.padding(top = 16.dp, bottom = 40.dp)
            .height(46.dp),
        shape = TwoTooTheme.shape.extraSmall,
        colors = ButtonDefaults.buttonColors(containerColor = TwoTooTheme.color.mainBrown),
        onClick = { isBottomSheetVisible = !isBottomSheetVisible },
    ) {
        Text(
            text = stringResource(id = R.string.recommend_challenge),
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.mainWhite,
        )
    }
}

@Composable
fun SettingChallengeDate(
    @StringRes title: Int
) {
    Column {
        Text(
            text = stringResource(id = title),
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.mainBrown,
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Text(
            text = "2023/05/01",
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.gray600,
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        Color.White,
                        cornerRadius = CornerRadius(8.dp.toPx()),
                    )
                }
                .padding(10.dp),
        )
    }
}

@Preview
@Composable
private fun PreviewDate() {
    SettingChallengeDate(title = R.string.challenge_start_date)
}
