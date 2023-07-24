package com.mashup.twotoo.presenter.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooRound4
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.HomeGoalCountUiModel
import com.mashup.twotoo.presenter.home.ongoing.components.HomeGoalCount
import com.mashup.twotoo.presenter.mypage.components.MyPageItemList
import com.mashup.twotoo.presenter.mypage.model.MyPageItem

@Composable
fun MyPageRoute(
    state: Int,
    modifier: Modifier = Modifier,
    onClickMyPageItem: (MyPageItem) -> Unit,
    navigateToGuide: () -> Unit,
) {
    MyPageScreen(
        modifier = modifier.testTag(
            stringResource(id = R.string.mypage),
        ),
        homeGoalCountUiModel = HomeGoalCountUiModel.default,
        onClickMyPageItem = onClickMyPageItem,
        navigateToGuide = navigateToGuide,
    )
}

@Composable
fun MyPageScreen(
    homeGoalCountUiModel: HomeGoalCountUiModel,
    modifier: Modifier = Modifier,
    onClickMyPageItem: (MyPageItem) -> Unit,
    navigateToGuide: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TwoTooMainToolbar(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.mypage),
            onClickHelpIcon = { navigateToGuide() },
        )
        Spacer(modifier = Modifier.height(25.dp))
        HomeGoalCount(
            homeGoalCountUiModel,
            horizontalAlignment = Alignment.CenterHorizontally,
            textLineSpacerHeight = 10.dp,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.width(66.dp).height(30.dp).background(
                color = Color.White,
                shape = TwoTooRound4,
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(

                text = homeGoalCountUiModel.myName,
                style = TwoTooTheme.typography.headLineNormal18,
                color = TwoTooTheme.color.mainBrown,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Divider(
            modifier = Modifier.fillMaxWidth().height(4.dp),
            color = TwoTooTheme.color.gray500.copy(alpha = 0.1f),
        )
        MyPageItemList(
            modifier = Modifier.fillMaxWidth(),
            onClickMyPageItem = onClickMyPageItem,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    TwoTooTheme {
        MyPageRoute(0, onClickMyPageItem = {}, navigateToGuide = {})
    }
}
