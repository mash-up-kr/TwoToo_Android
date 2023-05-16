package com.mashup.twotoo.presenter.twotoo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mashup.twotoo.presenter.designsystem.component.TwoTooNavigationBar
import com.mashup.twotoo.presenter.designsystem.component.TwoTooNavigationBarItem
import com.mashup.twotoo.presenter.designsystem.icon.Icon
import com.mashup.twotoo.presenter.designsystem.icon.Icon.ImageVectorIcon
import com.mashup.twotoo.presenter.navigation.TopLevelDestination
import com.mashup.twotoo.presenter.navigation.TwoTooNavHost
import com.mashup.twotoo.presenter.ui.TwoTooAppState
import com.mashup.twotoo.presenter.ui.rememberTwoTooAppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoTooApp(
    appState: TwoTooAppState = rememberTwoTooAppState(),
) {
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            TwoTooBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigationToTopLevelDestination,
                currentDestination = appState.currentDestination,
                containerColor = Color.White,
            )
        },
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(paddingValues),
        ) {
            val destination = appState.currentTopLevelDestination
            if (destination != null) {
                // topbar 공통된 tapbar 있는거 같아서 넣으면 좋을 듯
            }
            TwoTooNavHost(appState = appState)
        }
    }
}

@Composable
fun TwoTooBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    containerColor: Color,
    modifier: Modifier = Modifier,
) {
    TwoTooNavigationBar(
        modifier = modifier,
        containerColor = containerColor,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination = destination)
            TwoTooNavigationBarItem(
                selected,
                onClick = {
                    onNavigateToDestination(destination)
                },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )
                        is Icon.DrawableResourceIcon -> Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )
                    }
                },
                label = null,
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
