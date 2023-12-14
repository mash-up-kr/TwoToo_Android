package com.mashup.twotoo.presenter.invite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.home.navigation.navigateToHome
import com.mashup.twotoo.presenter.invite.SendInvitationRoute
import com.mashup.twotoo.presenter.invite.WaitingAcceptPairRoute
import com.mashup.twotoo.presenter.invite.di.InviteComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.onboarding.navigation.navigateToOnBoarding
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToInvitation(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.InvitationGraph.route, navOptions = navOptions)
}

fun NavController.navigateToWaitingAcceptPair(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.InvitationGraph.WaitingAcceptPairScreen.route, navOptions = navOptions)
}
fun NavGraphBuilder.invitationGraph(
    navController: NavController
) {
    navigation(
        startDestination = NavigationRoute.InvitationGraph.SendInvitationScreen.route,
        route = NavigationRoute.InvitationGraph.route,
    ) {
        composable(
            route = NavigationRoute.InvitationGraph.SendInvitationScreen.route,
        ) {
            val inviteComponent = componentProvider<InviteComponentProvider>().provideInviteComponent()
            val inviteViewModel = daggerViewModel {
                inviteComponent.getViewModel()
            }
            SendInvitationRoute(inviteViewModel) {
                navController.navigateToWaitingAcceptPair(
                    navOptions = navOptions {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    },
                )
            }
        }
        composable(route = NavigationRoute.InvitationGraph.WaitingAcceptPairScreen.route) {
            val inviteComponent = componentProvider<InviteComponentProvider>().provideInviteComponent()
            val inviteViewModel = daggerViewModel {
                inviteComponent.getViewModel()
            }
            WaitingAcceptPairRoute(
                inviteViewModel,
                onSuccessMatchingPartner = {
                    navController.navigateToHome(
                        navOptions = navOptions {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        },
                    )
                },
                onClickOutButton = {
                    navController.navigateToOnBoarding(
                        navOptions = navOptions {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        },
                    )
                },
            )
        }
    }
}
