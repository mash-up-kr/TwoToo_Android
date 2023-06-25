package com.mashup.twotoo.presenter.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun TwoTooDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: DialogContent
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .background(
                    Color.White,
                    shape = TwoTooTheme.shape.medium,
                )
                .padding(horizontal = 30.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = content.title),
                textAlign = TextAlign.Center,
                style = TwoTooTheme.typography.headLineNormal24,
                color = TwoTooTheme.color.mainBrown,
            )
            Spacer(modifier = Modifier.height(24.dp))

            content.image?.let { drawableRes ->
                TwoTooImageView(
                    model = drawableRes,
                    modifier = Modifier.size(100.dp),
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            Text(
                text = stringResource(id = content.desc),
                textAlign = TextAlign.Center,
                style = TwoTooTheme.typography.bodyNormal16,
                color = TwoTooTheme.color.gray500,
            )
            Spacer(modifier = Modifier.height(45.dp))

            Row {
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
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    val content = DialogContent(
        title = R.string.all_auth_title,
        desc = R.string.all_auth_desc,
        image = null,
        buttons = listOf(
            DialogButtonContent(R.string.cheer_up_cancel),
            DialogButtonContent(R.string.cheer_up),
        ),
    )
    TwoTooDialog(
        onDismissRequest = {},
        content = content,
    )
}
