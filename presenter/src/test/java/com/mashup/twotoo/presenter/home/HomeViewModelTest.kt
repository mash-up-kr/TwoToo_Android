package com.mashup.twotoo.presenter.home

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.ChallengeStateTypeUiModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import repository.ViewRepository
import usecase.view.GetViewHomeUseCase

class HomeViewModelTest {

    lateinit var getViewHomeUseCase: GetViewHomeUseCase
    lateinit var fakeRepository: ViewRepository

    @Test
    fun getHomeViewBeforeCreateStateTest(): Unit = runTest {
        val scope = TestScope()
        fakeRepository = FakeViewBeforeCreateRepository()
        getViewHomeUseCase = GetViewHomeUseCase(fakeRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase)

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
        fakeRepository = FakeViewBeforePartnerApproveRepository()
        getViewHomeUseCase = GetViewHomeUseCase(fakeRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase)

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.request.state)
        }
    }

    @Test
    fun getHomeViewBeforeMyApproveStateTest(): Unit = runTest {
        fakeRepository = FakeViewBeforeMyApproveRepository()
        getViewHomeUseCase = GetViewHomeUseCase(fakeRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase)

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.response.state)
        }
    }

    @Test
    fun getHomeViewApprovedButBeforeStartDateStateTest(): Unit = runTest {
        fakeRepository = FakeViewApprovedButBeforeStartDateRepository()
        getViewHomeUseCase = GetViewHomeUseCase(fakeRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase)

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.wait.state)
        }
    }

    @Test
    fun getHomeViewExpiredByNotApprovedStateTest(): Unit = runTest {
        fakeRepository = FakeViewExpiredByNotApprovedRepository()
        getViewHomeUseCase = GetViewHomeUseCase(fakeRepository)
        val viewModel = HomeViewModel(getViewHomeUseCase)

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals((awaitItem() as BeforeChallengeUiModel).state, BeforeChallengeUiModel.termination.state)
        }
    }
}
