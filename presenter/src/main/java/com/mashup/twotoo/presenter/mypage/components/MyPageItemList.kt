package com.mashup.twotoo.presenter.mypage.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.mypage.model.MyPageItem

/**
 * @Created by 김현국 2023/06/18
 */

@Composable
fun MyPageItemList(
    navigateToGuide: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        MyPageItem.values().forEach { myPageItem ->
            MyPageItemRow(
                myPageItem = myPageItem,
                navigateToGuide = navigateToGuide,
            )
        }
    }
}

@Composable
fun MyPageItemRow(
    myPageItem: MyPageItem,
    navigateToGuide: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(46.dp)
            .clickable { navigateToGuide(myPageItem.route) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = stringResource(id = myPageItem.value),
            style = TwoTooTheme.typography.headLineNormal18,
            color = TwoTooTheme.color.mainBrown,
        )
    }
}
