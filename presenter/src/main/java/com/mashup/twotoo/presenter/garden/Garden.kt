package com.mashup.twotoo.presenter.garden

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.loading.FlowerLoadingIndicator
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun GardenRoute(
    isComplete: Boolean = false,
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
        isComplete = isComplete,
        modifier = modifier.fillMaxSize().testTag(stringResource(id = R.string.garden)),
        navigateToGarden = navigateToGarden,
        navigateToGuide = navigateToGuide,
        state = state,
    )
}

@Composable
fun GardenScreen(
    isComplete: Boolean = false,
    state: GardenState,
    modifier: Modifier = Modifier,
    navigateToGarden: (Int) -> Unit,
    navigateToGuide: () -> Unit,
) {
    // 애니메이션 상태 변수 (3초 후에 애니메이션 종료)

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(5.dp))
        TwoTooMainToolbar(
            text = stringResource(id = R.string.garden_title),
            onClickHelpIcon = { navigateToGuide() },
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (state.hasNotRealChallenge) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.challengeEmptyDescription),
                        style = TwoTooTheme.typography.bodyNormal16,
                        color = TwoTooTheme.color.gray500,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp,
                    )
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize()
                        .padding(horizontal = 24.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    verticalArrangement = Arrangement.spacedBy(13.dp),
                ) {
                    items(items = state.challengeCardInfos, key = { it.challengeNo }) { challengeInfo ->
                        var isStartAnimation by remember { mutableStateOf(isComplete && state.challengeCardInfos.first() == challengeInfo) }
                        LaunchedEffect(isStartAnimation) {
                            delay(2000)
                            isStartAnimation = false
                        }
                        ChallengeCard(
                            isStartAnimation = isStartAnimation,
                            challengeCardInfoUiModel = challengeInfo,
                            navigateToGarden = navigateToGarden,

                        )
                    }
                }
            }
            if (state.loadingIndicatorState) {
                FlowerLoadingIndicator(
                    modifier = Modifier.width(128.dp).height(144.dp).align(Alignment.Center),
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
            isComplete = false,
            GardenState.default,
            navigateToGarden = {},
            navigateToGuide = {},
        )
    }
}
