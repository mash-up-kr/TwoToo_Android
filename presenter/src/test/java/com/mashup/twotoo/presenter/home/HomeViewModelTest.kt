package com.mashup.twotoo.presenter.home

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.repository.FakeChallengeRepository
import com.mashup.twotoo.presenter.home.repository.FakeCommitRepository
import com.mashup.twotoo.presenter.home.repository.FakeUserDataStoreRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewApprovedButBeforeStartDateRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewBeforeCreateRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewBeforeMyApproveRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewBeforePartnerApproveRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewExpiredByNotApprovedRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import repository.ChallengeRepository
import repository.CommitRepository
import repository.UserDataStoreRepository
import repository.ViewRepository
import usecase.challenge.FinishChallengeWithNoUseCase
import usecase.commit.CreateCommitUseCase
import usecase.user.GetVisibilityCheerDialogUseCase
import usecase.user.GetVisibilityCompleteDialogUseCase
import usecase.user.SetVisibilityCheerDialogUseCase
import usecase.user.SetVisibilityCompleteDialogUseCase
import usecase.view.GetViewHomeUseCase

class HomeViewModelTest {

    lateinit var getViewHomeUseCase: GetViewHomeUseCase
    lateinit var createCommitUseCase: CreateCommitUseCase
    lateinit var getVisibilityCheerDialogUseCase: GetVisibilityCheerDialogUseCase
    lateinit var getVisibilityCompleteDialogUseCase: GetVisibilityCompleteDialogUseCase
    lateinit var setVisibilityCheerDialogUseCase: SetVisibilityCheerDialogUseCase
    lateinit var setVisibilityCompleteDialogUseCase: SetVisibilityCompleteDialogUseCase
    lateinit var finishChallengeWithNoUseCase: FinishChallengeWithNoUseCase

    lateinit var fakeViewRepository: ViewRepository
    lateinit var fakeCommitRepository: CommitRepository
    lateinit var userDataStoreRepository: UserDataStoreRepository
    lateinit var fakeChallengeRepository: ChallengeRepository

    @Before
    fun initUserDataStoreRepository() {
        userDataStoreRepository = FakeUserDataStoreRepository()
        getVisibilityCheerDialogUseCase = GetVisibilityCheerDialogUseCase(userDataStoreRepository)
        getVisibilityCompleteDialogUseCase = GetVisibilityCompleteDialogUseCase(userDataStoreRepository)
        setVisibilityCheerDialogUseCase = SetVisibilityCheerDialogUseCase(userDataStoreRepository)
        setVisibilityCompleteDialogUseCase = SetVisibilityCompleteDialogUseCase(userDataStoreRepository)
    }

    @Test
    fun getHomeViewBeforeCreateStateTest(): Unit = runTest {
        val scope = TestScope()
        fakeViewRepository = FakeViewBeforeCreateRepository()
        fakeCommitRepository = FakeCommitRepository()
        fakeChallengeRepository = FakeChallengeRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        finishChallengeWithNoUseCase = FinishChallengeWithNoUseCase(fakeChallengeRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
        )

        viewModel.getHomeViewChallenge().join()
        scope.launch {
            viewModel.container.stateFlow.collect {
                assertEquals(it, BeforeChallengeUiModel.empty)
            }
        }
    }

    suspend fun TurbineTestContext<ChallengeStateTypeUiModel>.assertInitState() {
        assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.empty.state)
    }

    @Test
    fun getHomeViewBeforePartnerApproveStateTest(): Unit = runTest {
        fakeViewRepository = FakeViewBeforePartnerApproveRepository()
        fakeCommitRepository = FakeCommitRepository()
        fakeChallengeRepository = FakeChallengeRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        finishChallengeWithNoUseCase = FinishChallengeWithNoUseCase(fakeChallengeRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
        )

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.request.state)
        }
    }

    @Test
    fun getHomeViewBeforeMyApproveStateTest(): Unit = runTest {
        fakeViewRepository = FakeViewBeforeMyApproveRepository()
        fakeCommitRepository = FakeCommitRepository()
        fakeChallengeRepository = FakeChallengeRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        finishChallengeWithNoUseCase = FinishChallengeWithNoUseCase(fakeChallengeRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
        )

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.response.state)
        }
    }

    @Test
    fun getHomeViewApprovedButBeforeStartDateStateTest(): Unit = runTest {
        fakeViewRepository = FakeViewApprovedButBeforeStartDateRepository()
        fakeCommitRepository = FakeCommitRepository()
        fakeChallengeRepository = FakeChallengeRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        finishChallengeWithNoUseCase = FinishChallengeWithNoUseCase(fakeChallengeRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
        )

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.wait.state)
        }
    }

    @Test
    fun getHomeViewExpiredByNotApprovedStateTest(): Unit = runTest {
        fakeViewRepository = FakeViewExpiredByNotApprovedRepository()
        fakeCommitRepository = FakeCommitRepository()
        fakeChallengeRepository = FakeChallengeRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        finishChallengeWithNoUseCase = FinishChallengeWithNoUseCase(fakeChallengeRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
        )

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.termination.state)
        }
    }
}
