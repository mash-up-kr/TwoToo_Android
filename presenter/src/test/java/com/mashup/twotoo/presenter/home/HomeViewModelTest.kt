package com.mashup.twotoo.presenter.home

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.mashup.twotoo.presenter.home.model.BeforeChallengeState
import com.mashup.twotoo.presenter.home.model.BeforeChallengeUiModel
import com.mashup.twotoo.presenter.home.model.HomeStateUiModel
import com.mashup.twotoo.presenter.repository.FakeChallengeRepository
import com.mashup.twotoo.presenter.repository.FakeCommitRepository
import com.mashup.twotoo.presenter.repository.FakeNotificationRepository
import com.mashup.twotoo.presenter.repository.FakeUserDataStoreRepository
import com.mashup.twotoo.presenter.repository.FakeViewApprovedButBeforeStartDateRepository
import com.mashup.twotoo.presenter.repository.FakeViewBeforeCreateRepository
import com.mashup.twotoo.presenter.repository.FakeViewBeforeMyApproveRepository
import com.mashup.twotoo.presenter.repository.FakeViewBeforePartnerApproveRepository
import com.mashup.twotoo.presenter.repository.FakeViewExpiredByNotApprovedRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import repository.ChallengeRepository
import repository.CommitRepository
import repository.NotificationRepository
import repository.UserDataStoreRepository
import repository.ViewRepository
import usecase.challenge.FinishChallengeWithNoUseCase
import usecase.commit.CreateCheerUseCase
import usecase.commit.CreateCommitUseCase
import usecase.notification.StingUseCase
import usecase.user.GetVisibilityCheerDialogUseCase
import usecase.user.GetVisibilityCompleteDialogUseCase
import usecase.user.RemoveVisibilityCheerDialogUseCase
import usecase.user.RemoveVisibilityCompleteDialogUseCase
import usecase.user.SetVisibilityCheerDialogUseCase
import usecase.user.SetVisibilityCompleteDialogUseCase
import usecase.view.GetViewHomeUseCase

class HomeViewModelTest {

    private lateinit var getViewHomeUseCase: GetViewHomeUseCase
    private lateinit var createCommitUseCase: CreateCommitUseCase
    private lateinit var getVisibilityCheerDialogUseCase: GetVisibilityCheerDialogUseCase
    private lateinit var getVisibilityCompleteDialogUseCase: GetVisibilityCompleteDialogUseCase
    private lateinit var setVisibilityCheerDialogUseCase: SetVisibilityCheerDialogUseCase
    private lateinit var setVisibilityCompleteDialogUseCase: SetVisibilityCompleteDialogUseCase
    private lateinit var finishChallengeWithNoUseCase: FinishChallengeWithNoUseCase
    private lateinit var removeVisibilityCompleteDialogUseCase: RemoveVisibilityCompleteDialogUseCase
    private lateinit var removeVisibilityCheerDialogUseCase: RemoveVisibilityCheerDialogUseCase
    private lateinit var createCheerUseCase: CreateCheerUseCase
    private lateinit var stingUseCase: StingUseCase

    private lateinit var fakeViewRepository: ViewRepository
    private lateinit var fakeCommitRepository: CommitRepository
    private lateinit var fakeUserDataStoreRepository: UserDataStoreRepository
    private lateinit var fakeChallengeRepository: ChallengeRepository
    private lateinit var fakeNotificationRepository: NotificationRepository

    @Before
    fun initUserDataStoreRepository() {
        fakeUserDataStoreRepository = FakeUserDataStoreRepository()
        fakeCommitRepository = FakeCommitRepository()
        fakeChallengeRepository = FakeChallengeRepository()
        fakeNotificationRepository = FakeNotificationRepository()
        getVisibilityCheerDialogUseCase =
            GetVisibilityCheerDialogUseCase(fakeUserDataStoreRepository)
        getVisibilityCompleteDialogUseCase =
            GetVisibilityCompleteDialogUseCase(fakeUserDataStoreRepository)
        setVisibilityCheerDialogUseCase =
            SetVisibilityCheerDialogUseCase(fakeUserDataStoreRepository)
        setVisibilityCompleteDialogUseCase =
            SetVisibilityCompleteDialogUseCase(fakeUserDataStoreRepository)
        createCommitUseCase = CreateCommitUseCase(fakeCommitRepository)
        finishChallengeWithNoUseCase = FinishChallengeWithNoUseCase(fakeChallengeRepository)
        removeVisibilityCheerDialogUseCase =
            RemoveVisibilityCheerDialogUseCase(fakeUserDataStoreRepository)
        removeVisibilityCompleteDialogUseCase =
            RemoveVisibilityCompleteDialogUseCase(fakeUserDataStoreRepository)
        createCheerUseCase = CreateCheerUseCase(fakeCommitRepository)
        stingUseCase = StingUseCase(fakeNotificationRepository)
    }

    @Test
    fun getHomeViewBeforeCreateStateTest(): Unit = runTest {
        val scope = TestScope()
        fakeViewRepository = FakeViewBeforeCreateRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
            removeVisibilityCheerDialogUseCase,
            removeVisibilityCompleteDialogUseCase,
            createCheerUseCase,
            stingUseCase,
        )

        viewModel.getHomeViewChallenge().join()
        scope.launch {
            viewModel.container.stateFlow.collect {
                assertEquals(it.challengeStateUiModel, BeforeChallengeState.Init)
            }
        }
    }

    private suspend fun TurbineTestContext<HomeStateUiModel>.assertInitState() {
        assertEquals(
            (awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
            BeforeChallengeUiModel.init.state,
        )
    }

    @Test
    fun getHomeViewBeforePartnerApproveStateTest(): Unit = runTest {
        fakeViewRepository = FakeViewBeforePartnerApproveRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
            removeVisibilityCheerDialogUseCase,
            removeVisibilityCompleteDialogUseCase,
            createCheerUseCase,
            stingUseCase,
        )

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals(
                (this.awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
                BeforeChallengeUiModel.init.state,
            )
            assertEquals(
                (this.awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
                BeforeChallengeUiModel.request.state,
            )
        }
    }

    @Test
    fun getHomeViewBeforeMyApproveStateTest(): Unit = runTest {
        fakeViewRepository = FakeViewBeforeMyApproveRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
            removeVisibilityCheerDialogUseCase,
            removeVisibilityCompleteDialogUseCase,
            createCheerUseCase,
            stingUseCase,
        )

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals(
                (this.awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
                BeforeChallengeUiModel.init.state,
            )
            assertEquals(
                (this.awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
                BeforeChallengeUiModel.response.state,
            )
        }
    }

    @Test
    fun getHomeViewApprovedButBeforeStartDateStateTest(): Unit = runTest {
        fakeViewRepository = FakeViewApprovedButBeforeStartDateRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
            removeVisibilityCheerDialogUseCase,
            removeVisibilityCompleteDialogUseCase,
            createCheerUseCase,
            stingUseCase,
        )

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()

            assertEquals(
                (this.awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
                BeforeChallengeUiModel.init.state,
            )
            assertEquals(
                (this.awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
                BeforeChallengeUiModel.wait.state,
            )
        }
    }

    @Test
    fun getHomeViewExpiredByNotApprovedStateTest(): Unit = runTest {
        fakeViewRepository = FakeViewExpiredByNotApprovedRepository()

        getViewHomeUseCase = GetViewHomeUseCase(fakeViewRepository)

        val viewModel = HomeViewModel(
            getViewHomeUseCase,
            createCommitUseCase,
            getVisibilityCheerDialogUseCase,
            getVisibilityCompleteDialogUseCase,
            setVisibilityCheerDialogUseCase,
            setVisibilityCompleteDialogUseCase,
            finishChallengeWithNoUseCase,
            removeVisibilityCheerDialogUseCase,
            removeVisibilityCompleteDialogUseCase,
            createCheerUseCase,
            stingUseCase,
        )

        viewModel.container.stateFlow.test {
            assertInitState()
            viewModel.getHomeViewChallenge().join()
            assertEquals(
                (this.awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
                BeforeChallengeUiModel.init.state,
            )
            assertEquals(
                (this.awaitItem().challengeStateUiModel as BeforeChallengeUiModel).state,
                BeforeChallengeUiModel.termination.state,
            )
        }
    }
}
