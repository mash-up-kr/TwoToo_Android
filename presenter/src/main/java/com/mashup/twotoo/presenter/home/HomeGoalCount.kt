package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

data class HomeGoalCountData(
    val partnerName: String = "공주",
    val myName: String = "나",
    val count: Int = 1,
)

@Composable
fun HomeGoalCount(
    homeGoalCountData: HomeGoalCountData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides TextStyle(
                color = Color(0xFFF07C4B),
                fontSize = 14.sp,
            ),
        ) {
            Row {
                Text(homeGoalCountData.myName)
                Text("♥")
                Text(homeGoalCountData.partnerName)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = "${homeGoalCountData.count}번째 꽃 피우는 중")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGoalCount() {
    TwoTooTheme {
        HomeGoalCount(
            homeGoalCountData = HomeGoalCountData(
                partnerName = "공주",
                myName = "나",
                count = 1,
            ),
        )
    }
}
