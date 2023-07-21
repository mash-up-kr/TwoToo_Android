package com.mashup.twotoo.presenter.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
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
        TwoTooImageView(
            modifier = Modifier.size(width = 149.dp, height = 129.dp),
            model = R.drawable.img_nicknam_my,
            previewPlaceholder = R.drawable.img_nicknam_my,
        )
        Spacer(modifier = Modifier.height(29.dp))
        HomeGoalCount(
            homeGoalCountUiModel,
            horizontalAlignment = Alignment.CenterHorizontally,
            textLineSpacerHeight = 10.dp,
        )
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier
                .width(66.dp)
                .height(34.dp)
                .background(
                    color = Color.White,
                    shape = TwoTooTheme.shape.small,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(

                text = homeGoalCountUiModel.myName ?: "",
                style = TwoTooTheme.typography.headLineNormal18,
                color = TwoTooTheme.color.mainBrown,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            color = TwoTooTheme.color.mainYellow,
        )
        Spacer(modifier = Modifier.height(11.dp))
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
