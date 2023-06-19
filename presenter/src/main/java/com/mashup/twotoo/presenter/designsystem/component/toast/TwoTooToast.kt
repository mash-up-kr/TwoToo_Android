package com.mashup.twotoo.presenter.designsystem.component.toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.MainLightPink
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2023/06/19
 */

@Composable
fun TwoTooToast(
    snackBarHostState: SnackbarHostState,
    toastBackgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        modifier = modifier,
        hostState = snackBarHostState,
    ) {
        snackBarHostState.currentSnackbarData?.let { snackBarData ->
            Snackbar(
                modifier = Modifier.padding(horizontal = 21.dp).fillMaxWidth(),
                backgroundColor = toastBackgroundColor,
                shape = TwoTooTheme.shape.extraSmall,
                elevation = 0.dp,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = snackBarData.message,
                        style = TwoTooTheme.typography.bodyNormal16,
                        color = TwoTooTheme.color.mainBrown,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTwoTooToast() {
    TwoTooTheme {
        TwoTooToast(
            modifier = Modifier
                .padding(horizontal = 21.dp)
                .fillMaxWidth(),
            toastBackgroundColor = MainLightPink,
            snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }.value,
        )
    }
}

@Preview(showBackground = true, name = "실제 사용할때 !")
@Composable
private fun PreviewOpenTwoTooToast() {
    TwoTooTheme {
        val hostState by remember { mutableStateOf(SnackbarHostState()) }
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Button(
                onClick = {
                    scope.launch {
                        hostState.showSnackbar(
                            context.getString(R.string.toast_challenge_start),
                            duration = SnackbarDuration.Short,
                        )
                    }
                },
            ) {
            }
            TwoTooToast(
                snackBarHostState = hostState,
                modifier = Modifier
                    .fillMaxWidth().align(Alignment.BottomCenter),
                toastBackgroundColor = MainLightPink,
            )
        }
    }
}
