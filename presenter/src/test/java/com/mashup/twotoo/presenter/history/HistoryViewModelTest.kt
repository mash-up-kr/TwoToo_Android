package com.mashup.twotoo.presenter.history

import app.cash.turbine.test
import com.mashup.twotoo.presenter.repository.FakeChallengeRepository
import com.mashup.twotoo.presenter.repository.FakeCommitRepository
import com.mashup.twotoo.presenter.repository.FakeUserRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import repository.ChallengeRepository
import repository.CommitRepository
import repository.UserRepository
import usecase.challenge.GetChallengeByNoUseCase
import usecase.challenge.QuiteChallengeUseCase
import usecase.commit.CreateCommitUseCase
import usecase.user.GetUserInfoUseCase

class HistoryViewModelTest {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var fakeUserRepository: UserRepository
    private lateinit var fakeCommitRepository: CommitRepository
    private lateinit var fakeChallengeRepository: ChallengeRepository

    private lateinit var getUserInfoUseCase: GetUserInfoUseCase
    private lateinit var createCommitUseCase: CreateCommitUseCase
    private lateinit var getChallengeByNoUserCase: GetChallengeByNoUseCase
    private lateinit var quiteChallengeUseCase: QuiteChallengeUseCase

    @Before
    fun initViewModel() {
        fakeUserRepository = FakeUserRepository()
        fakeCommitRepository = FakeCommitRepository()
        fakeChallengeRepository = FakeChallengeRepository()

        getUserInfoUseCase = GetUserInfoUseCase(userRepository = fakeUserRepository)
        createCommitUseCase = CreateCommitUseCase(commitRepository = fakeCommitRepository)
        getChallengeByNoUserCase = GetChallengeByNoUseCase(challengeRepository = fakeChallengeRepository)
        quiteChallengeUseCase = QuiteChallengeUseCase(challengeRepository = fakeChallengeRepository)

        historyViewModel = HistoryViewModel(
            getUserInfoUseCase = getUserInfoUseCase,
            createCommitUseCase = createCommitUseCase,
            getChallengeByNoUseCase = getChallengeByNoUserCase,
            quiteChallengeUseCase = quiteChallengeUseCase,
        )
    }

    /*
    generate되는 list 보기용
     */
    @Test
    fun getCommitList(): Unit = runTest {
        with(historyViewModel) {
            container.stateFlow.test {
                this.awaitItem()
                getChallengeByUser(0).join()
                this.awaitItem()
                println(container.stateFlow.value)
            }
        }
    }
}
