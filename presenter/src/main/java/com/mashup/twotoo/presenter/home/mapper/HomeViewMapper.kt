package com.mashup.twotoo.presenter.home.mapper

import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeState
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
import com.mashup.twotoo.presenter.home.model.StateTitleUiModel
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.model.Stage
import com.mashup.twotoo.presenter.model.toFlowerName
import model.challenge.response.ChallengeResponseDomainModel
import model.challenge.response.HomeViewResponseDomainModel
import model.challenge.response.UserCommitResponseDomainModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

fun HomeViewResponseDomainModel.toUiModel(): ChallengeStateTypeUiModel {
    return if (viewState.isBeforeChallengeState()) {
        this.toBeforeChallengeUiModel()
    } else {
        this.toOngoingChallengeUiModel()
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

fun HomeViewResponseDomainModel.toBeforeChallengeUiModel(): BeforeChallengeUiModel {
    val homeGoalCountUiModel = HomeGoalCountUiModel.default.copy(
        partnerName = partnerInfo.nickname,
        myName = myInfo.nickname,
    )

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
                stateTitleUiModel = StateTitleUiModel.response.copy(
                    partnerName = partnerInfo.nickname,
                ),
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

fun HomeViewResponseDomainModel.toOngoingChallengeUiModel(): OngoingChallengeUiModel {
    return OngoingChallengeUiModel(
        challengeNo = this.onGoingChallenge!!.challengeNo,
        homeChallengeStateUiModel = toHomeChallengeStateUiModel(),
        homeGoalAchievePartnerAndMeUiModel = toHomeGoalAchievePartnerAndMeUiModel(),
        homeGoalCountUiModel = toHomeGoalCountUiModel(),
        homeGoalFieldUiModel = this.onGoingChallenge!!.toHomeGoalFieldUiModel(),
        homeShotCountTextUiModel = toHomeShotCountTextUiModel(),
    )
}

fun HomeViewResponseDomainModel.toHomeChallengeStateUiModel(): HomeChallengeStateUiModel {
    return if (isCompleteState()) {
        HomeChallengeStateUiModel.complete.copy(
            challengeStateUiModel = toHomeFlowerPartnerAndMeUiModel(
                challengeState = ChallengeState.Complete,
            ),
        )
    } else if (isCheerState()) {
        HomeChallengeStateUiModel.cheer.copy(
            challengeStateUiModel = toHomeCheerUiModel(),
        )
    } else {
        HomeChallengeStateUiModel.auth.copy(
            challengeStateUiModel = toHomeFlowerPartnerAndMeUiModel(
                challengeState = ChallengeState.Auth,
            ),
        )
    }
}

fun HomeViewResponseDomainModel.toHomeGoalAchievePartnerAndMeUiModel(): HomeGoalAchievePartnerAndMeUiModel {
    val meUserNo = myInfo.userNo
    val (me, partner) = toHomeGoalAchieveUiModel(userNo = meUserNo)

    return HomeGoalAchievePartnerAndMeUiModel.default.copy(
        partner = partner,
        me = me,
    )
}

fun HomeViewResponseDomainModel.toHomeGoalCountUiModel(): HomeGoalCountUiModel {
    val (meNickName, partnerNickName) = getUserNickName()

    val count = challengeTotal

    return HomeGoalCountUiModel(
        partnerName = partnerNickName,
        myName = meNickName,
        count = count,
    )
}

fun ChallengeResponseDomainModel.toHomeGoalFieldUiModel(): HomeGoalFieldUiModel {
    val timeZone = TimeZone.getTimeZone("UTC")
    val calendar = Calendar.getInstance(timeZone)
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
    simpleDateFormat.timeZone = timeZone

    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)
    val endDate = formatter.parse(endDate)

    val currentDateString = simpleDateFormat.format(calendar.time)
    val currentDate = formatter.parse(currentDateString)

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
        count = 5 - this.myStingCnt,
    )
}

fun HomeViewResponseDomainModel.isCheerState(): Boolean {
    /*
    모두 인증을 했을 경우에만 cheerState로 이동
     */
    return myCommit != null && partnerCommit != null
}

fun HomeViewResponseDomainModel.isCompleteState(): Boolean {
    return viewState == "COMPLETE"
}

fun HomeViewResponseDomainModel.getUserCommit(): Pair<UserCommitResponseDomainModel?, UserCommitResponseDomainModel?> {
    return Pair(myCommit, partnerCommit)
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

fun HomeViewResponseDomainModel.toHomeCheerUiModel(): HomeCheerUiModel {
    val meUserNo = this.myInfo.userNo
    val (me, partner) = getUserCommit()
    val (meNickName, partnerNickName) = getUserNickName()
    val (meFlower, partnerFlower) = this.onGoingChallenge!!.getFlowerType(userNo = meUserNo)

    return when {
        me!!.partnerComment.isNotBlank() && partner!!.partnerComment.isNotBlank() -> {
            // 둘다 응원을 했을 경우
            HomeCheerUiModel.cheerBoth.copy(
                cheerState = CheerState.CheerBoth,
                partner = CheerWithFlower.partnerNotEmpty.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.partner.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                    cheerText = me.partnerComment.multiLineConverter(),
                    commitNo = partner.commitNo,
                ),
                me = CheerWithFlower.meNotEmpty.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = meNickName,
                        flowerType = meFlower,
                    ),
                    cheerText = partner.partnerComment.multiLineConverter(),
                    commitNo = me.commitNo,
                ),
            )
        }

        me.partnerComment.isBlank() && partner!!.partnerComment.isNotBlank() -> {
            // 나만 응원을 했을 경우
            HomeCheerUiModel.cheerOnlyMe.copy(
                cheerState = CheerState.CheerOnlyMe,
                partner = CheerWithFlower.partnerNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.partner.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                    commitNo = partner.commitNo,
                ),
                me = CheerWithFlower.meNotEmpty.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = meNickName,
                        flowerType = meFlower,
                    ),
                    cheerText = partner.partnerComment.multiLineConverter(), // 파트너의 문구에서 나의 문구를 찾아야함.
                    commitNo = me.commitNo,
                ),
            )
        }

        me.partnerComment.isNotBlank() && partner!!.partnerComment.isBlank() -> {
            // 파트너만 응원을 했을 경우
            HomeCheerUiModel.cheerOnlyPartner.copy(
                cheerState = CheerState.CheerOnlyPartner,
                partner = CheerWithFlower.partnerNotEmpty.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.partner.copy(
                        name = partnerNickName,
                        flowerType = partnerFlower,
                    ),
                    cheerText = me.partnerComment.multiLineConverter(),
                    commitNo = partner.commitNo,
                ),
                me = CheerWithFlower.meNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = meNickName,
                        flowerType = meFlower,
                    ),
                    commitNo = me.commitNo,
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
                    commitNo = partner.commitNo,
                ),
                me = CheerWithFlower.meNotYet.copy(
                    homeFlowerUiModel = HomeFlowerUiModel.me.copy(
                        name = meNickName,
                        flowerType = meFlower,
                    ),
                    commitNo = me.commitNo,
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
                        name = meNickName,
                        flowerType = meFlower,
                    ),
                ),
            )
        }
    }
}

fun HomeViewResponseDomainModel.getUserNickName(): Pair<String, String> {
    return Pair(this.myInfo.nickname, this.partnerInfo.nickname)
}

fun ChallengeResponseDomainModel.getFlowerType(
    userNo: Int,
): Pair<Flower, Flower> {
    val (meGrowType, partnerGrowType) = getGrowType(userNo = userNo)
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
            growType = partnerGrowType,
        )

        val user2 = Flower(
            flowerName = user2Flower.toFlowerName(),
            userType = UserType.ME,
            growType = meGrowType,
        )
        Pair(user2, user1)
    }
}

fun ChallengeResponseDomainModel.getGrowType(
    userNo: Int,
): Pair<Stage, Stage> {
    return if (userNo == user1.userNo) {
        Pair(
            user1CommitCnt.toGrowState(),
            user2CommitCnt.toGrowState(),
        )
    } else {
        Pair(
            user2CommitCnt.toGrowState(),
            user1CommitCnt.toGrowState(),
        )
    }
}
fun Int.toGrowState(): Stage {
    return when (this) {
        in 0..0 -> Stage.Zero
        in 1..4 -> Stage.First
        in 5..9 -> Stage.Second
        in 10..15 -> Stage.Third
        in 16..21 -> Stage.Fourth
        in 22..22 -> Stage.Fifth
        else -> Stage.Third
    }
}

fun HomeViewResponseDomainModel.toHomeFlowerPartnerAndMeUiModel(
    challengeState: ChallengeState,
): HomeFlowerPartnerAndMeUiModel {
    val meUserNo = this.myInfo.userNo
    val (meCommit, partnerCommit) = getUserCommit()
    val (meNickName, partnerNickName) = getUserNickName()
    val (meFlower, partnerFlower) = this.onGoingChallenge!!.getFlowerType(userNo = meUserNo)

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

        this.onGoingChallenge!!.isFirstChallengeButAuthOnlyPartner(meUserNo) -> {
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

        meCommit == null && partnerCommit == null -> {
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

        meCommit != null && partnerCommit == null -> {
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

        meCommit == null && partnerCommit != null -> {
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
    val (meNickName, partnerNickName) = getUserNickName()

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
        0 -> 0f
        in 1..19 -> (this / 20f)
        20, 21 -> 0.99f
        22 -> 1f
        else -> 1f
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
