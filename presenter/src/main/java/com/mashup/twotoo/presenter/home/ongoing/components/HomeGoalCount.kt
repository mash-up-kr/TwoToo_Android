package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.HomeGoalCountUiModel

@Composable
fun HomeGoalCount(
    homeGoalCountUiModel: HomeGoalCountUiModel,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.End,
    textLineSpacerHeight: Dp = 2.dp,
    isChallengeCountVisible: Boolean = true,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides TwoTooTheme.typography.bodyNormal14,
            LocalContentColor provides TwoTooTheme.color.twoTooPink,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                Row(
                    modifier = Modifier.width(21.dp).height(22.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = homeGoalCountUiModel.partnerName ?: "",
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                TwoTooImageView(
                    modifier = Modifier.width(14.dp).height(14.dp),
                    model = R.drawable.img_heart,
                    previewPlaceholder = R.drawable.img_heart,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Row(
                    modifier = Modifier.width(21.dp).height(22.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = homeGoalCountUiModel.myName ?: "",
                        textAlign = TextAlign.Center,
                    )
                }
            }
            if (isChallengeCountVisible) {
                Spacer(modifier = Modifier.height(textLineSpacerHeight))
                Text(text = "${homeGoalCountUiModel.count}번째 챌린지 중")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGoalCount() {
    TwoTooTheme {
        HomeGoalCount(
            homeGoalCountUiModel = HomeGoalCountUiModel.default,
        )
    }
}
