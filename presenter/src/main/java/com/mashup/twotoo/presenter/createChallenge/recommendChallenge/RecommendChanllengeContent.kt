package com.mashup.twotoo.presenter.createChallenge.recommendChallenge

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.createChallenge.model.RecommendChallengeUiModel
import com.mashup.twotoo.presenter.designsystem.theme.BackgroundYellow
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun RecommendChallengeContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)
            .background(color = BackgroundYellow),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.recommend_challenge_title),
            style = TwoTooTheme.typography.headLineNormal24,
            color = TwoTooTheme.color.mainBrown,
            modifier = Modifier.padding(top = 7.dp),
        )
        val recommendList = RecommendChallengeUiModel.getRecommendChallengeList()
        recommendList.forEach { recommendName ->
            RecommendButtonItem(recommendName.name)
        }
    }
}

@Composable
fun RecommendButtonItem(recommendName: Int) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val color = if (isPressed) TwoTooTheme.color.mainYellow else TwoTooTheme.color.mainWhite

    Button(
        shape = TwoTooTheme.shape.medium,
        interactionSource = interactionSource,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        onClick = {},
    ) {
        Text(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp),
            text = stringResource(id = recommendName),
            style = TwoTooTheme.typography.headLineNormal18,
            color = TwoTooTheme.color.mainBrown,
        )
    }
}

@Preview
@Composable
private fun PreviewRecommendChallengeContent() {
    RecommendChallengeContent()
}
