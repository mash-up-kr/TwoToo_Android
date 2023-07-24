package com.mashup.twotoo.presenter.guid

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun GuideRoute(
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit,
) {
    Column {
        TwoTooBackToolbar(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.guideTitle),
            titleModifier = Modifier.offset(x = -16.dp),
            onClickBackIcon = { onClickBackButton() },
            color = TwoTooTheme.color.mainWhite,
        )
        GuideWebView(
            modifier = modifier.fillMaxSize(),
        )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GuideWebView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context)
    }
    val url = "https://www.notion.so/"
    AndroidView(
        factory = { webView },
        modifier = modifier,
    ) { wb ->
        wb.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }
}

@Composable
@Preview
fun PreviewGuideWebView() {
    GuideRoute(Modifier, {})
}
