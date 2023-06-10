package com.mashup.twotoo.presenter.history.datail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun HistoryDetailScreen() {
    Scaffold(
        topBar = { TwoTooMainToolbar(title = "왕자의 기록") },
        containerColor = TwoTooTheme.color.backgroundYellow,
    ) {
        Column(modifier = Modifier.padding(paddingValues = it).padding(horizontal = 24.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "2023년 4월 20일")
                Icon(painter = painterResource(id = R.drawable.ic_cancel), contentDescription = null)
            }
            TwoTooImageView(model = "", modifier = Modifier.fillMaxWidth().height(327.dp))
            Text(text = "30분이상 운동하기")
            Text(text = "오늘 따라 너무 핀곤해서 힘들었다!!! 으악 ! \n 그래도 귀여운 꽃을 키우기 위해 열심히 해야지 ㅎㅎ")
            Text(text = "입력 시간: 20:35")
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryDetailScreen() {
    HistoryDetailScreen()
}
