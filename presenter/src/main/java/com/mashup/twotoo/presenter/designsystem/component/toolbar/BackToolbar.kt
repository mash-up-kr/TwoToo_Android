package com.mashup.twotoo.presenter.designsystem.component.toolbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackToolbar(
    title: String = "",
    @DrawableRes backIconId: Int = R.drawable.back_arrow,
    onClickBackIcon: () -> Unit,
    actionIconButton: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = FontBlack,
                    fontFamily = Font.SpoqaHanSansNeo,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
        navigationIcon = {
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterStart) {
                IconButton(onClick = {
                    onClickBackIcon()
                }) {
                    Icon(
                        painter = painterResource(id = backIconId),
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            Row(
                Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically
            ) {
                actionIconButton()
            }
        },
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackGroundWhite,
        )
    )
}

@Composable
@Preview
fun BackToolbarWithNavTitleAndActionPreview() {
    val moreIconButton: @Composable () -> Unit =
        {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.more),
                    contentDescription = null
                )
            }
        }
    BackToolbar(title = "title", onClickBackIcon = {}, actionIconButton = moreIconButton)

}

@Composable
@Preview
fun BackToolbarWithNavAndTitlePreview() {
    val moreIconButton: @Composable () -> Unit =
        {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.more),
                    contentDescription = null
                )
            }
        }
    BackToolbar(onClickBackIcon = {}, actionIconButton = moreIconButton)
}

@Composable
@Preview
fun BackToolbar() {
    BackToolbar(onClickBackIcon = {})
}
