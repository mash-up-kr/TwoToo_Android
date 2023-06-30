package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.mashup.twotoo.presenter.home.model.CheerWithFlower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER

/**
 * @Created by 김현국 2023/06/07
 */

@Composable
fun HomeFlowerMeAndPartner(
    homeChallengeStateUiModel: HomeChallengeStateUiModel,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (textHint, partnerText, waterImage, partner, me, partnerCheer, meCheer, heartImage) = createRefs()
        when (homeChallengeStateUiModel.challengeStateUiModel) {
            is HomeFlowerPartnerAndMeUiModel -> with(homeChallengeStateUiModel.challengeStateUiModel) {
                if (this.me.authType == FirstCreateChallenge) {
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
                HomeFlowerPartner(
                    modifier = Modifier.constrainAs(partner) {
                        start.linkTo(parent.start)
                        end.linkTo(me.start)
                        bottom.linkTo(parent.bottom)
                    },
                    homeFlowerUiModel = this.partner,
                )

                if (this.partner.authType == AuthOnlyPartner) {
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

                if (this.me.authType != AuthOnlyMe && this.me.authType != AuthBoth) {
                    TwoTooImageView(
                        modifier = Modifier.testTag(
                            stringResource(id = R.string.homeOngoingChallengeWaterImage),
                        ).width(75.dp).height(69.dp).constrainAs(waterImage) {
                            top.linkTo(textHint.bottom)
                            bottom.linkTo(me.top, margin = 8.dp)
                            start.linkTo(me.start)
                            end.linkTo(me.end)
                        },
                        model = R.drawable.img_need_water,
                    )
                }

                HomeFlowerMe(
                    modifier = Modifier.constrainAs(me) {
                        end.linkTo(parent.end)
                        start.linkTo(partner.end)
                        bottom.linkTo(parent.bottom)
                    },
                    homeFlowerUiModel = this.me,
                )
            }

            is HomeCheerUiModel -> with(homeChallengeStateUiModel.challengeStateUiModel) {
                (this.partner.homeFlowerUiModel as HomeCheerUiModel).partner
                HomeCheerPartner(cheerText = )
                HomeFlowerPartner(
                    modifier = Modifier.constrainAs(partner) {
                        start.linkTo(parent.start)
                        end.linkTo(me.start)
                        bottom.linkTo(parent.bottom)
                    },
                    homeFlowerUiModel = this.partner.homeFlowerUiModel,
                )

                TwoTooImageView(
                    modifier = Modifier.constrainAs(heartImage) {
                        start.linkTo(partner.end)
                        end.linkTo(me.start)
                        top.linkTo(partner.top)
                        bottom.linkTo(partner.bottom)
                    }.size(28.dp),
                    model = R.drawable.ic_heart,
                    previewPlaceholder = R.drawable.ic_heart,
                )

                HomeFlowerMe(
                    modifier = Modifier.constrainAs(me) {
                        end.linkTo(parent.end)
                        start.linkTo(partner.end)
                        bottom.linkTo(parent.bottom)
                    },
                    homeFlowerUiModel = this.me.homeFlowerUiModel,
                )
            }
        }
    }
}

@Composable
fun HomeCheerPartner(
    cheerText: String,
    modifier: Modifier = Modifier,
) {
    HomeCheerSpeechBubble(
        modifier = modifier,
        userType = PARTNER,
        cheerText = cheerText,
    )
}

@Composable
fun HomeCheerMe(
    cheerText: String,
    modifier: Modifier = Modifier,
) {
    HomeCheerSpeechBubble(
        modifier = modifier,
        userType = ME,
        cheerText = cheerText,
    )
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
        val context = LocalContext.current
        with(homeFlowerUiModel.flowerType.getFlowerImage(context = context)) {
            TwoTooImageView(
                modifier = Modifier.testTag(
                    stringResource(id = R.string.homeOngoingChallengeFlowerPartnerImage),
                ).width(width).height(height),
                model = image,
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
        val context = LocalContext.current
        with(homeFlowerUiModel.flowerType.getFlowerImage(context = context)) {
            TwoTooImageView(
                modifier = Modifier.testTag(
                    stringResource(id = R.string.homeOngoingChallengeFlowerMeImage),
                ).width(width).height(height),
                model = image,
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
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.firstChallenge,
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
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authOnlyPartner,
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
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authOnlyMe,
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
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authBoth,
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
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.doNotAuthBoth,
                ),
            )
        }
    }
}

@Preview("둘다 칭찬하지 않았을때", showBackground = true)
@Composable
private fun PreviewDoNotCheerBothHomeFlower() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.default.copy(
                        partner = CheerWithFlower.partner,
                        me = CheerWithFlower.me,
                    ),
                ),
            )
        }
    }
}
