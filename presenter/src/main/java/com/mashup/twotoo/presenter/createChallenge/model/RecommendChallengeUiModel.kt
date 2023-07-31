package com.mashup.twotoo.presenter.createChallenge.model

import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

data class RecommendChallengeUiModel(
    @StringRes val name: Int,
) {
    companion object {
        const val startTextIndex = 2
        fun getRecommendChallengeList(): List<RecommendChallengeUiModel> {
            return listOf(
                RecommendChallengeUiModel(R.string.recommend_challenge_busy_day),
                RecommendChallengeUiModel(R.string.recommend_challenge_share_daily),
                RecommendChallengeUiModel(R.string.recommend_challenge_oneday_praise),
                RecommendChallengeUiModel(R.string.recommend_challenge_mirror_selfie),
                RecommendChallengeUiModel(R.string.recommend_challenge_fashion),
                RecommendChallengeUiModel(R.string.recommend_challenge_eat_vegetable),
                RecommendChallengeUiModel(R.string.recommend_challenge_money_daily),
                RecommendChallengeUiModel(R.string.recommend_challenge_exercise),
                RecommendChallengeUiModel(R.string.recommend_challenge_weight_loss),
                RecommendChallengeUiModel(R.string.recommend_challenge_read_book),
                RecommendChallengeUiModel(R.string.recommend_challenge_miracle_morning),
            )
        }
    }
}
