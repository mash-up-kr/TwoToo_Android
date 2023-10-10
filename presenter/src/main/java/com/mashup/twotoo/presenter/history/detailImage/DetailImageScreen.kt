package com.mashup.twotoo.presenter.history.detailImage

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.toast.SnackBarHost
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooBackToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.util.saveBitmapToStorage
import dev.shreyaspatil.capturable.Capturable
import dev.shreyaspatil.capturable.controller.rememberCaptureController
import kotlinx.coroutines.launch

@Composable
fun DetailImageRoute(
    url: String,
    onClickBackButton: () -> Unit = {},
) {
    DetailImageScreen(
        modifier = Modifier.fillMaxSize(),
        url = url,
        onClickBackButton = onClickBackButton,
    )
}

@Composable
fun DetailImageScreen(
    url: String,
    modifier: Modifier = Modifier,
    onClickBackButton: () -> Unit = {},
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    var settingMenuVisibility by remember { mutableStateOf(false) }
    val captureController = rememberCaptureController()
    val context = LocalContext.current

    val screenWidth = LocalConfiguration.current.screenWidthDp.toFloat()
    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val successToastText = stringResource(id = R.string.saveImageSuccessToastText)
    val errorToastText = stringResource(id = R.string.saveImageErrorToastText)
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = modifier
            .background(color = Color.Black)
            .pointerInput(Unit) {
                detectTransformGestures(
                    onGesture = { _, pan, gestureZoom, _ ->
                        scale = (scale * gestureZoom).coerceIn(1f, 5f)
                        if (scale > 1) {
                            offsetX += pan.x * scale
                            offsetY += pan.y * scale
                            offsetX = offsetX.coerceIn(-screenWidth, screenWidth)
                            offsetY = offsetY.coerceIn(-screenWidth, screenWidth)
                        } else {
                            offsetX = 0f
                            offsetY = 0f
                        }
                    },
                )
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = 1f
                        offsetX = 0f
                        offsetY = 0f
                    },
                )
            },
    ) {
        val (topBar, image, captureWrapper, dropDownMenu, snackBar) = createRefs()
        TwoTooBackToolbar(
            modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onClickBackIcon = {
                onClickBackButton()
            },
            contentColor = Color.White,
        ) {
            IconButton(
                onClick = {
                    settingMenuVisibility = true
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(dropDownMenu) {
                        top.linkTo(topBar.bottom)
                        end.linkTo(parent.end)
                    },
                expanded = settingMenuVisibility,
                onDismissRequest = { settingMenuVisibility = false },
            ) {
                DropdownMenuItem(text = {
                    Text(text = stringResource(id = R.string.saveImage))
                }, onClick = {
                    captureController.capture(Bitmap.Config.ARGB_8888)
                })
            }
        }

        Capturable(
            modifier = Modifier.constrainAs(captureWrapper) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
                height = Dimension.ratio("1:1")
            },
            controller = captureController,
            onCaptured = { bitmap, error ->
                if (bitmap != null) {
                    saveBitmapToStorage(
                        context = context,
                        bitmap = bitmap.asAndroidBitmap(),
                        title = url,
                        onSuccessToSave = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(successToastText)
                            }
                            settingMenuVisibility = false
                        },
                        onFailToSave = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(errorToastText)
                            }
                            settingMenuVisibility = false
                        },
                    )
                }
                if (error != null) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(errorToastText)
                    }
                    settingMenuVisibility = false
                }
            },
        ) {
            TwoTooImageView(
                modifier = Modifier
                    .constrainAs(image) {
                        linkTo(
                            start = parent.start,
                            end = parent.end,
                            top = parent.top,
                            bottom = parent.bottom,
                        )
                    }
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offsetX
                        translationY = offsetY
                    },
                model = url,
                contentScale = ContentScale.Fit,
                previewPlaceholder = R.drawable.empty_image_color_placeholder,
            )
        }
        SnackBarHost(
            modifier = Modifier.constrainAs(snackBar) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 30.dp)
            },
            snackState = snackbarHostState,
        )
    }
}

@Preview
@Composable
private fun PreviewDetailImageScreen() {
    TwoTooTheme {
        DetailImageRoute(url = "")
    }
}
