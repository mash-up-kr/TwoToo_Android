package com.mashup.twotoo.presenter.designsystem.component.toolbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

object TwoTooToolbar : TwoTooToolbarType {
    @Composable
    override fun HelpCenterToolbar(
        modifier: Modifier,
        onClickHelpIcon: () -> Unit,
        text: String,
    ) {
        TwoTooTopAppBar(
            modifier = modifier,
            titleEnabled = true,
            actionIcons = {
                Icon(
                    modifier = Modifier.clip(shape = CircleShape).clickable {
                        onClickHelpIcon()
                    },
                    painter = painterResource(id = R.drawable.help),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            },
        )
    }

    @Composable
    override fun CalendarToolbar(
        modifier: Modifier,
        onClickHelpIcon: () -> Unit,
        onClickCalendarIcon: () -> Unit,
    ) {
        TwoTooTopAppBar(
            modifier = modifier,
            titleEnabled = true,
            title = stringResource(id = R.string.app_name),
            titleAlignment = Arrangement.Start,
            actionIcons = {
                Icon(
                    modifier = Modifier.clickable {
                        onClickCalendarIcon()
                    },
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
                Spacer(
                    modifier = Modifier.width(20.dp),
                )
                Icon(
                    modifier = Modifier.clip(shape = CircleShape).clickable {
                        onClickHelpIcon()
                    },
                    painter = painterResource(id = R.drawable.help),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            },
        )
    }

    @Composable
    override fun BackToolbar(
        modifier: Modifier,
        onClickBackIcon: () -> Unit,
        onClickActionButton: () -> Unit,
        actionIcons: (@Composable () -> Unit)?,
    ) {
        TwoTooTopAppBar(
            modifier = modifier,
            actionIcons = {
                if (actionIcons != null) {
                    actionIcons()
                }
            },
            navigationIcon = {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterStart) {
                    IconButton(onClick = {
                        onClickBackIcon()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = TwoTooTheme.color.mainBrown,
                        )
                    }
                }
            },
        )
    }

    @Composable
    override fun GardenToolbar(
        modifier: Modifier,
        onClickHelpIcon: () -> Unit,
    ) {
        TwoTooTopAppBar(
            modifier = modifier,
            titleEnabled = true,
            title = stringResource(id = R.string.garden_title),
            titleAlignment = Arrangement.Start,
            actionIcons = {
                Icon(
                    modifier = Modifier.clip(shape = CircleShape).clickable {
                        onClickHelpIcon()
                    },
                    painter = painterResource(id = R.drawable.help),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            },
        )
    }

    @Composable
    override fun HistoryToolbar(
        modifier: Modifier,
        onClickBackIcon: () -> Unit,
        onClickActionButton: () -> Unit,
    ) {
        TwoTooTopAppBar(
            modifier = modifier,
            titleEnabled = false,
            navigationIcon = {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterStart) {
                    IconButton(onClick = {
                        onClickBackIcon()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = TwoTooTheme.color.mainBrown,
                        )
                    }
                }
            },
            actionIcons = {
                Icon(
                    modifier = Modifier.clickable {
                        onClickActionButton()
                    },
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    tint = TwoTooTheme.color.mainBrown,
                )
            },
        )
    }

    @Composable
    override fun HistoryDetailToolbar(
        modifier: Modifier,
        title: String,
    ) {
        TwoTooTopAppBar(
            modifier = modifier,
            titleEnabled = true,
            title = title,
            titleAlignment = Arrangement.Start,
        )
    }

    @Composable
    override fun TitleToolbar(modifier: Modifier) {
        TwoTooTopAppBar(
            modifier = modifier,
            titleEnabled = true,
            title = stringResource(id = R.string.app_name),
            titleAlignment = Arrangement.Start,
        )
    }

    @Composable
    override fun MyPageToolbar(modifier: Modifier, onClickHelpIcon: () -> Unit) {
        TwoTooTopAppBar(
            modifier = modifier,
            titleEnabled = true,
            title = stringResource(id = R.string.mypage),
            titleAlignment = Arrangement.Start,
            actionIcons = {
                Icon(
                    modifier = Modifier.clip(shape = CircleShape).clickable {
                        onClickHelpIcon()
                    },
                    painter = painterResource(id = R.drawable.help),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            },
        )
    }

    @Composable
    override fun CreateChallengeToolbar(
        modifier: Modifier,
        title: String,
        onClickActionButton: () -> Unit,
        onClickBackIcon: () -> Unit,
    ) {
        TwoTooTopAppBar(
            modifier = modifier,
            title = title,
            titleEnabled = true,
            titleAlignment = Arrangement.Center,
            titleTextColor = TwoTooTheme.color.mainBrown,
            navigationIcon = {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterStart) {
                    IconButton(onClick = {
                        onClickBackIcon()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = TwoTooTheme.color.mainBrown,
                        )
                    }
                }
            },
            actionIcons = {
                Icon(
                    modifier = Modifier.clickable { onClickActionButton() },
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    tint = TwoTooTheme.color.mainBrown,
                )
            },
        )
    }

    @Composable
    override fun DetailImageToolbar(
        modifier: Modifier,
        dropDownModifier: Modifier,
        onClickBackIcon: () -> Unit,
        onClickActionButton: () -> Unit,
        onClickSaveButton: () -> Unit,
        expandedState: Boolean,
        onDismiss: () -> Unit,
    ) {
        TwoTooTopAppBar(
            modifier = modifier,
            navigationIcon = {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterStart) {
                    IconButton(onClick = {
                        onClickBackIcon()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                }
            },
            actionIcons = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    tint = Color.White,
                )

                DropdownMenu(
                    modifier = dropDownModifier,
                    expanded = expandedState,
                    onDismissRequest = onDismiss,
                ) {
                    DropdownMenuItem(text = {
                        Text(text = stringResource(id = R.string.saveImage))
                    }, onClick = onClickSaveButton)
                }
            },
        )
    }
}
