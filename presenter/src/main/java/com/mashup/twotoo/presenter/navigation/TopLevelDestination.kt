package com.mashup.twotoo.presenter.navigation

import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.icon.Icon
import com.mashup.twotoo.presenter.designsystem.icon.Icon.DrawableResourceIcon
import com.mashup.twotoo.presenter.designsystem.icon.TwoTooIcon

enum class TopLevelDestination(
    val icon: Icon,
    val iconTextId: Int,
    val buttonTitleTextId: Int,
    val route: String,
) {
    Garden(
        icon = DrawableResourceIcon(TwoTooIcon.GardenIcon),
        iconTextId = R.string.garden,
        buttonTitleTextId = R.string.garden_nav_button,
        route = NavigationRoute.HomeScreenGraph.GardenScreen.route,
    ),
    Home(
        icon = DrawableResourceIcon(TwoTooIcon.HomeIcon),
        iconTextId = R.string.home,
        buttonTitleTextId = R.string.home_nav_button,
        route = NavigationRoute.HomeScreenGraph.HomeScreen.route,
    ),
    User(
        icon = DrawableResourceIcon(TwoTooIcon.UserIcon),
        iconTextId = R.string.mypage,
        buttonTitleTextId = R.string.user_nav_button,
        route = NavigationRoute.HomeScreenGraph.UserScreen.route,
    ),
    ;

    companion object {
        fun findBy(route: String): TopLevelDestination? {
            return values().firstOrNull { it.route == route }
        }
    }
}

sealed class NavigationRoute(val route: String) {
    object OnBoardingGraph : NavigationRoute("onboarding") {
        object OnboardingScreen : NavigationRoute("onboarding/screen")
    }
    object NickNameSettingGraph : NavigationRoute("nickNameSetting") {
        object NickNameSettingScreen : NavigationRoute("nickNameSetting/screen")
    }
    object InvitationGraph : NavigationRoute("invitation") {
        object SendInvitationScreen : NavigationRoute("sendInvitation/screen")
        object WaitingAcceptPairScreen : NavigationRoute("waitingAccept/screen")
    }
    object CreateChallengeGraph : NavigationRoute("createChallenge") {
        object CreateChallengeScreen : NavigationRoute("createChallenge/screen")
        object SelectFlowerCardScreen : NavigationRoute("selectFlowerCard/screen")
        object SuccessChallengeRequest : NavigationRoute("successChallengeRequest/screen")
    }

    object HomeScreenGraph : NavigationRoute("home") {
        object HomeScreen : NavigationRoute("home/screen")
        object GardenScreen : NavigationRoute("home/garden_screen")
        object UserScreen : NavigationRoute("home/user_screen")
    }
    object HistoryScreenGraph : NavigationRoute("history") {
        object HistoryScreen : NavigationRoute("history/screen")
        object HistoryDetailScreen : NavigationRoute("history/detail_screen")
    }
}
