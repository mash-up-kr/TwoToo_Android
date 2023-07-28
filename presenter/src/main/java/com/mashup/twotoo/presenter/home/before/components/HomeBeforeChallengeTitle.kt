package com.mashup.twotoo.presenter.home.before.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.StateTitleUiModel

/**
 * @Created by 김현국 2023/06/11
 */

@Composable
fun HomeBeforeChallengeTitle(
    stateTitle: StateTitleUiModel,
    modifier: Modifier = Modifier,
) {
    val partnerName = stateTitle.partnerName
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        Text(
            modifier = Modifier.testTag(stringResource(id = R.string.homeBeforeChallengeTitle)),
            text = if (partnerName != null) {
                partnerName + stringResource(id = stateTitle.title)
            } else {
                stringResource(id = stateTitle.title)
            },

            style = TwoTooTheme.typography.headLineNormal28,
            color = TwoTooTheme.color.mainBrown,
            textAlign = TextAlign.Center,
        )
        stateTitle.subTitle?.let {
            Text(
                modifier = Modifier.testTag(stringResource(id = R.string.homeBeforeChallengeSubTitle)),
                text = stringResource(id = stateTitle.subTitle),
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.gray600,
                textAlign = TextAlign.Center,
            )
        }
    }
}
