package com.mashup.twotoo.presenter.history

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel
import com.mashup.twotoo.presenter.history.model.ChallengeInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel
import com.mashup.twotoo.presenter.util.DateFormatter
import model.challenge.request.ChallengeNoRequestDomainModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.GetChallengeByNoUseCase
import java.util.*
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

            val commitPairs = with(challengeDetailResponseDomainModel) {
                combineLists(
                    myCommitResponseDomainModel,
                    partnerCommitResponseDomainModel,
                )
            }

            val startDate = DateFormatter.getDateTimeByStr(challengeDetailResponseDomainModel.challengeResponseDomainModel.startDate)
            val endDate = DateFormatter.getDateTimeByStr(challengeDetailResponseDomainModel.challengeResponseDomainModel.endDate)

            val challengingDates = if (challengeDetailResponseDomainModel.challengeResponseDomainModel.isFinished) {
                getDatesInRangeFromDateToToday(startDate, endDate)
            } else {
                getDatesInRangeFromDateToToday(startDate, Date()) // currentDate
            }

            val newChallengeInfoUiModel = ChallengeInfoUiModel.from(challengeDetailResponseDomainModel.challengeResponseDomainModel)
            val combineHistoryItemUiModels = commitPairs.map {
                HistoryItemUiModel.from(it.first, it.second)
            }

            val newHistoryItemUiModels: MutableList<HistoryItemUiModel> =
                getNewHistoryItemUiModels(challengingDates, combineHistoryItemUiModels)

            val newOwnerNickNamesUiModel = with(challengeDetailResponseDomainModel.challengeResponseDomainModel) {
                OwnerNickNamesUiModel.from(this.user1, this.user2)
            }

            reduce {
                state.copy(
                    challengeInfoUiModel = newChallengeInfoUiModel,
                    historyItemUiModel = newHistoryItemUiModels,
                    ownerNickNamesUiModel = newOwnerNickNamesUiModel,
                )
            }
        }.onFailure {
            Log.e("HistoryViewModel", "getChallengeByUser: ${it.message} 서버 에러!!")
        }
    }

    private fun getDatesInRangeFromDateToToday(startDate: Date, endDate: Date): List<String> {
        val calendar = Calendar.getInstance()
        calendar.time = startDate

        val datesList = mutableListOf<String>()

        while (calendar.time <= endDate) {
            datesList.add(DateFormatter.getDateStrByDate(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        datesList.reverse() // start from current date
        return datesList
    }

    private fun getNewHistoryItemUiModels(
        challengingDates: List<String>,
        combineHistoryItemUiModels: List<HistoryItemUiModel>,
    ): MutableList<HistoryItemUiModel> {
        val newHistoryItemUiModels: MutableList<HistoryItemUiModel> = mutableListOf()
        for (date in challengingDates) {
            val commit = combineHistoryItemUiModels.firstOrNull { date == it.createDate }
            if (commit != null) {
                newHistoryItemUiModels.add(commit)
            } else {
                newHistoryItemUiModels.add(
                    HistoryItemUiModel(
                        partnerInfo = HistoryInfoUiModel.empty,
                        myInfo = HistoryInfoUiModel.empty,
                        createDate = date,
                    ),
                )
            }
        }
        return newHistoryItemUiModels
    }

    private fun <T, R> combineLists(list1: List<T>, list2: List<R>): List<Pair<T?, R?>> {
        val maxSize = maxOf(list1.size, list2.size)
        val combinedList = mutableListOf<Pair<T?, R?>>()

        for (i in 0 until maxSize) {
            val element1 = list1.getOrNull(i)
            val element2 = list2.getOrNull(i)
            combinedList.add(element1 to element2)
        }

        return combinedList
    }

    fun updateChallengeDetail(commitNo: Int) = intent {
        val partnerCommits = state.historyItemUiModel.map {
            it.partnerInfo
        }
        val myCommits = state.historyItemUiModel.map {
            it.myInfo
        }
        Log.i("HistoryViewModel", "updateChallengeDetail: myCommitSize${myCommits.size}, partnerCommitSize=${partnerCommits.size}")

        var isMyCommit = true
        val commit = run {
            myCommits.firstOrNull { it.commitNo == commitNo }
        } ?: run {
            isMyCommit = false
            partnerCommits.firstOrNull { it.commitNo == commitNo }
        }

        if (commit == null) {
            Log.d("HistoryViewModel", "해당 커밋이 존재하지 않습니다")
            return@intent
        }

        val ownerNickName = if (isMyCommit) {
            OwnerNickNamesUiModel(myNickName = this.state.ownerNickNamesUiModel.myNickName, partnerName = this.state.ownerNickNamesUiModel.partnerName)
        } else {
            OwnerNickNamesUiModel(myNickName = this.state.ownerNickNamesUiModel.partnerName, partnerName = this.state.ownerNickNamesUiModel.myNickName)
        }

        reduce {
            state.copy(
                historyDetailInfoUiModel = HistoryDetailInfoUiModel(
                    infoUiModel = commit,
                    ownerNickNamesUiModel = ownerNickName,
                ),
            )
        }
    }
}
