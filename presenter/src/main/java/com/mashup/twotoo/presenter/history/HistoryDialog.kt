package com.mashup.twotoo.presenter.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.common.TextContainer
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooRed
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooRound7
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun ChallengeDropDialog(
    onDismissRequest: () -> Unit,
    onClickDoneButton: () -> Unit,
    onClickCancelButton: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        val textContainerModifier =
            Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(
                    color = Color.White,
                    shape = TwoTooRound7,
                )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 19.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextContainer(
                    modifier = textContainerModifier.clickable {
                        onClickDoneButton()
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.challenge_done),
                            textAlign = TextAlign.Center,
                            color = TwoTooRed,
                            style = TwoTooTheme.typography.headLineNormal18,

                        )
                    },
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextContainer(
                    modifier = textContainerModifier.clickable {
                        onClickCancelButton()
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            textAlign = TextAlign.Center,
                            style = TwoTooTheme.typography.headLineNormal18,
                        )
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun TwoTooButtonDialogPreview() {
    ChallengeDropDialog(
        onDismissRequest = { },
        onClickDoneButton = { },
        onClickCancelButton = { },
    )
}
