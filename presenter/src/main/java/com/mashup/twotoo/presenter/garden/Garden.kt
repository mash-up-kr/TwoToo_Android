package com.mashup.twotoo.presenter.garden

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun GardenRoute() {
    GardenScreen()
}

@Composable
fun GardenScreen() {
    Scaffold(
        topBar = {
            TwoTooMainToolbar(text = "우리의 정원", onClickHelpIcon = {})
        },
        containerColor = TwoTooTheme.color.backgroundYellow,
    ) {
        val challenges: List<String> = listOf("하루 30분 운동하기")
        LazyVerticalGrid(modifier = Modifier.padding(paddingValues = it), columns = GridCells.Adaptive(minSize = 157.dp)) {
            items(challenges) { challenge ->
                Text(challenge)
            }
        }
    }
}

@Preview(widthDp = 327, heightDp = 812)
@Composable
private fun PreviewGardenScreen() {
    GardenScreen()
}
