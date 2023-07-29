package com.mashup.twotoo.presenter.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun TwoTooDialog(
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(),
    content: DialogContent,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color.White,
                    shape = TwoTooTheme.shape.medium,
                ).width(screenWidth - 102.dp).padding(top = 34.dp, bottom = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = content.title),
                textAlign = TextAlign.Center,
                style = TwoTooTheme.typography.headLineNormal24,
                color = TwoTooTheme.color.mainBrown,
            )
            Spacer(modifier = Modifier.height(24.dp))

            TwoTooImageView(
                model = content.dialogImage.image,
                modifier = Modifier.width(content.dialogImage.width).height(content.dialogImage.height),
                previewPlaceholder = content.dialogImage.image,
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = content.desc),
                textAlign = TextAlign.Center,
                style = TwoTooTheme.typography.bodyNormal16,
                color = TwoTooTheme.color.gray500,
            )
            Spacer(modifier = Modifier.height(45.dp))

            when (content.buttons.size) {
                1 -> {
                    Row {
                        TwoTooDialogContent(content = content)
                    }
                }
                2 -> {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        TwoTooDialogContent(content = content)
                    }
                }
            }
        }
    }
}

@Composable
private fun TwoTooDialogContent(
    content: DialogContent,
) {
    content.buttons.forEach { btn ->
        TextButton(
            onClick = { btn.action() },
            contentPadding = PaddingValues(12.dp),
        ) {
            Text(
                text = stringResource(id = btn.text),
                style = TwoTooTheme.typography.headLineNormal20,
                color = btn.color,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeBothAuthDialog() {
    val content = DialogContent.createHomeBothAuthDialogContent(
        negativeAction = {},
        positiveAction = {},
    )
    TwoTooTheme {
        TwoTooDialog(
            onDismissRequest = {},
            content = content,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeBloomBothDialog() {
    val content = DialogContent.createHomeBloomBothDialogContent(
        onConfirm = {},
    )
    TwoTooTheme {
        TwoTooDialog(
            onDismissRequest = {},
            content = content,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeDoNotBloomBothDialog() {
    val content = DialogContent.createHomeDoNotBloomBothDialogContent(
        onConfirm = {},
    )
    TwoTooTheme {
        TwoTooDialog(
            onDismissRequest = {},
            content = content,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeCancelChallenge() {
    val content = DialogContent.createHistoryLeaveChallengeDialogContent(
        negativeAction = {},
        positiveAction = {},
    )
    TwoTooTheme {
        TwoTooDialog(
            onDismissRequest = {},
            content = content,
        )
    }
}
