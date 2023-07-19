package com.mashup.twotoo.presenter.history

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel
import com.mashup.twotoo.presenter.history.model.ChallengeInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel
import model.challenge.request.ChallengeNoRequestDomainModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.GetChallengeByNoUseCase
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getChallengeByNoUseCase: GetChallengeByNoUseCase,
) : ContainerHost<HistoryState, Nothing>, ViewModel() {
    override val container: Container<HistoryState, Nothing> = container(
        HistoryState(),
    )

    fun getChallengeByUser(challengeNo: Int) = intent {
        getChallengeByNoUseCase(ChallengeNoRequestDomainModel(challengeNo)).onSuccess {
                challengeDetailResponseDomainModel ->

            val newHistoryItemUiModel = challengeDetailResponseDomainModel.myCommitResponseDomainModel.map {
                HistoryItemUiModel.from(it)
            }
            val newState = HistoryState(
                challengeInfoUiModel = ChallengeInfoUiModel.from(challengeDetailResponseDomainModel.challengeResponseDomainModel),
                historyItemUiModel = newHistoryItemUiModel,
                ownerNickNamesUiModel = OwnerNickNamesUiModel.from(challengeDetailResponseDomainModel.challengeResponseDomainModel.user1, challengeDetailResponseDomainModel.challengeResponseDomainModel.user2),
            )
            reduce {
                state.copy(
                    challengeInfoUiModel = newState.challengeInfoUiModel,
                    historyItemUiModel = newState.historyItemUiModel,
                    ownerNickNamesUiModel = newState.ownerNickNamesUiModel,
                )
            }
        }.onFailure {
            Log.e("HistoryViewModel", "${it.message} 서버 에러!!")
        }
    }

    fun updateChallengeDetail(commitNo: Int) = intent {
        val partnerCommits = state.historyItemUiModel.map {
            it.partnerInfo
        }
        val myCommits = state.historyItemUiModel.map {
            it.myInfo
        }

        var isMyCommit: Boolean = true
        val commit = run {
            myCommits.firstOrNull { it.commitNo == commitNo }
        } ?: run {
            isMyCommit = false
            partnerCommits.firstOrNull { it.commitNo == commitNo }
        }

        if (commit == null) {
            Log.e("HistoryViewModel", "해당 커밋이 존재하지 않습니다")
            return@intent
        }

        val ownerNickName = if (isMyCommit) {
            OwnerNickNamesUiModel(partnerName = this.state.ownerNickNamesUiModel.partnerName, myNickName = this.state.ownerNickNamesUiModel.myNickName)
        } else {
            OwnerNickNamesUiModel(partnerName = this.state.ownerNickNamesUiModel.myNickName, myNickName = this.state.ownerNickNamesUiModel.partnerName)
        }

        reduce {
            state.copy(
                historyDetailInfoUiModel = HistoryDetailInfoUiModel(
                    infoUiModel = commit,
                    ownerNickNamesUiModel = ownerNickName,
                    createdDate = "2023년 4월 20일",
                ),
            )
        }
    }
}
