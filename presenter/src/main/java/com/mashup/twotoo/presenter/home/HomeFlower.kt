package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Visibility
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.home.model.AuthType.AuthBoth
import com.mashup.twotoo.presenter.home.model.AuthType.AuthOnlyMe
import com.mashup.twotoo.presenter.home.model.AuthType.AuthOnlyPartner
import com.mashup.twotoo.presenter.home.model.AuthType.DoNotAuthBoth
import com.mashup.twotoo.presenter.home.model.AuthType.FirstCreateChallenge
import com.mashup.twotoo.presenter.home.model.GrowType.SEED
import com.mashup.twotoo.presenter.home.model.HomeFlower
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER

/**
 * @Created by 김현국 2023/06/07
 */

@Composable
fun HomeFlowerMeAndPartner(
    meAndPartner: List<HomeFlower>,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (textHint, partnerText, waterImage, partner, me) = createRefs()
        TextHint(
            modifier = Modifier.constrainAs(textHint) {
                start.linkTo(waterImage.start)
                end.linkTo(waterImage.end)
                top.linkTo(parent.top)
                bottom.linkTo(waterImage.top, margin = 15.dp)
                visibility = if (meAndPartner[1].authType == FirstCreateChallenge) {
                    Visibility.Visible
                } else {
                    Visibility.Invisible
                }
            },
        )
        Row(
            modifier = Modifier.constrainAs(partnerText) {
                bottom.linkTo(partner.top, margin = 15.dp)
                start.linkTo(partner.start)
                end.linkTo(partner.end)
                visibility = if (meAndPartner[0].authType == AuthOnlyPartner ||
                    meAndPartner[0].authType == AuthBoth
                ) {
                    Visibility.Visible
                } else {
                    Visibility.Invisible
                }
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

        HomeFlowerPartner(
            modifier = Modifier.constrainAs(partner) {
                start.linkTo(parent.start)
                end.linkTo(me.start)
                bottom.linkTo(parent.bottom)
            },
            homeFlower = meAndPartner[0],
        )

        TwoTooImageView(
            modifier = Modifier.width(59.dp).height(60.dp).constrainAs(waterImage) {
                top.linkTo(textHint.bottom)
                bottom.linkTo(me.top, margin = 22.dp)
                start.linkTo(me.start)
                end.linkTo(me.end)
                visibility = if (meAndPartner[1].authType == AuthOnlyPartner ||
                    meAndPartner[1].authType == FirstCreateChallenge ||
                    meAndPartner[1].authType == DoNotAuthBoth
                ) {
                    Visibility.Visible
                } else {
                    Visibility.Invisible
                }
            },
            model = R.drawable.ic_needed_water,
        )

        HomeFlowerMe(
            modifier = Modifier.constrainAs(me) {
                end.linkTo(parent.end)
                start.linkTo(partner.end)
                bottom.linkTo(parent.bottom)
            },
            homeFlower = meAndPartner[1],
        )
    }
}

@Composable
fun HomeFlowerPartner(
    homeFlower: HomeFlower,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        with(homeFlower.growType) {
            TwoTooImageView(
                modifier = Modifier.width(width).height(height),
                model = growImage,
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        HomeFlowerOwnerText(
            name = homeFlower.name,
            userType = PARTNER,
        )
    }
}

@Composable
fun HomeFlowerMe(
    homeFlower: HomeFlower,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        with(homeFlower.growType) {
            TwoTooImageView(
                modifier = Modifier.width(width).height(height),
                model = growImage,
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        HomeFlowerOwnerText(
            name = homeFlower.name,
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
                listOf(
                    HomeFlower(
                        name = "공주",
                        growType = SEED,
                        authType = FirstCreateChallenge,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = SEED,
                        authType = FirstCreateChallenge,
                    ),
                ),
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
                listOf(
                    HomeFlower(
                        name = "공주",
                        growType = SEED,
                        authType = AuthOnlyPartner,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = SEED,
                        authType = AuthOnlyPartner,
                    ),
                ),
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
                listOf(
                    HomeFlower(
                        name = "공주",
                        growType = SEED,
                        authType = AuthOnlyMe,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = SEED,
                        authType = AuthOnlyMe,
                    ),
                ),
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
                listOf(
                    HomeFlower(
                        name = "공주",
                        growType = SEED,
                        authType = AuthBoth,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = SEED,
                        authType = AuthBoth,
                    ),
                ),
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
                listOf(
                    HomeFlower(
                        name = "공주",
                        growType = SEED,
                        authType = DoNotAuthBoth,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = SEED,
                        authType = DoNotAuthBoth,
                    ),
                ),
            )
        }
    }
}
