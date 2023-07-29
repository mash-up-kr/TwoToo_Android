package com.mashup.twotoo.presenter.garden

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun GardenRoute(
    gardenViewModel: GardenViewModel,
    modifier: Modifier = Modifier,
    navigateToGarden: (Int) -> Unit = {},
    navigateToGuide: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.RESUMED) {
            gardenViewModel.getAllChallenge()
        }
    }
    val state by gardenViewModel.collectAsState()

    GardenScreen(
        modifier = modifier.fillMaxSize().testTag(stringResource(id = R.string.garden)),
        navigateToGarden = navigateToGarden,
        navigateToGuide = navigateToGuide,
        state = state,
        stopAnimation = gardenViewModel::stopAnimation,
    )
}

@Composable
fun GardenScreen(
    state: GardenState,
    modifier: Modifier = Modifier,
    navigateToGarden: (Int) -> Unit,
    stopAnimation: () -> Unit,
    navigateToGuide: () -> Unit,
) {
    // 애니메이션 상태 변수 (3초 후에 애니메이션 종료)

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(5.dp))
        TwoTooMainToolbar(text = stringResource(id = R.string.garden_title), onClickHelpIcon = { navigateToGuide() })
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 24.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            verticalArrangement = Arrangement.spacedBy(13.dp),
        ) {
            items(items = state.challengeCardInfos, key = { it.challengeNo }) { challengeInfo ->
                val isStartAnimation = state.startAnimation.first && state.startAnimation.second == challengeInfo.challengeNo
                LaunchedEffect(true) {
                    delay(2000)
                    stopAnimation()
                }
                ChallengeCard(
                    isStartAnimation = isStartAnimation,
                    challengeCardInfoUiModel = challengeInfo,
                    navigateToGarden = navigateToGarden,

                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewGardenScreen() {
    TwoTooTheme {
        GardenScreen(
            GardenState.default,
            navigateToGarden = {},
            stopAnimation = {},
            navigateToGuide = {},
        )
    }
}
