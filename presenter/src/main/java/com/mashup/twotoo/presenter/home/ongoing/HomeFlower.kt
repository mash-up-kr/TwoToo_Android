package com.mashup.twotoo.presenter.home.ongoing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.home.model.AuthType.AuthBoth
import com.mashup.twotoo.presenter.home.model.AuthType.AuthOnlyMe
import com.mashup.twotoo.presenter.home.model.AuthType.AuthOnlyPartner
import com.mashup.twotoo.presenter.home.model.AuthType.FirstCreateChallenge
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER

/**
 * @Created by 김현국 2023/06/07
 */

@Composable
fun HomeFlowerMeAndPartner(
    meAndPartner: HomeFlowerPartnerAndMeUiModel,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (textHint, partnerText, waterImage, partner, me) = createRefs()

        if (meAndPartner.me.authType == FirstCreateChallenge) {
            TextHint(
                modifier = Modifier.testTag(
                    stringResource(id = R.string.homeOngoingChallengeFirstChallengeHint),
                ).constrainAs(textHint) {
                    start.linkTo(waterImage.start)
                    end.linkTo(waterImage.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(waterImage.top, margin = 6.dp)
                },
            )
        }

        if (meAndPartner.partner.authType == AuthOnlyPartner) {
            Row(
                modifier = Modifier.testTag(
                    stringResource(id = R.string.homeOngoingChallengeAuthPartnerText),
                ).constrainAs(partnerText) {
                    bottom.linkTo(partner.top, margin = 15.dp)
                    start.linkTo(partner.start)
                    end.linkTo(partner.end)
                },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(id = R.string.homeAuthOnlyPartener),
                    style = TwoTooTheme.typography.bodyNormal14,
                    color = TwotooPink,
                )
                Spacer(modifier = Modifier.width(4.dp))
                TwoTooImageView(
                    modifier = Modifier.width(14.dp).height(14.dp),
                    model = R.drawable.ic_heart,
                    previewPlaceholder = R.drawable.ic_heart,
                )
            }
        }

        HomeFlowerPartner(
            modifier = Modifier.constrainAs(partner) {
                start.linkTo(parent.start)
                end.linkTo(me.start)
                bottom.linkTo(parent.bottom)
            },
            homeFlowerUiModel = meAndPartner.partner,
        )

        if (meAndPartner.me.authType != AuthOnlyMe && meAndPartner.me.authType != AuthBoth) {
            TwoTooImageView(
                modifier = Modifier.testTag(
                    stringResource(id = R.string.homeOngoingChallengeWaterImage),
                ).width(59.dp).height(60.dp).constrainAs(waterImage) {
                    top.linkTo(textHint.bottom)
                    bottom.linkTo(me.top, margin = 8.dp)
                    start.linkTo(me.start)
                    end.linkTo(me.end)
                },
                model = R.drawable.ic_needed_water,
            )
        }

        HomeFlowerMe(
            modifier = Modifier.constrainAs(me) {
                end.linkTo(parent.end)
                start.linkTo(partner.end)
                bottom.linkTo(parent.bottom)
            },
            homeFlowerUiModel = meAndPartner.me,
        )
    }
}

@Composable
fun HomeFlowerPartner(
    homeFlowerUiModel: HomeFlowerUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        with(homeFlowerUiModel.growType) {
            TwoTooImageView(
                modifier = Modifier.testTag(
                    stringResource(id = R.string.homeOngoingChallengeFlowerPartnerImage),
                ).width(width).height(height),
                model = growImage,
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        HomeFlowerOwnerText(
            name = homeFlowerUiModel.name,
            userType = PARTNER,
        )
    }
}

@Composable
fun HomeFlowerMe(
    homeFlowerUiModel: HomeFlowerUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        with(homeFlowerUiModel.growType) {
            TwoTooImageView(
                modifier = Modifier.testTag(
                    stringResource(id = R.string.homeOngoingChallengeFlowerMeImage),
                ).width(width).height(height),
                model = growImage,
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        HomeFlowerOwnerText(
            name = homeFlowerUiModel.name,
            userType = ME,
        )
    }
}

@Composable
fun TextHint(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.homeFirstCreatedChallenge),
        style = TwoTooTheme.typography.bodyNormal16,
        color = TwoTooTheme.color.gray500,
    )
}

@Preview("첫 챌린지 생성", showBackground = true)
@Composable
private fun PreviewFirstChallengeHomeFlower() {
    TwoTooTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                meAndPartner = HomeFlowerPartnerAndMeUiModel.firstChallenge,
            )
        }
    }
}

@Preview("상대방만 인증 완료헀을 때", showBackground = true)
@Composable
private fun PreviewAuthOnlyPartnerHomeFlower() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                meAndPartner = HomeFlowerPartnerAndMeUiModel.authOnlyPartner,
            )
        }
    }
}

@Preview("나만 인증 완료했을 때", showBackground = true)
@Composable
private fun PreviewAuthOnlyMeHomeFlower() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                meAndPartner = HomeFlowerPartnerAndMeUiModel.authOnlyMe,
            )
        }
    }
}

@Preview("둘다 인증 완료했을 때", showBackground = true)
@Composable
private fun PreviewAuthBothHomeFlower() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                meAndPartner = HomeFlowerPartnerAndMeUiModel.authBoth,
            )
        }
    }
}

@Preview("둘다 인증 완료하지 않았을 때", showBackground = true)
@Composable
private fun PreviewDoNotAuthBothHomeFlower() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                meAndPartner = HomeFlowerPartnerAndMeUiModel.doNotAuthBoth,
            )
        }
    }
}
