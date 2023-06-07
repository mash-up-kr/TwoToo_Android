package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.home.UserType.ME
import com.mashup.twotoo.presenter.home.UserType.PARTNER

@Composable
fun HomeFlowerOwnerText(
    name: String,
    userType: UserType,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.background(
            color = TwoTooTheme.color.backgroundYellow,
            shape = TwoTooTheme.shape.small,
        ),
        color = when (userType) {
            PARTNER -> {
                TwoTooTheme.color.mainBrown
            }
            ME -> {
                TwotooPink
            }
        },
        text = name,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewFlowerOwerPartnerText() {
    TwoTooTheme {
        HomeFlowerOwnerText(name = "공주", userType = PARTNER)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFlowerOwnerText() {
    TwoTooTheme {
        HomeFlowerOwnerText(
            name = "공주",
            userType = ME,
        )
    }
}
