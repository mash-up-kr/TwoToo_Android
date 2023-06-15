package com.mashup.twotoo.presenter.home.before

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel

/**
 * @Created by 김현국 2023/06/11
 */

@Composable
fun HomeBeforeChallengeImage(
    @DrawableRes stateImage: Int,
    modifier: Modifier = Modifier,
) {
    TwoTooImageView(
        modifier = modifier.testTag(stringResource(id = R.string.homeBeforeChallengeImage)),
        model = stateImage,
        previewPlaceholder = stateImage,
    )
}

@Preview
@Composable
private fun PreviewHomeBeforeChallengeImageEmpty() {
    TwoTooTheme {
        HomeBeforeChallengeImage(
            modifier = Modifier.width(93.dp)
                .height(109.dp),
            stateImage = BeforeChallengeUiModel.empty.stateImage,
        )
    }
}

@Preview
@Composable
private fun PreviewHomeBeforeChallengeImageRequest() {
    TwoTooTheme {
        HomeBeforeChallengeImage(
            modifier = Modifier.width(93.dp)
                .height(109.dp),
            stateImage = BeforeChallengeUiModel.request.stateImage,
        )
    }
}

@Preview
@Composable
private fun PreviewHomeBeforeChallengeImageResponse() {
    TwoTooTheme {
        HomeBeforeChallengeImage(
            modifier = Modifier.width(93.dp)
                .height(109.dp),
            stateImage = BeforeChallengeUiModel.response.stateImage,
        )
    }
}

@Preview
@Composable
private fun PreviewHomeBeforeChallengeImageWait() {
    TwoTooTheme {
        HomeBeforeChallengeImage(
            modifier = Modifier.width(93.dp)
                .height(109.dp),
            stateImage = BeforeChallengeUiModel.wait.stateImage,
        )
    }
}

@Preview
@Composable
private fun PreviewHomeBeforeChallengeImageTerminate() {
    TwoTooTheme {
        HomeBeforeChallengeImage(
            modifier = Modifier.width(93.dp)
                .height(109.dp),
            stateImage = BeforeChallengeUiModel.termination.stateImage,
        )
    }
}
