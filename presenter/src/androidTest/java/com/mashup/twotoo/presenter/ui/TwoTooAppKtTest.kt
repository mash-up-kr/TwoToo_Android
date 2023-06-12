package com.mashup.twotoo.presenter.ui

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.BackgroundYellow
import com.mashup.twotoo.presenter.designsystem.theme.MainPink
import com.mashup.twotoo.presenter.garden.navigation.GardenNavigationRoute
import com.mashup.twotoo.presenter.home.navigation.HomeNavigationRoute
import com.mashup.twotoo.presenter.navigation.TopLevelDestination
import com.mashup.twotoo.presenter.twotoo.TwoTooApp
import com.mashup.twotoo.presenter.user.navigation.UserNavigationRoute
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * TwoTooApp 파일 테스트
 * Composable의 Color에 접근할 수 없어서, 이를 TwoTooAppState에서 현재 컬러를 반환하는 함수를 작성하였습니다.
 *
 * * [setupAppNavHost] :  테스트를 진행하기전, Navigation 세팅을 진행하고 TwoTooApp을 Display합니다.
 */
class TwoTooAppKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var twoTooAppState: TwoTooAppState
    private lateinit var navController: TestNavHostController
    private lateinit var context: Context

    @Before
    fun setupAppNavHost() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            twoTooAppState = rememberTwoTooAppState(
                navController = navController,
            )
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            TwoTooApp(appState = twoTooAppState)
        }
    }

    private fun setDestinationHome() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            twoTooAppState.navigationToTopLevelDestination(TopLevelDestination.Home)
        }
    }

    private fun setDestinationGarden() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            twoTooAppState.navigationToTopLevelDestination(TopLevelDestination.Garden)
        }
    }

    private fun setDestinationUser() {
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            twoTooAppState.navigationToTopLevelDestination(TopLevelDestination.User)
        }
    }

    @Test
    fun 경로가_홈일때_경로가_홈이_맞는가() {
        setDestinationHome()

        // 현재 Destination이 Home인지 검증
        assertThat(navController.currentDestination?.route).isEqualTo(HomeNavigationRoute)
    }

    @Test
    fun 경로가_홈일때_바텀네비게이션색상이_핑크색상인가() {
        setDestinationHome()

        // 현재 Destination인 TwoTooBottomBar Background 색상 검증
        assertThat(twoTooAppState.getContainerColorByDestinationForTest()).isEqualTo(MainPink)
    }

    @Test
    fun 경로가_홈일때_다른_스크린_아이콘이_노랑색상인가() {
        setDestinationHome()

        // 현재 Destination이 아닌 아이콘의 색상 검증
        assertThat(twoTooAppState.getUnSelectedColorByDestinationForTest()).isEqualTo(BackgroundYellow)
    }

    @Test
    fun 경로가_홈일때_홈스크린이_보이는가() {
        setDestinationHome()

        // 현재 HomeScreen이 보이는 지 검증
        composeTestRule.onNodeWithTag(context.getString(R.string.home)).assertExists()

        // 현재 UserScreen이 보이지 않는지 검증
        composeTestRule.onNodeWithTag(context.getString(R.string.user)).assertDoesNotExist()

        // 현재 GardenScreen이 보이지 않는지 검증
        composeTestRule.onNodeWithTag(context.getString(R.string.garden)).assertDoesNotExist()
    }

    @Test
    fun 경로가_가든일때_경로가_가든이_맞는가() {
        setDestinationGarden()
        assertThat(navController.currentDestination?.route).isEqualTo(GardenNavigationRoute)
    }

    @Test
    fun 경로가_가든일때_바텀네비게이션색상이_노랑색상인가() {
        setDestinationGarden()
        assertThat(twoTooAppState.getContainerColorByDestinationForTest()).isEqualTo(BackgroundYellow)
    }

    @Test
    fun 경로가_가든일때_다른_스크린_아이콘이_핑크색상인가() {
        setDestinationGarden()
        assertThat(twoTooAppState.getUnSelectedColorByDestinationForTest()).isEqualTo(MainPink)
    }

    @Test
    fun 경로가_가든일때_가든스크린이_보이는가() {
        setDestinationGarden()
        composeTestRule.onNodeWithTag(context.getString(R.string.garden)).assertExists()
        composeTestRule.onNodeWithTag(context.getString(R.string.user)).assertDoesNotExist()
        composeTestRule.onNodeWithTag(context.getString(R.string.home)).assertDoesNotExist()
    }

    @Test
    fun 경로가_유저일때_경로가_유저가_맞는가() {
        setDestinationUser()
        assertThat(navController.currentDestination?.route).isEqualTo(UserNavigationRoute)
    }

    @Test
    fun 경로가_유저일때_바텀네비게이션_색상이_노랑인가() {
        setDestinationUser()
        assertThat(twoTooAppState.getContainerColorByDestinationForTest()).isEqualTo(BackgroundYellow)
    }

    @Test
    fun 경로가_유저일때_다른_스크린_아이콘이_핑크색상인가() {
        setDestinationUser()
        assertThat(twoTooAppState.getUnSelectedColorByDestinationForTest()).isEqualTo(MainPink)
    }

    @Test
    fun 경로가_유저일때_유저스크린이_보이는가() {
        setDestinationUser()
        composeTestRule.onNodeWithTag(context.getString(R.string.user)).assertExists()
        composeTestRule.onNodeWithTag(context.getString(R.string.garden)).assertDoesNotExist()
        composeTestRule.onNodeWithTag(context.getString(R.string.home)).assertDoesNotExist()
    }
}
