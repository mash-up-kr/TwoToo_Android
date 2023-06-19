package com.mashup.twotoo.presenter.designsystem.component.toast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.MainLightPink
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import kotlinx.coroutines.launch

@Composable
fun SnackBarHost(modifier: Modifier, snackState: SnackbarHostState) {
    SnackbarHost(
        modifier = modifier,
        hostState = snackState,
    ) { snackData ->
        TwoTooSnackBarView(snackData.message)
    }
}

@Composable
fun TwoTooSnackBarView(
    message: String
) {
    Card(
        shape = TwoTooTheme.shape.extraSmall,
        backgroundColor = MainLightPink,
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(13.dp, 12.dp),
            style = TwoTooTheme.typography.bodyNormal16,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewTwoTooSnackBar() {
    val snackState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    coroutineScope.launch {
                        snackState.showSnackbar(
                            context.getString(R.string.other_not_accept),
                        )
                    }
                },
            ) {
                Text("Preview Snackbar")
            }
        }
        SnackBarHost(
            Modifier.align(Alignment.BottomCenter),
            snackState,
        )
    }
}
