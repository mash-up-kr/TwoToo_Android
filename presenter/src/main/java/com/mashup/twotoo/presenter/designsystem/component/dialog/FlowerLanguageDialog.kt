package com.mashup.twotoo.presenter.designsystem.component.dialog

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.FlowerLanguageUiModel
import com.mashup.twotoo.presenter.util.shareImage
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import java.io.ByteArrayOutputStream

fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path =
        MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
    return Uri.parse(path)
}

@Composable
fun FlowerLanguageDialog(
    flowerLanguageUiModel: FlowerLanguageUiModel,
    onClickDismiss: () -> Unit,
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
    ),
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current
    val captureController = rememberCaptureController()

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.get_card_title, flowerLanguageUiModel.getFlowerName(context)),
                style = TwoTooTheme.typography.headLineNormal20,
                color = TwoTooTheme.color.mainWhite,

            )
            Spacer(
                modifier = Modifier.height(16.dp),
            )
            Column(
                modifier = Modifier
                    .background(
                        TwoTooTheme.color.mainWhite,
                        shape = TwoTooTheme.shape.medium,
                    )
                    .width(screenWidth - 102.dp)
                    .padding(11.dp),
            ) {
                Capturable(
                    controller = captureController,
                    onCaptured = { bitmap, error ->
                        context.shareImage(bitmap, error)
                    },
                ) {
                    ConstraintLayout(
                        modifier = Modifier.background(
                            color = TwoTooTheme.color.backgroundYellow,
                            shape = TwoTooTheme.shape.medium,
                        ),
                    ) {
                        val (content, flowerImage, challengeCount, groundImage) = createRefs()

                        FlowerLanguageContent(
                            modifier = Modifier.constrainAs(content) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top, 32.dp)
                            },
                            flower = flowerLanguageUiModel.getFlowerName(context),
                            language = flowerLanguageUiModel.getFlowerLanguage(context),
                        )

                        TwoTooImageView(
                            modifier = Modifier
                                .constrainAs(groundImage) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                }
                                .height(96.dp)
                                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)),
                            model = R.drawable.img_flower_card_bottom,
                            previewPlaceholder = R.drawable.img_flower_card_bottom,
                        )

                        TwoTooImageView(
                            modifier = Modifier
                                .size(99.dp, 164.dp)
                                .constrainAs(flowerImage) {
                                    bottom.linkTo(challengeCount.top, 22.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(content.bottom, 32.dp)
                                },
                            model = flowerLanguageUiModel.getFlowerImage(context),
                            previewPlaceholder = R.drawable.img_home_fourth_stage_fig_partner,
                        )
                        ChallengeCountCard(
                            modifier = Modifier.constrainAs(challengeCount) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom, 30.dp)
                            },
                            flowerLanguageUiModel.challengeNo,
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.height(11.dp),
                )
                TwoTooTextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(49.dp),
                    text = stringResource(id = R.string.share),
                    shape = TwoTooTheme.shape.extraSmall,
                    onClick = {
                        captureController.capture(Bitmap.Config.ARGB_8888)
                    },

                )
            }
            Spacer(
                modifier = Modifier.height(10.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(49.dp)
                    .clickable {
                        onClickDismiss()
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.close),
                    style = TwoTooTheme.typography.headLineNormal18,
                    color = TwoTooTheme.color.mainWhite,
                )
            }
        }
    }
}

@Composable
fun FlowerLanguageContent(modifier: Modifier, flower: String, language: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = flower,
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.headLineNormal24,
            color = TwoTooTheme.color.mainBrown,
        )
        Text(
            text = language,
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.bodyNormal16,
            color = TwoTooTheme.color.mainBrown,
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun ChallengeCountCard(
    modifier: Modifier,
    challengeCount: Int,
) {
    Text(
        modifier = modifier.then(
            Modifier
                .background(
                    color = Color.White.copy(alpha = LocalContentAlpha.current),
                    shape = RoundedCornerShape(4.dp),
                )
                .padding(vertical = 4.dp, horizontal = 10.dp),
        ),
        text = stringResource(id = R.string.challenge_number, challengeCount),
        color = TwoTooTheme.color.twoTooPink,
        style = TwoTooTheme.typography.bodyNormal16,

    )
}

@Preview
@Composable
fun PreviewFlowerDialog() {
    FlowerLanguageDialog(FlowerLanguageUiModel(), {})
}
