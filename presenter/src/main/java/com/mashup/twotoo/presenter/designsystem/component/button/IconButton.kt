package com.mashup.twotoo.presenter.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.SelectedIconColor
import com.mashup.twotoo.presenter.designsystem.theme.Yello

@Composable
fun IconButton(
    text: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = 30.dp),
    @DrawableRes iconId: Int,
    color: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = SelectedIconColor
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                style = TextStyle(color = SelectedIconColor, fontWeight = FontWeight.Bold)
            )
        }
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun IconButtonPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(1.0f))
        IconButton(
            "카카오톡으로 로그인",
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 10.dp),
            R.drawable.kakaotalk,
            Yello
        ) {}
        Spacer(modifier = Modifier.height(10.dp))
    }
}
