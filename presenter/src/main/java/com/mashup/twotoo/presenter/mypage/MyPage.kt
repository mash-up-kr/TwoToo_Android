package com.mashup.twotoo.presenter.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.dialog.DialogContent
import com.mashup.twotoo.presenter.designsystem.component.dialog.TwoTooDialog
import com.mashup.twotoo.presenter.designsystem.component.toast.SnackBarHost
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.model.HomeGoalCountUiModel
import com.mashup.twotoo.presenter.home.ongoing.components.HomeGoalCount
import com.mashup.twotoo.presenter.mypage.components.MyPageItemList
import com.mashup.twotoo.presenter.mypage.model.GuideUrlItem
import com.mashup.twotoo.presenter.mypage.model.MyPageItem
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun MyPageRoute(
    isChangeNicknameSuccess: Boolean = false,
    userViewModel: UserViewModel,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            userViewModel.getUserInfo()
        }
    }
    var isChangeSuccess by remember { mutableStateOf(isChangeNicknameSuccess) }
    if(isChangeSuccess) {
        userViewModel.isSuccessChangeNickName(isChangeNicknameSuccess)
        isChangeSuccess = false
    }

    val coroutineScope = rememberCoroutineScope()
    val snackState = remember { SnackbarHostState() }

    var isMyPageDialogVisible by remember { mutableStateOf(false) }
    var myPageDialogContent by remember { mutableStateOf(DialogContent.default) }

    val state by userViewModel.collectAsState()
    userViewModel.collectSideEffect { sideEffect ->
        handleSideEffect(
            sideEffect,
            navigate = navigateToRoute,
            toast = {
                coroutineScope.launch {
                    snackState.showSnackbar("닉네임이 변경되었습니다.")
                }
            },
            action = { type ->
                when (type) {
                    MyPageDialogType.SignOutConfirm -> {
                        myPageDialogContent = DialogContent.createSignOutConfirmDialogContent(
                            negativeAction = { isMyPageDialogVisible = false },
                            positiveAction = {
                                isMyPageDialogVisible = false
                                userViewModel.signOut()
                            },
                        )
                        isMyPageDialogVisible = true
                    }
                    MyPageDialogType.SignOutSuccess -> {
                        myPageDialogContent = DialogContent.createSignOutSuccessDialogContent(
                            positiveAction = {
                                userViewModel.navigateToRoute(MyPageItem.SignOut.route)
                            },
                        )
                        isMyPageDialogVisible = true
                    }
                    MyPageDialogType.DeletePartnerConfirm -> {
                        myPageDialogContent = DialogContent.createDeletePartnerConfirmDialogContent(
                            negativeAction = {
                                isMyPageDialogVisible = false
                            },
                            positiveAction = {
                                isMyPageDialogVisible = false
                                userViewModel.deletePartner()
                            },
                        )
                        isMyPageDialogVisible = true
                    }
                }
            },
        )
    }

    MyPageScreen(
        modifier = modifier.testTag(
            stringResource(id = R.string.mypage),
        ),
        state = state,
        snackState = snackState,
        isMyPageDialogVisible = isMyPageDialogVisible,
        myPageDialogContent = myPageDialogContent,
        navigateToChangeNickName = { route -> userViewModel.navigateToRoute(route) },
        navigateToGuide = { route ->
            when (route) {
                MyPageItem.DeletePartner.route -> { userViewModel.openDeletePartnerConfirmDialog() }
                MyPageItem.SignOut.route -> { userViewModel.openSignOutConfirmDialog() }
                else -> { userViewModel.navigateToRoute(route) }
            }
        },
    )
}

private fun handleSideEffect(
    sideEffect: UserSideEffect,
    toast: () -> Unit,
    navigate: (String) -> Unit,
    action: (MyPageDialogType) -> Unit
) {
    when (sideEffect) {
        is UserSideEffect.NavigateToRoute -> {
            navigate(sideEffect.route)
        }
        UserSideEffect.OpenSignOutConfirmDialog -> {
            action(MyPageDialogType.SignOutConfirm)
        }
        UserSideEffect.OpenSignOutSuccessDialog -> {
            action(MyPageDialogType.SignOutSuccess)
        }
        UserSideEffect.OpenDeletePartnerConfirmDialog -> {
            action(MyPageDialogType.DeletePartnerConfirm)
        }
        UserSideEffect.SuccessNickNameChange -> {
            toast()
        }
    }
}

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    state: UserState,
    snackState: SnackbarHostState,
    isMyPageDialogVisible: Boolean = false,
    myPageDialogContent: DialogContent = DialogContent.default,
    navigateToGuide: (String) -> Unit,
    navigateToChangeNickName: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        TwoTooMainToolbar(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.mypage),
            onClickHelpIcon = { navigateToGuide(GuideUrlItem.UsingGuide.name) },
        )
        TwoTooImageView(
            modifier = Modifier.size(width = 149.dp, height = 129.dp),
            model = R.drawable.img_nicknam_my,
            previewPlaceholder = R.drawable.img_nicknam_my,
        )
        Spacer(modifier = Modifier.height(29.dp))
        HomeGoalCount(
            homeGoalCountUiModel = state.homeGoalCountUiModel,
            horizontalAlignment = Alignment.CenterHorizontally,
            textLineSpacerHeight = 10.dp,
        )
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier
                .clickable { navigateToChangeNickName(NavigationRoute.NickNameSettingGraph.route) }
                .height(34.dp)
                .background(
                    color = Color.White,
                    shape = TwoTooTheme.shape.small,
                )
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = state.homeGoalCountUiModel.myName ?: "",
                style = TwoTooTheme.typography.headLineNormal18,
                color = TwoTooTheme.color.mainBrown,
            )
            Spacer(modifier = modifier.width(10.dp))
            TwoTooImageView(
                modifier = Modifier.size(16.dp),
                model = R.drawable.ic_edit,
                previewPlaceholder = R.drawable.ic_edit,
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp),
            color = TwoTooTheme.color.mainYellow,
        )
        Spacer(modifier = Modifier.height(11.dp))
        MyPageItemList(
            modifier = Modifier.fillMaxWidth(),
            navigateToGuide = navigateToGuide,
        )
        Spacer(modifier = Modifier.weight(1f))
        SnackBarHost(
            Modifier.padding(bottom = 54.dp),
            snackState,
        )
    }

    if (isMyPageDialogVisible) {
        TwoTooDialog(content = myPageDialogContent)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    TwoTooTheme {
        MyPageScreen(
            state = UserState(HomeGoalCountUiModel.default),
            snackState = SnackbarHostState(),
            navigateToChangeNickName = {},
            navigateToGuide = {},
        )
    }
}
