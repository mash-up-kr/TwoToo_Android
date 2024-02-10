package com.mashup.twotoo.presenter.designsystem.component.toolbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.R

@Preview
@Composable
private fun HelpCenterToolbar(
    modifier: Modifier = Modifier,
    onClickHelpIcon: () -> Unit = {},
    text: String = "",
) {
    TwoTooToolbar.HelpCenterToolbar(
        modifier = modifier.fillMaxWidth(),
        onClickHelpIcon = onClickHelpIcon,
        text = text,
    )
}

@Preview
@Composable
private fun CalendarToolbar(
    modifier: Modifier = Modifier,
    onClickHelpIcon: () -> Unit = {},
    onClickCalendarIcon: () -> Unit = {},
    text: String = "",
) {
    TwoTooToolbar.CalendarToolbar(
        modifier = modifier.fillMaxWidth(),
        onClickHelpIcon = onClickHelpIcon,
        onClickCalendarIcon = onClickCalendarIcon,
    )
}

@Preview
@Composable
private fun BackToolbar(
    modifier: Modifier = Modifier,
    onClickBackIcon: () -> Unit = {},
    onClickActionButton: () -> Unit = {},
) {
    TwoTooToolbar.BackToolbar(
        modifier = modifier.fillMaxWidth(),
        onClickBackIcon = onClickBackIcon,
        onClickActionButton = onClickActionButton,
        actionIcons = null,
    )
}

@Preview
@Composable
private fun PreviewCreateChallengeToolbar() {
    TwoTooToolbar.CreateChallengeToolbar(
        modifier = Modifier.fillMaxWidth(),
        title = stringResource(id = R.string.challenge_info),
        onClickActionButton = {},
        onClickBackIcon = {},
    )
}

@Preview
@Composable
private fun HistoryToolbar(
    modifier: Modifier = Modifier,
) {
    TwoTooToolbar.HistoryToolbar(
        modifier = modifier.fillMaxWidth(),
        onClickBackIcon = {},
        onClickActionButton = {},
    )
}

@Preview
@Composable
private fun DetailImageToolbar() {
    ConstraintLayout {
        val (topBar, dropDownMenu) = createRefs()
        TwoTooToolbar.DetailImageToolbar(
            modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            dropDownModifier = Modifier
                .wrapContentHeight()
                .constrainAs(dropDownMenu) {
                    top.linkTo(topBar.bottom)
                    end.linkTo(parent.end)
                },
            onClickBackIcon = { },
            onClickActionButton = { },
            onDismiss = { },
            onClickSaveButton = { },
            expandedState = false,
        )
    }
}
