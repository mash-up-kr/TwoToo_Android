package com.mashup.twotoo.presenter.home.mapper

import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.model.CheerState
import com.mashup.twotoo.presenter.home.model.CheerWithFlower
import com.mashup.twotoo.presenter.home.model.Flower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.HomeGoalAchievePartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeGoalAchieveUiModel
import com.mashup.twotoo.presenter.home.model.HomeGoalCountUiModel
import com.mashup.twotoo.presenter.home.model.HomeGoalFieldUiModel
import com.mashup.twotoo.presenter.home.model.HomeShotCountTextUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.model.Stage
import com.mashup.twotoo.presenter.model.toFlowerName
import model.challenge.response.ChallengeResponseDomainModel
import model.challenge.response.HomeViewResponseDomainModel
import model.challenge.response.UserCommitResponseDomainModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun HomeViewResponseDomainModel.toUiModel(
    userNo: Int,
): ChallengeStateTypeUiModel {
    return if (viewState.isBeforeChallengeState()) {
        this.toBeforeChallengeUiModel(userNo)
    } else {
        this.toOngoingChallengeUiModel(userNo)
    }
}

fun String.isBeforeChallengeState(): Boolean {
    return this in listOf(
        "BEFORE_CREATE",
        "BEFORE_PARTNER_APPROVE",
        "BEFORE_MY_APPROVE",
        "APPROVED_BUT_BEFORE_START_DATE",
        "EXPIRED_BY_NOT_APPROVED",
    )
}

fun HomeViewResponseDomainModel.toBeforeChallengeUiModel(
    userNo: Int,
): BeforeChallengeUiModel {
    val homeGoalCountUiModel = if (userNo == user1.userNo) {
        HomeGoalCountUiModel.default.copy(
            partnerName = user2.nickname,
            myName = user1.nickname,
        )
    } else {
        HomeGoalCountUiModel.default.copy(
            partnerName = user1.nickname,
            myName = user2.nickname,
        )
    }

    return when (viewState) {
        "BEFORE_CREATE" -> {
            BeforeChallengeUiModel.empty.copy(
                homeGoalCountUiModel = homeGoalCountUiModel,
            )
        }

        "BEFORE_PARTNER_APPROVE" -> {
            BeforeChallengeUiModel.request.copy(
                homeGoalCountUiModel = homeGoalCountUiModel,
            )
        }

        "BEFORE_MY_APPROVE" -> {
            BeforeChallengeUiModel.response.copy(
                homeGoalCountUiModel = homeGoalCountUiModel,
            )
        }

        "APPROVED_BUT_BEFORE_START_DATE" -> {
            BeforeChallengeUiModel.wait.copy(
                homeGoalCountUiModel = homeGoalCountUiModel,
            )
        }

        "EXPIRED_BY_NOT_APPROVED" -> {
            BeforeChallengeUiModel.termination.copy(
                homeGoalCountUiModel = homeGoalCountUiModel,
            )
        }

        else -> {
            BeforeChallengeUiModel.empty.copy(
                homeGoalCountUiModel = homeGoalCountUiModel,
            )
        }
    }
}

fun HomeViewResponseDomainModel.toOngoingChallengeUiModel(
    userNo: Int,
): OngoingChallengeUiModel {
    return OngoingChallengeUiModel(
        challengeNo = this.onGoingChallenge!!.challengeNo,
        homeChallengeStateUiModel = toHomeChallengeStateUiModel(userNo = userNo),
        homeGoalAchievePartnerAndMeUiModel = toHomeGoalAchievePartnerAndMeUiModel(userNo = userNo),
        homeGoalCountUiModel = toHomeGoalCountUiModel(userNo = userNo),
        homeGoalFieldUiModel = this.onGoingChallenge!!.toHomeGoalFieldUiModel(),
        homeShotCountTextUiModel = toHomeShotCountTextUiModel(),
    )
}

fun HomeViewResponseDomainModel.toHomeChallengeStateUiModel(
    userNo: Int,
): HomeChallengeStateUiModel {
    return if (isCompleteState()) {
        HomeChallengeStateUiModel.complete.copy(
            challengeStateUiModel = toHomeFlowerPartnerAndMeUiModel(userNo = userNo),
        )
    } else if (isCheerState()) {
        HomeChallengeStateUiModel.cheer.copy(
            challengeStateUiModel = toHomeCheerUiModel(userNo = userNo),
        )
    } else {
        HomeChallengeStateUiModel.auth.copy(
            challengeStateUiModel = toHomeFlowerPartnerAndMeUiModel(userNo = userNo),
        )
    }
}

fun HomeViewResponseDomainModel.toHomeGoalAchievePartnerAndMeUiModel(
    userNo: Int,
): HomeGoalAchievePartnerAndMeUiModel {
    val (me, partner) = toHomeGoalAchieveUiModel(userNo = userNo)

    return HomeGoalAchievePartnerAndMeUiModel.default.copy(
        partner = partner,
        me = me,
    )
}

fun HomeViewResponseDomainModel.toHomeGoalCountUiModel(
    userNo: Int,
): HomeGoalCountUiModel {
    val (meNickName, partnerNickName) = this.onGoingChallenge!!.getUserNickName(userNo = userNo)

    val count = challengeTotal

    return HomeGoalCountUiModel(
        partnerName = partnerNickName,
        myName = meNickName,
        count = count,
    )
}

fun ChallengeResponseDomainModel.toHomeGoalFieldUiModel(): HomeGoalFieldUiModel {
    val currentDate = Calendar.getInstance(Locale.KOREA).time
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    val endDate = formatter.parse(endDate)

    val diff: Long = endDate.time - currentDate.time
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24

    return HomeGoalFieldUiModel(
        goal = name,
        dDay = days.toInt(),
    )
}

fun HomeViewResponseDomainModel.toHomeShotCountTextUiModel(): HomeShotCountTextUiModel {
    return HomeShotCountTextUiModel(
        count = this.userStingCnt,
    )
}

fun HomeViewResponseDomainModel.isCheerState(): Boolean {
    /*
    모두 인증을 했을 경우에만 cheerState로 이동
     */
    return user1Commit != null && user2Commit != null
}

fun HomeViewResponseDomainModel.isCompleteState(): Boolean {
    return viewState == "COMPLETE"
}

fun HomeViewResponseDomainModel.getUserCommit(
    userNo: Int,
): Pair<UserCommitResponseDomainModel?, UserCommitResponseDomainModel?> {
    return if (user1Commit!!.userNo == userNo) {
        Pair(user1Commit, user2Commit)
    } else {
        Pair(user2Commit, user1Commit)
    }
}

fun ChallengeResponseDomainModel.isFirstChallenge(): Boolean {
    return user1CommitCnt == 0 && user2CommitCnt == 0
}

fun ChallengeResponseDomainModel.isFirstChallengeButAuthOnlyPartner(
    userNo: Int,
): Boolean {
    return if (user1.userNo == userNo) {
        user1CommitCnt == 0 && user2CommitCnt != 0
    } else {
        user2CommitCnt == 0 && user1CommitCnt != 0
    }
}

fun String.multiLineConverter(): String {
    return if (this.isBlank()) {
        ""
    } else {
        val maxLength = 10
        if (this.length > maxLength) {
            val firstPart = this.substring(0, maxLength)
            val secondPart = this.substring(maxLength)
            "$firstPart\n$secondPart"
        } else {
            this
        }
    }
}

fun HomeViewResponseDomainModel.toHomeCheerUiModel(
    userNo: Int,
): HomeCheerUiModel {
    val (me, partner) = getUserCommit(userNo = userNo)

    val (meNickName, partnerNickName) = this.onGoingChallenge!!.getUserNickName(userNo = userNo)

    val (meFlower, partnerFlower) = this.onGoingChallenge!!.getFlowerType(userNo = userNo)

    return when {
        me!!.partnerComment.isNotBlank() && partner!!.partnerComment.isNotBlank() -> {
            // 둘다 응원을 했을 경우
            HomeCheerUiModel.cheerBoth.copy(
                cheerState = CheerState.DoNotCheerBoth,
                partner = CheerWithFlower.partnerNotEmpty.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.partner.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                    cheerText = partner.partnerComment.multiLineConverter(),
                ),
                me = CheerWithFlower.meNotEmpty.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = meNickName,
                        flowerType = meFlower,
                    ),

                ),
            )
        }

        me.partnerComment.isNotBlank() && partner!!.partnerComment.isBlank() -> {
            // 나만 응원을 했을 경우
            HomeCheerUiModel.cheerOnlyMe.copy(
                cheerState = CheerState.CheerOnlyMe,
                partner = CheerWithFlower.partnerNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.partner.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                ),
                me = CheerWithFlower.meNotEmpty.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                    cheerText = me.partnerComment.multiLineConverter(),
                ),
            )
        }

        me.partnerComment.isBlank() && partner!!.partnerComment.isNotBlank() -> {
            // 파트너만 응원을 했을 경우
            HomeCheerUiModel.cheerOnlyPartner.copy(
                cheerState = CheerState.CheerOnlyPartner,
                partner = CheerWithFlower.partnerNotEmpty.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.partner.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                    cheerText = partner.partnerComment.multiLineConverter(),
                ),
                me = CheerWithFlower.meNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                ),
            )
        }

        me.partnerComment.isBlank() && partner!!.partnerComment.isBlank() -> {
            // 둘다 응원을 하지 않았을 경우
            HomeCheerUiModel.doNotCheerBoth.copy(
                cheerState = CheerState.DoNotCheerBoth,
                partner = CheerWithFlower.partnerNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.partner.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                ),
                me = CheerWithFlower.meNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                ),
            )
        }

        else -> {
            HomeCheerUiModel.doNotCheerBoth.copy(
                cheerState = CheerState.DoNotCheerBoth,
                partner = CheerWithFlower.partnerNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.partner.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                ),
                me = CheerWithFlower.meNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                ),
            )
        }
    }
}

fun ChallengeResponseDomainModel.getUserNickName(
    userNo: Int,
): Pair<String, String> {
    return if (userNo == user1.userNo) {
        Pair(user1.nickname, user2.nickname)
    } else {
        Pair(user2.nickname, user1.nickname)
    }
}

fun ChallengeResponseDomainModel.getFlowerType(
    userNo: Int,
): Pair<Flower, Flower> {
    val (meGrowType, partnerGrowType) = getGrowType(userNo)
    return if (userNo == user1.userNo) {
        val user1 = Flower(
            flowerName = user1Flower.toFlowerName(),
            userType = UserType.ME,
            growType = meGrowType,
        )

        val user2 = Flower(
            flowerName = user2Flower.toFlowerName(),
            userType = UserType.PARTNER,
            growType = partnerGrowType,
        )
        Pair(user1, user2)
    } else {
        val user1 = Flower(
            flowerName = user1Flower.toFlowerName(),
            userType = UserType.PARTNER,
            growType = meGrowType,
        )

        val user2 = Flower(
            flowerName = user2Flower.toFlowerName(),
            userType = UserType.ME,
            growType = partnerGrowType,
        )
        Pair(user2, user1)
    }
}

fun ChallengeResponseDomainModel.getGrowType(
    userNo: Int,
): Pair<Stage, Stage> {
    return if (userNo == user1.userNo) {
        Pair(user1CommitCnt.toGrowState(), user2CommitCnt.toGrowState())
    } else {
        Pair(user2CommitCnt.toGrowState(), user1CommitCnt.toGrowState())
    }
}

fun Int.toGrowState(): Stage {
    return when (this) {
        in 0..5 -> {
            Stage.Zero
        }

        in 5..9 -> {
            Stage.First
        }

        in 10..14 -> {
            Stage.Second
        }

        in 15..16 -> {
            Stage.Third
        }

        in 17..21 -> {
            Stage.Fourth
        }

        in 22..22 -> {
            Stage.Fifth
        }

        else -> {
            Stage.Fifth
        }
    }
}

fun HomeViewResponseDomainModel.toHomeFlowerPartnerAndMeUiModel(
    userNo: Int,
): HomeFlowerPartnerAndMeUiModel {
    val (me, partner) = getUserCommit(userNo = userNo)

    val (meNickName, partnerNickName) = this.onGoingChallenge!!.getUserNickName(userNo = userNo)

    val (meFlower, partnerFlower) = this.onGoingChallenge!!.getFlowerType(userNo = userNo)

    return when {
        this.onGoingChallenge!!.isFirstChallenge() -> {
            HomeFlowerPartnerAndMeUiModel.firstChallenge.copy(
                partner = HomeFlowerUiModel.partner.copy(
                    flowerType = partnerFlower,
                    name = partnerNickName,
                ),
                me = HomeFlowerUiModel.me.copy(
                    flowerType = meFlower,
                    name = meNickName,
                ),
            )
        }

        this.onGoingChallenge!!.isFirstChallengeButAuthOnlyPartner(userNo) -> {
            HomeFlowerPartnerAndMeUiModel.firstChallengeButAuthOnlyPartner.copy(
                partner = HomeFlowerUiModel.partner.copy(
                    flowerType = partnerFlower,
                    name = partnerNickName,
                ),
                me = HomeFlowerUiModel.me.copy(
                    flowerType = meFlower,
                    name = meNickName,
                ),
            )
        }

        me == null && partner == null -> {
            // 둘다 인증을 하지 않았을 경우
            HomeFlowerPartnerAndMeUiModel.doNotAuthBoth.copy(
                partner = HomeFlowerUiModel.partner.copy(
                    flowerType = partnerFlower,
                    name = partnerNickName,
                ),
                me = HomeFlowerUiModel.me.copy(
                    flowerType = meFlower,
                    name = meNickName,
                ),
            )
        }

        me != null && partner == null -> {
            // 나만 인증을 했을 경우
            HomeFlowerPartnerAndMeUiModel.authOnlyMe.copy(
                partner = HomeFlowerUiModel.partner.copy(
                    flowerType = partnerFlower,
                    name = partnerNickName,
                ),
                me = HomeFlowerUiModel.me.copy(
                    flowerType = meFlower,
                    name = meNickName,
                ),
            )
        }

        me == null && partner != null -> {
            // 파트너만 인증을 했을 경우
            HomeFlowerPartnerAndMeUiModel.authOnlyPartner.copy(
                partner = HomeFlowerUiModel.partner.copy(
                    flowerType = partnerFlower,
                    name = partnerNickName,
                ),
                me = HomeFlowerUiModel.me.copy(
                    flowerType = meFlower,
                    name = meNickName,
                ),
            )
        }

        else -> {
            // 둘다 인증을 했을 경우
            HomeFlowerPartnerAndMeUiModel.authBoth.copy(
                partner = HomeFlowerUiModel.partner.copy(
                    flowerType = partnerFlower,
                    name = partnerNickName,
                ),
                me = HomeFlowerUiModel.me.copy(
                    flowerType = meFlower,
                    name = meNickName,
                ),
            )
        }
    }
}

fun HomeViewResponseDomainModel.toHomeGoalAchieveUiModel(
    userNo: Int,
): Pair<HomeGoalAchieveUiModel, HomeGoalAchieveUiModel> {
    val (meNickName, partnerNickName) = this.onGoingChallenge!!.getUserNickName(userNo = userNo)

    val (meCommitCount, partnerCommitCount) = this.onGoingChallenge!!.getUserCommitCount(userNo = userNo)

    val me = HomeGoalAchieveUiModel(
        name = meNickName,
        type = UserType.ME,
        progress = meCommitCount.toProgress(),

    )
    val partner = HomeGoalAchieveUiModel(
        name = partnerNickName,
        type = UserType.PARTNER,
        progress = partnerCommitCount.toProgress(),
    )
    return Pair(me, partner)
}

fun Int.toProgress(): Float {
    return when (this) {
        0 -> {
            0f
        }

        in 1..19 -> {
            (this / 20f)
        }

        20, 21 -> {
            0.99f
        }

        22 -> {
            1f
        }

        else -> {
            1f
        }
    }
}

fun ChallengeResponseDomainModel.getUserCommitCount(
    userNo: Int,
): Pair<Int, Int> {
    return if (user1.userNo == userNo) {
        Pair(user1CommitCnt, user2CommitCnt)
    } else {
        Pair(user2CommitCnt, user1CommitCnt)
    }
}
