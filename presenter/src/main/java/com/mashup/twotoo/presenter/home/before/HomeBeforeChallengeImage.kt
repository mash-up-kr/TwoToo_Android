package com.mashup.twotoo.presenter.home.before

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView

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
