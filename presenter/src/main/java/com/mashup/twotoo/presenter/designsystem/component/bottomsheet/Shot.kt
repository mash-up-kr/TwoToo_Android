package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Divider
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ShotList(
    titleText: String,
    textList: ImmutableList<String>,
    button: @Composable (Modifier) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedValue by remember { mutableStateOf(textList[0]) }
    val textMapList = textList.mapIndexed { index, text ->
        if (index == 0) {
            text to true
        } else {
            text to false
        }
    }.toMutableStateList()

    val isSelectedRow: (String) -> Boolean = { selectedValue == it }
    val onChangeState: (String) -> Unit = { selectedValue = it }

    Column(
        modifier = modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(titleText = titleText)
        Spacer(modifier = Modifier.height(20.5.dp))
        textMapList.forEachIndexed { index, textMap ->
            Divider(modifier = Modifier.fillMaxWidth())
            ShotRow(
                modifier = Modifier.fillMaxWidth().selectable(
                    selected = isSelectedRow(textMap.first),
                    enabled = textMap.second,
                    onClick = {
                        onChangeState(textMap.first)
                    },
                ),
                text = textMap.first,
                selected = isSelectedRow(textMap.first),
                onCheckedChange = onChangeState,
            )
            if (index == textMapList.size - 1) {
                Divider(modifier = Modifier.fillMaxWidth())
            }
        }
        Spacer(modifier = Modifier.height(24.5.dp))
        button(Modifier)
    }
}

@Composable
fun ShotRow(
    text: String,
    selected: Boolean,
    onCheckedChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.selectable(
            selected = selected,
            role = Role.RadioButton,
            onClick = { onCheckedChange(text) },
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
        )
        IconToggleButton(
            checked = selected,
            onCheckedChange = { onCheckedChange(text) },
        ) {
            TwoTooImageView(
                modifier = Modifier.size(22.dp),
                model =
                if (selected) {
                    R.drawable.ic_radio_check
                } else {
                    R.drawable.ic_radio_unchecked
                },
                previewPlaceholder = R.drawable.ic_radio_unchecked,
                failurePlaceHolder = null,
                loadingPlaceHolder = null,
            )
        }
    }
}

@Preview
@Composable
fun PreviewShotList() {
    TwoTooTheme {
        ShotList(
            titleText = stringResource(id = R.string.bottomSheetShot),
            textList = listOf(
                "언제까지 쉬고 있을 거야? 얼른 해야지?",
                "잘하구 있어 좀만 더 화이팅하자!!!",
                "이거 보면 빨리 한다! 실시!",
            ).toImmutableList(),
            button = {},
        )
    }
}
