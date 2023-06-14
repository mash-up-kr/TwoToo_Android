package com.mashup.twotoo.presenter.createChallenge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.createChallenge.model.FlowerCardUiModel
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.MainPink
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun SelectFlowerLazyColumn() {
    val list = FlowerCardUiModel.getFlowerCardModelToPreview()

    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 8.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(list.size) { idx ->
            FlowerCardItem(
                flowerCardModel = list[idx],
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowerCardItem(
    flowerCardModel: FlowerCardUiModel
) {
    var color by remember { mutableStateOf(Color.White) }

    Card(
        backgroundColor = TwoTooTheme.color.mainWhite,
        shape = TwoTooTheme.shape.small,
        border = BorderStroke(3.dp, color),
        modifier = Modifier
            .padding(horizontal = 13.dp, vertical = 15.dp)
            .size(157.dp),
        onClick = { color = MainPink },
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TwoTooImageView(
                model = R.drawable.example_flower,
                modifier = Modifier
                    .size(55.dp),
                previewPlaceholder = R.drawable.example_flower,
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                text = flowerCardModel.name,
                style = TwoTooTheme.typography.headLineNormal24,
                color = TwoTooTheme.color.mainBrown,
            )
            Text(
                text = flowerCardModel.desc,
                style = TwoTooTheme.typography.bodyNormal14,
                color = TwoTooTheme.color.mainBrown,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFlowerCardItem() {
    val model = FlowerCardUiModel(
        name = "장미",
        desc = "행복한 사랑을 이루고 싶어요",
    )
    FlowerCardItem(model)
}
