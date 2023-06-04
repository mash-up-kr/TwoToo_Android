package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun HomeFlowerOwnerText(
    name: String,
    fontColor: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.background(
            color = Color(0xFFFCF5E6),
            shape = RoundedCornerShape(35.dp),
        ),
        color = fontColor,
        fontSize = 14.sp,
        text = name,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewFlowerOwerColoredText() {
    TwoTooTheme {
        HomeFlowerOwnerText(name = "공주", Color(0xFFF07C4B))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFlowerOwerText() {
    TwoTooTheme {
        HomeFlowerOwnerText(
            name = "공주",
            Color(
                0xFF443018,
            ),
        )
    }
}
