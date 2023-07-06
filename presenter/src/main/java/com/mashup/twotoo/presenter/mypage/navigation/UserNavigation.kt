package com.mashup.twotoo.presenter.mypage.navigation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.mypage.MyPageRoute
import com.mashup.twotoo.presenter.mypage.di.UserComponentProvider
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.componentProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

fun NavController.navigateToUser(navOptions: NavOptions? = null) {
    this.navigate(route = NavigationRoute.UserGraph.route, navOptions = navOptions)
}

fun NavGraphBuilder.userGraph(navController: NavController) {
    navigation(startDestination = NavigationRoute.UserGraph.UserScreen.route, route = NavigationRoute.UserGraph.route) {
        composable(route = NavigationRoute.UserGraph.UserScreen.route) {
            val userComponent = componentProvider<UserComponentProvider>().provideUserComponent()
            val userViewModel = daggerViewModel {
                userComponent.getViewModel()
            }
            println("userViewModel instance : ${userViewModel.hashCode()}")
            val state = userViewModel.count.collectAsState()
            MyPageRoute(
                state = state.value,
                onClickMyPageItem = {
                    userViewModel.updateCount()
                },
            )
        }
    }
}

class UserViewModel @Inject constructor() : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()
    fun printHashCode() {
        println("로그 Two Too : ${this.hashCode()}")
    }

    fun updateCount() {
        _count.value++
    }
}
