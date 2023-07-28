package com.mashup.twotoo.presenter.home.before.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.omyuda
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
        if (partnerName != null) {
            Text(
                modifier = Modifier.testTag(stringResource(id = R.string.homeBeforeChallengeTitle)),
                text =
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFF07C4B),
                            fontFamily = omyuda,
                            fontWeight = FontWeight.Normal,
                            fontSize = 28.sp,
                        ),
                    ) {
                        append(partnerName)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = TwoTooTheme.color.mainBrown,
                            fontFamily = omyuda,
                            fontWeight = FontWeight.Normal,
                            fontSize = 28.sp,
                        ),
                    ) {
                        append(stringResource(id = stateTitle.title))
                    }
                },
                textAlign = TextAlign.Center,
            )
        } else {
            Text(
                modifier = Modifier.testTag(stringResource(id = R.string.homeBeforeChallengeTitle)),
                text = stringResource(id = stateTitle.title),
                style = TwoTooTheme.typography.headLineNormal28,
                textAlign = TextAlign.Center,
            )
        }
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
