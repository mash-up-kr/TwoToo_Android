package com.mashup.twotoo.presenter.createChallenge.selectflower

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.createChallenge.model.FlowerCardUiModel
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.MainPink
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun SelectFlowerLazyColumn(
    onClickOneItem: () -> Unit
) {
    val list = FlowerCardUiModel.getFlowerCardModel()
    var selectedItemIndex by remember { mutableIntStateOf(-1) }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(list.size) { idx ->
            FlowerCardItem(
                flowerCardModel = list[idx],
                selectedItemIndex,
                onClickItem = { index ->
                    selectedItemIndex = index
                    onClickOneItem()
                },
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowerCardItem(
    flowerCardModel: FlowerCardUiModel,
    selectedItem: Int,
    onClickItem: (Int) -> Unit,
) {
    Card(
        backgroundColor = TwoTooTheme.color.mainWhite,
        shape = TwoTooTheme.shape.small,
        border = BorderStroke(
            3.dp,
            if (selectedItem == flowerCardModel.index) {
                MainPink
            } else {
                Color.Transparent
            },
        ),
        modifier = Modifier
            .padding(horizontal = 13.dp, vertical = 15.dp)
            .size(157.dp),
        onClick = {
            onClickItem(flowerCardModel.index)
        },
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TwoTooImageView(
                model = flowerCardModel.selectFlowerImage,
                modifier = Modifier
                    .size(55.dp),
                previewPlaceholder = flowerCardModel.selectFlowerImage,
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                text = stringResource(id = flowerCardModel.name),
                style = TwoTooTheme.typography.headLineNormal24,
                color = TwoTooTheme.color.mainBrown,
            )
            Text(
                text = stringResource(flowerCardModel.desc),
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
        name = R.string.rose,
        desc = R.string.rose_language,
        selectFlowerImage = R.drawable.img_challenge_select_rose,
        0,
    )
    FlowerCardItem(model, 0, {})
}
