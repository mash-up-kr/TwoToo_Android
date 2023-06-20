package com.mashup.twotoo.presenter.designsystem.component.bottomsheet

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.Authenticate
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.SendType
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.SendType.Cheer
import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetType.SendType.Shot
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.util.createImageFile
import kotlinx.coroutines.launch
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoTooBottomSheet(
    type: BottomSheetType,
    button: @Composable (Modifier, BottomSheetData) -> Unit,
    onDismiss: () -> Unit,
    bottomSheetState: SheetState = rememberModalBottomSheetState(),
) {
    TwoTooBottomSheetImpl(
        bottomSheetState = bottomSheetState,
        type = type,
        button = button,
        onDismiss = onDismiss,
    )
}

@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun TwoTooBottomSheetImpl(
    bottomSheetState: SheetState,
    type: BottomSheetType,
    onDismiss: () -> Unit,
    button: @Composable (Modifier, BottomSheetData) -> Unit,
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        "com.mashup.twotoo.provider",
        file,
    )

    val imageCropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            imageUri = result.uriContent
        } else {
            val exception = result.error
            // 후에 토스트 추가
        }
    }

    var setImageDialogVisible by remember { mutableStateOf(false) }

    val takePhotoFromCameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
    ) { success ->
        if (success) {
            val cropOptions = CropImageContractOptions(
                uri,
                CropImageOptions(),
            ).apply {
                setFixAspectRatio(true)
            }
            imageCropLauncher.launch(cropOptions)
            setImageDialogVisible = false
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { success ->
        if (success) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            setImageDialogVisible = true
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val takePhotoFromAlbumLauncher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent(),
    ) { photoUri: Uri? ->
        val cropOptions = CropImageContractOptions(
            photoUri,
            CropImageOptions(),
        ).apply {
            setFixAspectRatio(true)
        }
        imageCropLauncher.launch(cropOptions)
        setImageDialogVisible = false
    }

    Box {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = onDismiss,
            containerColor = Color(0xFFFCF5E6),
        ) {
            when (type) {
                is Authenticate -> {
                    AuthenticateContent(
                        type = type,
                        button = button,
                        imageUri = imageUri,
                        onClickPlusButton = {
                            val permissionCheckResult =
                                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                setImageDialogVisible = true
                            } else {
                                // Request a permission
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                    )
                }
                is SendType -> {
                    SendMsgBottomSheetContent(
                        type = type,
                        button = button,
                    )
                }
            }
        }

        if (type is Authenticate) {
            if (setImageDialogVisible) {
                SetImageOptionDialog(
                    onDismissRequest = { setImageDialogVisible = false },
                    onClickCameraButton = {
                        takePhotoFromCameraLauncher.launch(uri)
                    },
                    onClickAlbumButton = {
                        takePhotoFromAlbumLauncher.launch("image/*")
                    },
                    onClickDismissButton = { setImageDialogVisible = false },
                )
            }
        }
    }
}

@Composable
fun TestButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Text("버튼입니다.")
    }
}

/**
 * 직접 실행하거나, Interactive Mode에서 확인이 가능합니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "인증하기 프리뷰")
@Composable
private fun AuthenticateSheet() {
    TwoTooTheme {
        TwoTooBottomSheet(
            type = Authenticate(),
            button = { _, _ ->
                TestButton(Modifier, {})
            },
            onDismiss = {},
        )
    }
}

/**
 * 직접 실행하거나, Interactive Mode에서 확인이 가능합니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "찌르기 프리뷰")
@Composable
private fun ShotSheet() {
    TwoTooTheme {
        TwoTooBottomSheet(
            type = Shot(),
            button = { _, _ ->
                TestButton(Modifier, {})
            },
            onDismiss = {},
        )
    }
}

/**
 * 직접 실행하거나, Interactive Mode에서 확인이 가능합니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "인증하기")
@Composable
private fun OpenAuthenticate() {
    TwoTooTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            var isBottomSheetVisible by rememberSaveable {
                mutableStateOf(false)
            }
            val bottomSheetState = rememberModalBottomSheetState(true)
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {
                    isBottomSheetVisible = !isBottomSheetVisible
                },
            ) {
                Text("열기 / 닫기")
            }
            if (isBottomSheetVisible) {
                TwoTooBottomSheet(
                    bottomSheetState = bottomSheetState,
                    button = { modifier, bottomSheetData ->
                        TestButton(modifier = Modifier.then(modifier)) {
                            Log.d(TAG, "OpenAuthenticate($bottomSheetData)")
                        }
                    },
                    type = Authenticate(),
                    onDismiss = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                isBottomSheetVisible = !isBottomSheetVisible
                            }
                        }
                    },
                )
            }
        }
    }
}

/**
 * 직접 실행하거나, Interactive Mode에서 확인이 가능합니다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "벌로 콕 찌르기")
@Composable
private fun OpenShot() {
    TwoTooTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            var isBottomSheetVisible by rememberSaveable {
                mutableStateOf(false)
            }
            val bottomSheetState = rememberModalBottomSheetState(true)
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {
                    isBottomSheetVisible = !isBottomSheetVisible
                },
            ) {
                Text("열기 / 닫기")
            }
            if (isBottomSheetVisible) {
                TwoTooBottomSheet(
                    bottomSheetState = bottomSheetState,
                    button = { modifier, bottomSheetData ->
                        /*
                        Custom Button Composable이 들어갑니다.
                         */
                        TestButton(modifier = Modifier.then(modifier)) {
                            Log.d(TAG, "OpenShot($bottomSheetData)")
                        }
                    },
                    type = Shot(),
                    onDismiss = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                isBottomSheetVisible = !isBottomSheetVisible
                            }
                        }
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "오늘의 응원 한마디")
@Composable
private fun OpenCheer() {
    TwoTooTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            var isBottomSheetVisible by rememberSaveable {
                mutableStateOf(false)
            }
            val bottomSheetState = rememberModalBottomSheetState(true)
            val coroutineScope = rememberCoroutineScope()
            Button(
                onClick = {
                    isBottomSheetVisible = !isBottomSheetVisible
                },
            ) {
                Text("열기 / 닫기")
            }
            if (isBottomSheetVisible) {
                TwoTooBottomSheet(
                    bottomSheetState = bottomSheetState,
                    button = { modifier, bottomSheetData ->
                        /*
                        Custom Button Composable이 들어갑니다.
                         */
                        TestButton(modifier = Modifier.then(modifier)) {
                            Log.d(TAG, "OpenShot($bottomSheetData)")
                        }
                    },
                    type = Cheer(),
                    onDismiss = {
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                isBottomSheetVisible = !isBottomSheetVisible
                            }
                        }
                    },
                )
            }
        }
    }
}
