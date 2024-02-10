package com.mashup.twotoo.presenter.designsystem.component.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

sealed interface TwoTooToolbarType {

    @Composable
    fun HelpCenterToolbar(
        modifier: Modifier,
        onClickHelpIcon: () -> Unit,
        text: String,
    )

    @Composable
    fun CalendarToolbar(
        modifier: Modifier,
        onClickHelpIcon: () -> Unit,
        onClickCalendarIcon: () -> Unit,
    )

    @Composable
    fun BackToolbar(
        modifier: Modifier,
        onClickBackIcon: () -> Unit,
        onClickActionButton: () -> Unit,
        actionIcons: (@Composable () -> Unit)?,
    )

    @Composable
    fun GardenToolbar(
        modifier: Modifier,
        onClickHelpIcon: () -> Unit,
    )

    @Composable
    fun HistoryToolbar(
        modifier: Modifier,
        onClickBackIcon: () -> Unit,
        onClickActionButton: () -> Unit,
    )

    @Composable
    fun HistoryDetailToolbar(
        modifier: Modifier,
        title: String,
    )

    @Composable
    fun TitleToolbar(
        modifier: Modifier,
    )

    @Composable
    fun MyPageToolbar(
        modifier: Modifier,
        onClickHelpIcon: () -> Unit,
    )

    @Composable
    fun CreateChallengeToolbar(
        modifier: Modifier,
        title: String,
        onClickActionButton: () -> Unit,
        onClickBackIcon: () -> Unit,
    )

    @Composable
    fun DetailImageToolbar(
        modifier: Modifier,
        dropDownModifier: Modifier,
        onClickBackIcon: () -> Unit,
        onClickActionButton: () -> Unit,
        onClickSaveButton: () -> Unit,
        expandedState: Boolean,
        onDismiss: () -> Unit,
    )
}
