package com.mashup.twotoo.presenter.designsystem.component.toolbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainToolbar(
    onClickBackIcon: () -> Unit,
    onClickCalendarIcon: () -> Unit,
    onClickAlarmIcon: () -> Unit,
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = FontBlack,
                    fontFamily = Font.Montserrat,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                )
            }
        },
        navigationIcon = {
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterStart) {
                IconButton(onClick = {
                    onClickBackIcon()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            Row(
                Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { onClickCalendarIcon() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = null,
                    )
                }
                IconButton(onClick = { onClickAlarmIcon() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.alarm_tmp),
                        contentDescription = null,
                    )
                }
            }
        },
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackGroundWhite,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainToolbar() {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = FontBlack,
                    fontFamily = Font.Montserrat,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center,
                )
            }
        },
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackGroundWhite,
            titleContentColor = Purple40,
        ),
    )
}

@Composable
@Preview
fun mainToolbarWithNavAndActionsPreview() {
    MainToolbar({}, {}, {})
}

@Composable
@Preview
fun mainToolbar() {
    MainToolbar()
}
