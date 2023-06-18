package com.mashup.twotoo.presenter.mypage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.mypage.components.UserInfomation

@Composable
fun MyPageRoute(
    modifier: Modifier = Modifier,
) {
    MyPageScreen(modifier = modifier.testTag(stringResource(id = R.string.mypage)))
}

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        TwoTooMainToolbar(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.mypage),
            onClickHelpIcon = {},
        )
        UserInfomation()
        Divider()
    }
}
