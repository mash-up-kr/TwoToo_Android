package com.mashup.twotoo.presenter.designsystem.component.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.*

@Composable
fun TwoTooMainToolbar(
    onClickHelpIcon: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.app_name),
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 22.dp).padding(horizontal = 24.dp).height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = text,
            color = TwotooPink,
            style = TwoTooTheme.typography.headLineNormal28,
        )

        Icon(
            modifier = Modifier.clip(shape = CircleShape).clickable {
                onClickHelpIcon()
            },
            painter = painterResource(id = R.drawable.help),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoTooMainToolbar(title: String = stringResource(id = R.string.app_name)) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = title,
                    color = TwotooPink,
                    style = TwoTooTheme.typography.headLineNormal28,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.offset(x = (-16).dp).padding(horizontal = 24.dp),
                )
            }
        },
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
    )
}

@Composable
@Preview
private fun MainToolbarWithNavAndActionsPreview() {
    TwoTooMainToolbar(text = "공주", onClickHelpIcon = {})
}

@Composable
@Preview
private fun MainToolbarPreview() {
    TwoTooMainToolbar()
}
