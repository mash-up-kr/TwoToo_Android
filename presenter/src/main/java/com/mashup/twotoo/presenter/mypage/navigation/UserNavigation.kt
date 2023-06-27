package com.mashup.twotoo.presenter.mypage.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.mypage.MyPageRoute
import com.mashup.twotoo.presenter.mypage.di.UserComponentProvider
import javax.inject.Inject

const val UserNavigationRoute = "user_route"

fun NavController.navigateToUser(navOptions: NavOptions? = null) {
    this.navigate(route = UserNavigationRoute, navOptions = navOptions)
}

fun NavGraphBuilder.userGraph() {
    composable(route = UserNavigationRoute) {
        val userComponent = (LocalContext.current.applicationContext as UserComponentProvider).provideUserComponent()
        val userViewModel = daggerViewModel(viewModelStoreOwner = it) {
            userComponent.getViewModel()
        }
        println("userViewModel instance : ${userViewModel.hashCode()}")
        MyPageRoute()
    }
}

class UserViewModel @Inject constructor() : ViewModel()
