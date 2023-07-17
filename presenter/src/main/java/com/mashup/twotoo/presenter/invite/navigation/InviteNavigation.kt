package com.mashup.twotoo.presenter.invite.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.invite.SendInvitationRoute
import com.mashup.twotoo.presenter.invite.WaitingAcceptPairRoute
import com.mashup.twotoo.presenter.invite.di.InviteComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToInvitation(
    nickname: String = "",
    partnerNo: Int? = null,
    navOptions: NavOptions? = null
) {
    this.navigate(route = "${NavigationRoute.InvitationGraph.route}?nickname=$nickname&partnerNo=$partnerNo", navOptions = navOptions)
}

private fun NavController.navigateToWaitingAcceptPair(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.InvitationGraph.route, navOptions = navOptions)
}
fun NavGraphBuilder.invitationGraph(
    navController: NavController
) {
    navigation(
        startDestination = "${NavigationRoute.InvitationGraph.route}?nickname={nickname}&partnerNo={partnerNo}",
        route = NavigationRoute.InvitationGraph.route,
    ) {
        composable(
            route = "${NavigationRoute.InvitationGraph.route}?nickname={nickname}&partnerNo={partnerNo}",
            arguments = listOf(
                navArgument("nickname") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("partnerNo") {
                    type = NavType.IntType
                    defaultValue = 0
                },
            ),
        ) { backStackEntry ->
            val nickname = backStackEntry.arguments?.getString("nickname")
            val partnerNo = backStackEntry.arguments?.getInt("partnerNo")
            Log.d(TAG, "invitationGraph: nickname=$nickname, partnerNo=$partnerNo")
            val inviteComponent = componentProvider<InviteComponentProvider>().provideInviteComponent()
            val inviteViewModel = daggerViewModel {
                inviteComponent.getViewModel()
            }
            SendInvitationRoute(inviteViewModel) { navController.navigateToWaitingAcceptPair() }
        }
        composable(route = NavigationRoute.InvitationGraph.WaitingAcceptPairScreen.route) {
            WaitingAcceptPairRoute()
        }
    }
}
