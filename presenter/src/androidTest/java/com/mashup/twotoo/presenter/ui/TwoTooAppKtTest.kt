package com.mashup.twotoo.presenter.ui

import android.content.Context
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.google.common.truth.Truth.assertThat
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.BackgroundYellow
import com.mashup.twotoo.presenter.designsystem.theme.MainPink
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.garden.navigation.GardenNavigationRoute
import com.mashup.twotoo.presenter.home.navigation.HomeNavigationRoute
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
        composeTestRule.setContent {
            TwoTooTheme {
                context = LocalContext.current

                navController = TestNavHostController(context)
                navController.navigatorProvider.addNavigator(ComposeNavigator())

                twoTooAppState = rememberTwoTooAppState(
                    navController = navController,
                )
                TwoTooApp(appState = twoTooAppState)
            }
        }
    }

    private fun homeNavButtonClick() {
        composeTestRule.onNodeWithTag(
            context.getString(R.string.home_nav_button),
        ).performClick()
    }

    private fun gardenNavButtonClick() {
        composeTestRule.onNodeWithTag(
            context.getString(R.string.garden_nav_button),
        ).performClick()
    }

    private fun userNavButtonClick() {
        composeTestRule.onNodeWithTag(
            context.getString(R.string.user_nav_button),
        ).performClick()
    }

    @Test
    fun 경로가_홈일때_바텀네비게이션색상이_핑크색상인가() {
        homeNavButtonClick()

        // 현재 Destination인 TwoTooBottomBar Background 색상 검증
        val array = IntArray(20)

        composeTestRule.onNodeWithTag(
            context.getString(R.string.bottom_navigation_bar),
        ).captureToImage().readPixels(array, startY = 0, startX = 0, width = 5, height = 4)
        array.forEach {
            assertThat(it).isEqualTo(MainPink.convert(ColorSpaces.Srgb).hashCode())
        }
    }

    @Test
    fun 경로가_홈일때_다른_스크린_아이콘이_노랑색상인가() {
        homeNavButtonClick()

        // 현재 Destination이 아닌 아이콘의 색상 검증

        assertThat(twoTooAppState.getUnSelectedColorByDestinationForTest()).isEqualTo(
            BackgroundYellow,
        )
    }

    @Test
    fun 경로가_홈일때_홈스크린이_보이는가() {
        homeNavButtonClick()

        // 현재 HomeScreen이 보이는 지 검증
        composeTestRule.onNodeWithTag(context.getString(R.string.home)).assertExists()

        // 현재 UserScreen이 보이지 않는지 검증
        composeTestRule.onNodeWithTag(context.getString(R.string.user)).assertDoesNotExist()

        // 현재 GardenScreen이 보이지 않는지 검증
        composeTestRule.onNodeWithTag(context.getString(R.string.garden)).assertDoesNotExist()
    }

    @Test
    fun 경로가_가든일때_경로가_가든이_맞는가() {
        gardenNavButtonClick()

        assertThat(navController.currentDestination?.route).isEqualTo(GardenNavigationRoute)
        assertThat(navController.currentDestination?.route).isNotEqualTo(HomeNavigationRoute)
        assertThat(navController.currentDestination?.route).isNotEqualTo(UserNavigationRoute)
    }

    @Test
    fun 경로가_가든일때_바텀네비게이션색상이_노랑색상인가() {
        gardenNavButtonClick()

        val array = IntArray(20)

        composeTestRule.onNodeWithTag(
            context.getString(R.string.bottom_navigation_bar),
        ).captureToImage().readPixels(array, startY = 0, startX = 0, width = 5, height = 4)
        array.forEach {
            assertThat(it).isEqualTo(BackgroundYellow.convert(ColorSpaces.Srgb).hashCode())
        }
    }

    @Test
    fun 경로가_가든일때_다른_스크린_아이콘이_핑크색상인가() {
        gardenNavButtonClick()

        assertThat(twoTooAppState.getUnSelectedColorByDestinationForTest()).isEqualTo(MainPink)
    }

    @Test
    fun 경로가_가든일때_가든스크린이_보이는가() {
        gardenNavButtonClick()

        composeTestRule.onNodeWithTag(context.getString(R.string.garden)).assertExists()
        composeTestRule.onNodeWithTag(context.getString(R.string.user)).assertDoesNotExist()
        composeTestRule.onNodeWithTag(context.getString(R.string.home)).assertDoesNotExist()
    }

    @Test
    fun 경로가_유저일때_경로가_유저가_맞는가() {
        userNavButtonClick()

        assertThat(navController.currentDestination?.route).isEqualTo(UserNavigationRoute)
    }

    @Test
    fun 경로가_유저일때_바텀네비게이션_색상이_노랑인가() {
        userNavButtonClick()

        val array = IntArray(20)

        composeTestRule.onNodeWithTag(
            context.getString(R.string.bottom_navigation_bar),
        ).captureToImage().readPixels(array, startY = 0, startX = 0, width = 5, height = 4)
        array.forEach {
            assertThat(it).isEqualTo(BackgroundYellow.convert(ColorSpaces.Srgb).hashCode())
        }
    }

    @Test
    fun 경로가_유저일때_다른_스크린_아이콘이_핑크색상인가() {
        userNavButtonClick()

        assertThat(twoTooAppState.getUnSelectedColorByDestinationForTest()).isEqualTo(MainPink)
    }

    @Test
    fun 경로가_유저일때_유저스크린이_보이는가() {
        userNavButtonClick()

        composeTestRule.onNodeWithTag(context.getString(R.string.user)).assertExists()
        composeTestRule.onNodeWithTag(context.getString(R.string.garden)).assertDoesNotExist()
        composeTestRule.onNodeWithTag(context.getString(R.string.home)).assertDoesNotExist()
    }
}
