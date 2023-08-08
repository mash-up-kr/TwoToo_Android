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
        route = NavigationRoute.GardenGraph.route,
    ),
    Home(
        icon = DrawableResourceIcon(TwoTooIcon.HomeIcon),
        iconTextId = R.string.home,
        buttonTitleTextId = R.string.home_nav_button,
        route = NavigationRoute.HomeGraph.route,
    ),
    User(
        icon = DrawableResourceIcon(TwoTooIcon.UserIcon),
        iconTextId = R.string.mypage,
        buttonTitleTextId = R.string.user_nav_button,
        route = NavigationRoute.UserGraph.route,
    ),
    ;

    companion object {
        fun findBy(route: String): TopLevelDestination? {
            return values().firstOrNull { it.route == route }
        }
    }
}

sealed class NavigationRoute(val route: String) {
    object SplashGraph : NavigationRoute("splash") {
        object SplashScreen : NavigationRoute("splash/screen")
    }
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

    object HomeGraph : NavigationRoute("home") {
        object HomeScreen : NavigationRoute("home/screen")
    }
    object GardenGraph : NavigationRoute("garden") {
        object GardenScreen : NavigationRoute("garden/screen")
    }

    object UserGraph : NavigationRoute("user") {
        object UserScreen : NavigationRoute("user/screen")
    }
    object HistoryGraph : NavigationRoute("history") {
        object HistoryScreen : NavigationRoute("history/screen")
        object HistoryDetailScreen : NavigationRoute("detail/screen")
    }

    object GuideGraph : NavigationRoute("guide") {
        object GuideWebView : NavigationRoute("guide/screen")
    }
}
