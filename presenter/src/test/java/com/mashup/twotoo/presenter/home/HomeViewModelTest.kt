package com.mashup.twotoo.presenter.home

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import com.mashup.twotoo.presenter.home.repository.FakeCommitRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewApprovedButBeforeStartDateRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewBeforeCreateRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewBeforeMyApproveRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewBeforePartnerApproveRepository
import com.mashup.twotoo.presenter.home.repository.FakeViewExpiredByNotApprovedRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import repository.CommitRepository
import repository.ViewRepository
import usecase.commit.CreateCommitUseCase
import usecase.view.GetViewHomeUseCase

class HomeViewModelTest {

    lateinit var getViewHomeUseCase: GetViewHomeUseCase
    lateinit var createCommitUseCase: CreateCommitUseCase
    lateinit var fakeViewRepository: ViewRepository
    lateinit var fakeCommitRepository: CommitRepository

    @Test
    fun getHomeViewBeforeCreateStateTest(): Unit = runTest {
        val scope = TestScope()
        fakeViewRepository = FakeViewBeforeCreateRepository()
        fakeCommitRepository = FakeCommitRepository()
        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase, createCommitUseCase)

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
        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase, createCommitUseCase)

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
        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase, createCommitUseCase)

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
        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase, createCommitUseCase)

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
        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase, createCommitUseCase)

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.termination.state)
        }
    }
}
