package com.mashup.twotoo.presenter.createChallenge

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.textfield.TwoTooTextField

@Composable
fun CreateChallengeTwoStep(
    onClickNext: (String) -> Unit
) {
    var challengeInfo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TwoTooTextField(
            modifier = Modifier.fillMaxWidth().padding(top = 46.dp).height(253.dp),
            text = challengeInfo,
            textHint = stringResource(id = R.string.input_challenge_info_placeholder),
            updateText = {
                if (challengeInfo.length <= 100) challengeInfo = it
            },
        )

        Spacer(Modifier.weight(1f))
        TwoTooTextButton(
            text = stringResource(id = R.string.button_next),
            enabled = challengeInfo.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
            onClick = {
                if (challengeInfo.isNotEmpty()) {
                    onClickNext(challengeInfo)
                }
            },
        )
        Spacer(modifier = Modifier.height(55.dp))
    }
}

@Composable
@Preview
private fun PreviewCreateChallengeTwoStep() {
    CreateChallengeTwoStep({})
}
