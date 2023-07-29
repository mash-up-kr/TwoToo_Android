package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import com.mashup.twotoo.presenter.home.model.AuthType.FirstCreateChallengeButAuthOnlyPartner
import com.mashup.twotoo.presenter.home.model.ChallengeState.Complete
import com.mashup.twotoo.presenter.home.model.CheerState
import com.mashup.twotoo.presenter.home.model.CheerWithFlower
import com.mashup.twotoo.presenter.home.model.Flower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.Stage

/**
 * @Created by 김현국 2023/06/07
 */

@Composable
fun HomeFlowerMeAndPartner(
    homeChallengeStateUiModel: HomeChallengeStateUiModel,
    modifier: Modifier = Modifier,
    onCommit: () -> Unit = {},
    onClickCheerButton: () -> Unit = {},
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (
            textHint, partnerText, waterImage, partner,
            partnerFlowerOwnerText, me, meFlowerOwnerText,
            partnerCheer, meCheer, heartImage, partnerFlowerLanguage, meFlowerLanguage,
        ) = createRefs()

        when (homeChallengeStateUiModel.challengeStateUiModel) {
            is HomeFlowerPartnerAndMeUiModel -> with(homeChallengeStateUiModel.challengeStateUiModel) {
                if (homeChallengeStateUiModel.challengeState == Complete) {
                    HomeFlowerLanguage(
                        modifier = Modifier.constrainAs(partnerFlowerLanguage) {
                            bottom.linkTo(partner.top)
                            start.linkTo(partner.start)
                            end.linkTo(partner.end)
                        },
                        homeFlowerUiModel = this.partner,
                    )
                    HomeFlowerLanguage(
                        modifier = Modifier.constrainAs(meFlowerLanguage) {
                            bottom.linkTo(me.top)
                            start.linkTo(me.start)
                            end.linkTo(me.end)
                        },
                        homeFlowerUiModel = this.me,
                    )
                }

                if (this.authType == FirstCreateChallenge || this.authType == FirstCreateChallengeButAuthOnlyPartner) {
                    TextHint(
                        modifier = Modifier
                            .testTag(
                                stringResource(id = R.string.homeOngoingChallengeFirstChallengeHint),
                            )
                            .constrainAs(textHint) {
                                start.linkTo(waterImage.start)
                                end.linkTo(waterImage.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(waterImage.top, margin = 6.dp)
                            },
                    )
                }
                HomeFlowerPartner(
                    modifier = Modifier.constrainAs(partner) {
                        start.linkTo(partnerFlowerOwnerText.start)
                        end.linkTo(partnerFlowerOwnerText.end)
                        bottom.linkTo(partnerFlowerOwnerText.top, margin = 3.dp)
                    },
                    homeFlowerUiModel = this.partner,
                )
                HomeFlowerOwnerText(
                    modifier = Modifier.constrainAs(partnerFlowerOwnerText) {
                        linkTo(start = parent.start, end = parent.end, bias = 0.3f)
                        bottom.linkTo(parent.bottom)
                    },
                    name = this.partner.name,
                    userType = PARTNER,
                )

                if (this.authType == AuthOnlyPartner || this.authType == FirstCreateChallengeButAuthOnlyPartner) {
                    Row(
                        modifier = Modifier
                            .testTag(
                                stringResource(id = R.string.homeOngoingChallengeAuthPartnerText),
                            )
                            .constrainAs(partnerText) {
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
                            modifier = Modifier
                                .width(14.dp)
                                .height(14.dp),
                            model = R.drawable.img_heart,
                            previewPlaceholder = R.drawable.img_heart,
                        )
                    }
                }

                if (this.authType != AuthOnlyMe && this.authType != AuthBoth) {
                    TwoTooImageView(
                        modifier = Modifier
                            .testTag(
                                stringResource(id = R.string.homeOngoingChallengeWaterImage),
                            )
                            .width(75.dp)
                            .height(69.dp)
                            .constrainAs(waterImage) {
                                top.linkTo(textHint.bottom)
                                bottom.linkTo(me.top, margin = 8.dp)
                                start.linkTo(me.start)
                                end.linkTo(me.end)
                            }.clickable {
                                onCommit()
                            },

                        model = R.drawable.img_need_water,
                    )
                }

                HomeFlowerMe(
                    modifier = Modifier.constrainAs(me) {
                        start.linkTo(meFlowerOwnerText.start)
                        end.linkTo(meFlowerOwnerText.end)
                        bottom.linkTo(meFlowerOwnerText.top, margin = 3.dp)
                    },
                    homeFlowerUiModel = this.me,
                )
                HomeFlowerOwnerText(
                    modifier = Modifier.constrainAs(
                        meFlowerOwnerText,
                    ) {
                        linkTo(start = parent.start, end = parent.end, bias = 0.7f)
                        bottom.linkTo(parent.bottom)
                    },
                    name = this.me.name,
                    userType = ME,
                )
            }

            is HomeCheerUiModel -> with(homeChallengeStateUiModel.challengeStateUiModel) {
                when (cheerState) {
                    CheerState.CheerOnlyPartner, CheerState.CheerBoth -> {
                        HomeCheerSpeechBubble(
                            modifier = Modifier.width(140.dp).testTag(
                                stringResource(R.string.homeCheerChallengePartnerBubble),
                            ).constrainAs(partnerCheer) {
                                start.linkTo(parent.start, margin = 32.dp)
                                end.linkTo(heartImage.start)
                                bottom.linkTo(
                                    partner.top,
                                    margin =
                                    if ((this@with.partner.homeFlowerUiModel.flowerType as Flower).growType >= Stage.Third) {
                                        0.dp
                                    } else {
                                        if (cheerState in listOf(CheerState.CheerOnlyMe, CheerState.DoNotCheerBoth)) {
                                            18.dp
                                        } else {
                                            12.dp
                                        }
                                    },

                                )
                            },
                            userType = PARTNER,
                            cheerText = this.partner.cheerText,
                        )
                    }
                    CheerState.DoNotCheerBoth, CheerState.CheerOnlyMe -> {
                        TwoTooImageView(
                            modifier = Modifier.constrainAs(partnerCheer) {
                                start.linkTo(parent.start, margin = 32.dp)
                                end.linkTo(heartImage.start)
                                bottom.linkTo(
                                    partner.top,
                                    margin =
                                    if ((this@with.partner.homeFlowerUiModel.flowerType as Flower).growType >= Stage.Third) {
                                        0.dp
                                    } else {
                                        if (cheerState in listOf(CheerState.CheerOnlyMe, CheerState.DoNotCheerBoth)) {
                                            18.dp
                                        } else {
                                            12.dp
                                        }
                                    },

                                )
                            }
                                .testTag(
                                    stringResource(R.string.homeCheerChallengePartnerBeforeCheerBubble),
                                )
                                .size(44.dp),
                            model = R.drawable.img_cheer_partner_empty,
                            previewPlaceholder = R.drawable.img_cheer_partner_empty,
                        )
                    }
                }
                HomeFlowerPartner(
                    modifier = Modifier.constrainAs(partner) {
                        start.linkTo(partnerFlowerOwnerText.start)
                        end.linkTo(partnerFlowerOwnerText.end)
                        bottom.linkTo(partnerFlowerOwnerText.top, margin = 7.dp)
                    },
                    homeFlowerUiModel = this.partner.homeFlowerUiModel,
                )

                HomeFlowerOwnerText(
                    modifier = Modifier.constrainAs(partnerFlowerOwnerText) {
                        linkTo(start = parent.start, end = parent.end, bias = 0.3f)
                        bottom.linkTo(parent.bottom)
                    },
                    name = this.partner.homeFlowerUiModel.name,
                    userType = PARTNER,
                )

                TwoTooImageView(
                    modifier = Modifier
                        .constrainAs(heartImage) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(partner.bottom, margin = 50.dp)
                        }
                        .size(28.dp),
                    model = R.drawable.img_heart,
                    previewPlaceholder = R.drawable.img_heart,
                )

                HomeCheerMe(
                    modifier = Modifier
                        .width(140.dp)
                        .constrainAs(meCheer) {
                            top.linkTo(parent.top)
                            start.linkTo(heartImage.end)
                            end.linkTo(parent.end, margin = 19.dp)
                            bottom.linkTo(
                                me.top,
                                margin =
                                if ((this@with.me.homeFlowerUiModel.flowerType as Flower).growType >= Stage.Third) {
                                    0.dp
                                } else {
                                    if (cheerState in listOf(CheerState.CheerOnlyPartner, CheerState.DoNotCheerBoth)) {
                                        32.dp
                                    } else {
                                        42.dp
                                    }
                                },
                            )
                        },
                    cheerState = this.cheerState,
                    cheerText = this.me.cheerText,
                    onClickCheerButton = onClickCheerButton,
                )

                HomeFlowerMe(
                    modifier = Modifier.constrainAs(me) {
                        start.linkTo(meFlowerOwnerText.start)
                        end.linkTo(meFlowerOwnerText.end)
                        bottom.linkTo(meFlowerOwnerText.top, margin = 3.dp)
                    },
                    homeFlowerUiModel = this.me.homeFlowerUiModel,
                )
                HomeFlowerOwnerText(
                    modifier = Modifier.constrainAs(
                        meFlowerOwnerText,
                    ) {
                        linkTo(start = parent.start, end = parent.end, bias = 0.7f)
                        bottom.linkTo(parent.bottom)
                    },
                    name = this.me.homeFlowerUiModel.name,
                    userType = ME,
                )
            }
        }
    }
}

@Composable
fun HomeCheerPartner(
    cheerState: CheerState,
    modifier: Modifier = Modifier,
    cheerText: String = "",
) {
    when (cheerState) {
        CheerState.CheerOnlyPartner, CheerState.CheerBoth -> {
            HomeCheerSpeechBubble(
                modifier = modifier.testTag(
                    stringResource(R.string.homeCheerChallengePartnerBubble),
                ),
                userType = PARTNER,
                cheerText = cheerText,
            )
        }
        CheerState.DoNotCheerBoth, CheerState.CheerOnlyMe -> {
            TwoTooImageView(
                modifier = Modifier
                    .testTag(
                        stringResource(R.string.homeCheerChallengePartnerBeforeCheerBubble),
                    )
                    .size(44.dp),
                model = R.drawable.img_cheer_partner_empty,
                previewPlaceholder = R.drawable.img_cheer_partner_empty,
            )
        }
    }
}

@Composable
fun HomeCheerMe(
    cheerState: CheerState,
    modifier: Modifier = Modifier,
    cheerText: String = "",
    onClickCheerButton: () -> Unit = {},
) {
    when (cheerState) {
        CheerState.CheerOnlyMe, CheerState.CheerBoth -> {
            HomeCheerSpeechBubble(
                modifier = modifier.testTag(
                    stringResource(id = R.string.homeCheerChallengeMeBubble),
                ),
                userType = ME,
                cheerText = cheerText,
            )
        }
        CheerState.DoNotCheerBoth, CheerState.CheerOnlyPartner -> {
            HomeCheerFirstSpeech(
                modifier = modifier.testTag(
                    stringResource(id = R.string.homeCheerChallengeMeBeforeCheerBubble),
                ).padding(bottom = 32.dp).clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    onClickCheerButton()
                },
                cheerText = stringResource(id = R.string.homeCheerChallengeFirstText),
            )
        }
    }
}

@Composable
fun HomeFlowerPartner(
    homeFlowerUiModel: HomeFlowerUiModel,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp
    with(homeFlowerUiModel.flowerType.getFlowerImage(context = context, screenWidth = screenWidth, screenHeight = screenHeight)) {
        TwoTooImageView(
            modifier = modifier
                .testTag(
                    stringResource(id = R.string.homeOngoingChallengeFlowerPartnerImage),
                )
                .width(width)
                .height(height),
            model = image,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun HomeFlowerMe(
    homeFlowerUiModel: HomeFlowerUiModel,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp
    with(homeFlowerUiModel.flowerType.getFlowerImage(context = context, screenWidth = screenWidth, screenHeight = screenHeight)) {
        TwoTooImageView(
            modifier = modifier
                .testTag(
                    stringResource(id = R.string.homeOngoingChallengeFlowerMeImage),
                )
                .width(width)
                .height(height),
            model = image,
            contentScale = ContentScale.Fit,
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
                        partner = CheerWithFlower.partnerNotYet,
                        me = CheerWithFlower.meNotYet,
                    ),
                ),
            )
        }
    }
}

@Preview("둘다 응원", showBackground = true)
@Composable
private fun PreviewCheerBoth() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.default.copy(
                        cheerState = CheerState.CheerBoth,
                        partner = CheerWithFlower.partnerNotEmpty.copy(
                            cheerText = "앞으로 더 화이팅이야",
                        ),
                        me = CheerWithFlower.meNotEmpty.copy(
                            cheerText = "앞으로 더 화이팅이야",
                        ),
                    ),
                ),
            )
        }
    }
}

@Preview("나만 응원", showBackground = true)
@Composable
private fun PreviewCheerOnlyMe() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.default.copy(
                        cheerState = CheerState.CheerOnlyMe,
                        partner = CheerWithFlower.partnerNotYet,
                        me = CheerWithFlower.meNotEmpty,
                    ),
                ),
            )
        }
    }
}

@Preview("파트너만 응원", showBackground = true)
@Composable
private fun PreviewCheerOnlyPartner() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.default.copy(
                        cheerState = CheerState.CheerOnlyPartner,
                        partner = CheerWithFlower.partnerNotEmpty,
                        me = CheerWithFlower.meNotYet,
                    ),
                ),
            )
        }
    }
}

@Preview("둘다 응원안하기", showBackground = true)
@Composable
private fun PreviewDoNotCheerBoth() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.default.copy(
                        cheerState = CheerState.DoNotCheerBoth,
                        partner = CheerWithFlower.partnerNotYet,
                        me = CheerWithFlower.meNotYet,
                    ),
                ),
            )
        }
    }
}

@Preview("완료상태", showBackground = true)
@Composable
private fun PreviewComplete() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeFlowerMeAndPartner(
                modifier = Modifier.fillMaxWidth(),
                homeChallengeStateUiModel = HomeChallengeStateUiModel.complete.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authBoth.copy(
                        partner = HomeFlowerUiModel.partner.copy(
                            flowerType = Flower(
                                flowerName = FlowerName.Tulip,
                                userType = PARTNER,
                                growType = Stage.Fifth,
                            ),
                        ),
                        me = HomeFlowerUiModel.me.copy(
                            flowerType = Flower(
                                flowerName = FlowerName.Tulip,
                                userType = ME,
                                growType = Stage.Fifth,
                            ),
                        ),
                    ),
                ),
            )
        }
    }
}
