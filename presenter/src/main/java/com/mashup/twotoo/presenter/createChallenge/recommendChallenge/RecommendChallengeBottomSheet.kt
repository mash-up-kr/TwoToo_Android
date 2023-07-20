package com.mashup.twotoo.presenter.createChallenge.recommendChallenge

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendChallengeBottomSheet(
    onClickItemName: (Int) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        containerColor = TwoTooTheme.color.backgroundYellow,
    ) {
        RecommendChallengeContent(onClickItemName)
    }
}
