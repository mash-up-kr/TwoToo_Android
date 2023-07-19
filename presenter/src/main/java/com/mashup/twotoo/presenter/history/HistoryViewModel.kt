package com.mashup.twotoo.presenter.history

import android.util.Log
import androidx.lifecycle.ViewModel
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
                this.copy(
                    state = newState,
                ).state
            }
        }.onFailure {
            Log.e("HistoryViewModel", "${it.message} 서버 에러!!")
        }
    }
}
