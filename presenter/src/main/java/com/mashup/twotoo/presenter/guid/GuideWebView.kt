package com.mashup.twotoo.presenter.guid

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooToolbar
import com.mashup.twotoo.presenter.mypage.model.GuideUrlItem

@Composable
fun GuideRoute(
    route: String,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val url = GuideUrlItem.findUrlBy(route)
    val title = GuideUrlItem.findTitleBy(route)
    Box(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        TwoTooToolbar.BackToolbar(
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter),
            onClickBackIcon = onClickBackButton,
            onClickActionButton = {},
            actionIcons = null,
        )
        GuideWebView(
            modifier = modifier.fillMaxSize().padding(top = 54.dp).navigationBarsPadding(),
            url = url,
        )
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GuideWebView(
    url: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context)
    }
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
    GuideRoute(modifier = Modifier, onClickBackButton = {}, route = "")
}
