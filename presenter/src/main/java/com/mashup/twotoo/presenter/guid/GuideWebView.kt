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
import com.mashup.twotoo.presenter.mypage.model.GuideUrlItem

@Composable
fun GuideRoute(
    route: String,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val url = GuideUrlItem.findUrlBy(route)
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
