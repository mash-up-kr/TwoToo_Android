package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
                visibility = if (meAndPartner[1].authType == AuthType.FirstCreateChallenge) {
                    Visibility.Visible
                } else {
                    Visibility.Invisible
                }
            },
        )

        Row(
            modifier = Modifier.constrainAs(partnerText) {
                bottom.linkTo(partner.top)
                start.linkTo(parent.start)
                visibility = if (meAndPartner[0].authType == AuthType.AuthOnlyPartner ||
                    meAndPartner[0].authType == AuthType.AuthBoth
                ) {
                    Visibility.Visible
                } else {
                    Visibility.Invisible
                }
            },
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(id = R.string.homeAuthOnlyPartener),
            )
            TwoTooImageView(
                modifier = Modifier.width(14.dp).height(12.dp),
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
                visibility = if (meAndPartner[1].authType == AuthType.AuthOnlyPartner ||
                    meAndPartner[1].authType == AuthType.FirstCreateChallenge ||
                    meAndPartner[1].authType == AuthType.DoNotAuthBoth
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
                start.linkTo(partner.end)
                end.linkTo(parent.end)
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
        TwoTooImageView(
            modifier = Modifier.size(12.dp),
            model = homeFlower.growType.growImage,
        )
        Spacer(modifier = Modifier.height(26.dp))
        HomeFlowerOwnerText(
            name = homeFlower.name,
            userType = UserType.PARTNER,
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
        TwoTooImageView(
            modifier = Modifier.size(12.dp),
            model = homeFlower.growType.growImage,
        )
        Spacer(modifier = Modifier.height(26.dp))
        HomeFlowerOwnerText(
            name = homeFlower.name,
            userType = UserType.ME,
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
                        growType = GrowType.SEED,
                        authType = AuthType.FirstCreateChallenge,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = GrowType.SEED,
                        authType = AuthType.FirstCreateChallenge,
                    ),
                ),
            )
        }
    }
}

@Preview("상대방만 인증 완료헀을 때")
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
                        growType = GrowType.SEED,
                        authType = AuthType.AuthOnlyPartner,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = GrowType.SEED,
                        authType = AuthType.AuthOnlyPartner,
                    ),
                ),
            )
        }
    }
}

@Preview("나만 인증 완료했을 때")
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
                        growType = GrowType.SEED,
                        authType = AuthType.AuthOnlyMe,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = GrowType.SEED,
                        authType = AuthType.AuthOnlyMe,
                    ),
                ),
            )
        }
    }
}

@Preview("둘다 인증 완료했을 때")
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
                        growType = GrowType.SEED,
                        authType = AuthType.AuthBoth,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = GrowType.SEED,
                        authType = AuthType.AuthBoth,
                    ),
                ),
            )
        }
    }
}

@Preview("둘다 인증 완료하지 않았을 때")
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
                        growType = GrowType.SEED,
                        authType = AuthType.DoNotAuthBoth,
                    ),
                    HomeFlower(
                        name = "왕자",
                        growType = GrowType.SEED,
                        authType = AuthType.DoNotAuthBoth,
                    ),
                ),
            )
        }
    }
}
