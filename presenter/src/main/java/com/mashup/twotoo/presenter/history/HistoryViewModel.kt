package com.mashup.twotoo.presenter.history

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetData
import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel
import com.mashup.twotoo.presenter.history.model.ChallengeInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel
import com.mashup.twotoo.presenter.util.DateFormatter
import model.challenge.request.ChallengeNoRequestDomainModel
import model.commit.request.CommitRequestDomainModel
import model.commit.response.CommitResponseDomainModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.GetChallengeByNoUseCase
import usecase.challenge.QuiteChallengeUseCase
import usecase.commit.CreateCommitUseCase
import usecase.user.GetUserInfoUseCase
import java.util.*
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val createCommitUseCase: CreateCommitUseCase,
    private val getChallengeByNoUseCase: GetChallengeByNoUseCase,
    private val quiteChallengeUseCase: QuiteChallengeUseCase,
) : ContainerHost<HistoryState, Nothing>, ViewModel() {
    override val container: Container<HistoryState, Nothing> = container(
        HistoryState(),
    )

    fun onClickBottomSheetDataButton(bottomSheetData: BottomSheetData) = intent {
        val bottomSheetAuthenticateData = (bottomSheetData as BottomSheetData.AuthenticateData)
        createCommitUseCase(
            commitRequestDomainModel = CommitRequestDomainModel(
                text = bottomSheetAuthenticateData.text,
                challengeNo = this.state.challengeInfoUiModel.challengeNo.toString(),
                img = bottomSheetAuthenticateData.image.toString(),
            ),
        ).onSuccess {
            Log.i("HistoryViewModel", "onClickBottomSheetDataButton: Success")
            getChallengeByUser(this.state.challengeInfoUiModel.challengeNo)
        }.onFailure {
            Log.i("HistoryViewModel", "onClickBottomSheetDataButton: Failed, message=${it.message}")
        }
    }

    fun getChallengeByUser(challengeNo: Int) = intent {
        reduce {
            state.copy(
                loadingIndicatorState = true,
            )
        }

        getChallengeByNoUseCase(ChallengeNoRequestDomainModel(challengeNo)).onSuccess { challengeDetailResponseDomainModel ->
            val userInfo = getUserInfoUseCase().getOrNull()
            if (userInfo == null) {
                Log.e("HistoryViewModel", "getChallengeByUser: not saved userInfo")
                return@onSuccess
            }
            val userNo = userInfo.userNo

            // Todo domain attribute name pater, me 말고 user1, user2로 바꾸기 또는 데이터 만들어서 아래 userName이랑 같이 관리하기
            val (myCommits, partnerCommits) =
                with(challengeDetailResponseDomainModel) {
                    if (userNo == this.challengeResponseDomainModel.user1.userNo) {
                        Pair(
                            this.myCommitResponseDomainModel,
                            this.partnerCommitResponseDomainModel,
                        )
                    } else {
                        Pair(
                            this.partnerCommitResponseDomainModel,
                            this.myCommitResponseDomainModel,
                        )
                    }
                }

            val newChallengeInfoUiModel =
                ChallengeInfoUiModel.from(challengeDetailResponseDomainModel.challengeResponseDomainModel)

            val newHistoryItemUiModels: MutableList<HistoryItemUiModel> =
                getNewHistoryItemUiModels(
                    _startDate = challengeDetailResponseDomainModel.challengeResponseDomainModel.startDate,
                    _endDate = challengeDetailResponseDomainModel.challengeResponseDomainModel.endDate,
                    isFinished = challengeDetailResponseDomainModel.challengeResponseDomainModel.isFinished,
                    myCommits = myCommits,
                    partnerCommits = partnerCommits,
                )

            val newOwnerNickNamesUiModel =
                with(challengeDetailResponseDomainModel.challengeResponseDomainModel) {
                    val (myInfo, partnerInfo) = if (userNo == this.user1.userNo) {
                        Pair(
                            this.user1,
                            this.user2,
                        )
                    } else {
                        Pair(this.user2, this.user1)
                    }
                    OwnerNickNamesUiModel.from(myInfo, partnerInfo)
                }

            reduce {
                state.copy(
                    loadingIndicatorState = false,
                    challengeInfoUiModel = newChallengeInfoUiModel,
                    historyItemUiModel = newHistoryItemUiModels,
                    ownerNickNamesUiModel = newOwnerNickNamesUiModel,
                )
            }
        }.onFailure {
            Log.e("HistoryViewModel", "getChallengeByUser: ${it.message} 서버 에러!!")
        }
    }

    private fun getNewHistoryItemUiModels(
        _startDate: String,
        _endDate: String,
        isFinished: Boolean,
        myCommits: List<CommitResponseDomainModel>,
        partnerCommits: List<CommitResponseDomainModel>,
    ): MutableList<HistoryItemUiModel> {
        val startDate =
            DateFormatter.dateConvertToPlusNineDate(_startDate)
        val endDate =
            DateFormatter.dateConvertToPlusNineDate(_endDate)

        val challengingDates =
            if (isFinished) {
                getDatesInRangeFromStartDateToEndDate(startDate, endDate)
            } else {
                // 현재 시간 전날 23:59:59로 변경해야한다.
                val currentDate = Calendar.getInstance()
                currentDate.add(Calendar.DAY_OF_MONTH, -1)
                val year = currentDate.get(Calendar.YEAR)
                val month = currentDate.get(Calendar.MONTH)
                val day = currentDate.get(Calendar.DAY_OF_MONTH)
                currentDate.set(
                    year,
                    month,
                    day,
                    23,
                    59,
                    59,
                )

                getDatesInRangeFromStartDateToEndDate(startDate, currentDate.time) // currentDate
            }

        val commitPairs =
            combineLists(
                myCommits,
                partnerCommits,
            )

        val historyItemsWithCommit = commitPairs.map {
            HistoryItemUiModel.from(it.first, it.second)
        }

        return getNewHistoryItemsCombinedBetween(challengingDates, historyItemsWithCommit)
    }

    private fun getDatesInRangeFromStartDateToEndDate(startDate: Date, endDate: Date): List<String> {
        val calendar = Calendar.getInstance()
        calendar.time = startDate

        val datesList = mutableListOf<String>()

        while (calendar.time <= endDate) {
            datesList.add(DateFormatter.getDateStrMonthDay(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        datesList.reverse() // start from current date
        return datesList
    }

    private fun getNewHistoryItemsCombinedBetween(
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

    fun quiteChallenge(challengeNo: Int) = intent {
        quiteChallengeUseCase(ChallengeNoRequestDomainModel(challengeNo)).onSuccess {
            Log.i("HistoryViewModel", "quiteChallenge: 챌린지 삭제완료")
        }.onFailure {
            Log.i("HistoryViewModel", "quiteChallenge: 챌린지 삭제 실패")
        }
    }
}
