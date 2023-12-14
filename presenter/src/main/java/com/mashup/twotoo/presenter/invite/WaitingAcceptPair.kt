package com.mashup.twotoo.presenter.invite

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.constant.TAG
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooOutlineTextButton
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toast.SnackBarHost
import com.mashup.twotoo.presenter.designsystem.theme.MainBrown
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.util.checkInviteLink
import com.mashup.twotoo.presenter.util.findActivity
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun WaitingAcceptPairRoute(
    inviteViewModel: InviteViewModel,
    onSuccessMatchingPartner: () -> Unit,
    onClickOutButton: () -> Unit
) {
    val state by inviteViewModel.collectAsState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackState = remember { SnackbarHostState() }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
    }

    WaitingAcceptPair(
        snackState = snackState,
        onClickRefreshState = {
            checkInviteLink(
                intent = context.findActivity().intent,
                partnerInfo = { nickname, partnerNo ->
                    Log.d(TAG, "checkInviteLink: $nickname")
                    Log.d(TAG, "checkInviteLink: $partnerNo")
                    inviteViewModel.getUserInfo(true, partnerNo)
                },
                error = { isFail ->
                    Log.d(TAG, "WaitingAcceptPairRoute: $isFail")
                    if (isFail == true) {
                        inviteViewModel.getPartnerInfo()
                    }
                },
            )
        },
        onClickResendInvitation = {
            inviteViewModel.getUserInfo()
        },
        onClickOutButton = onClickOutButton,
    )

    inviteViewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is InviteSideEffect.Toast -> {
                coroutineScope.launch {
                    snackState.showSnackbar(sideEffect.toastMessage)
                }
            }
            is InviteSideEffect.NavigateToWaitingPair -> {}
            is InviteSideEffect.NavigateToHome -> {
                onSuccessMatchingPartner()
            }
            is InviteSideEffect.SendSharedInvitation -> {
                if (state.userNo != 0) {
                    inviteViewModel.storeIsSendInvitation(true)
                    inviteViewModel.createInviteCode(context, state.userNo, state.userNickName) { intent ->
                        launcher.launch(intent)
                    }
                }
            }
            is InviteSideEffect.MatchingPartner -> {
                inviteViewModel.matchingPartner()
            }
        }
    }
}

@Composable
fun WaitingAcceptPair(
    snackState: SnackbarHostState = SnackbarHostState(),
    onClickRefreshState: () -> Unit = {},
    onClickResendInvitation: () -> Unit = {},
    onClickOutButton: () -> Unit = {}
) {
    Box(modifier = Modifier.navigationBarsPadding()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            WaitAcceptToolbar(onClickOutButton = onClickOutButton)
            Text(
                modifier = Modifier.padding(top = 120.dp, bottom = 28.dp),
                text = stringResource(id = R.string.invite_waiting_other),
                textAlign = TextAlign.Center,
                style = TwoTooTheme.typography.headLineNormal28,
                color = TwoTooTheme.color.mainBrown,
            )
            Text(
                text = stringResource(id = R.string.invite_refresh),
                textAlign = TextAlign.Center,
                style = TwoTooTheme.typography.bodyNormal16,
                color = TwoTooTheme.color.gray600,
            )
            TwoTooImageView(
                modifier = Modifier
                    .size(185.dp, 128.dp)
                    .padding(top = 32.dp),
                model = R.drawable.invite_waiting_heart,
                previewPlaceholder = R.drawable.img_waiting_mate_flower,
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.weight(1f))
            WaitingInviteBottom(onClickRefreshState, onClickResendInvitation)
            Spacer(modifier = Modifier.height(54.dp))
        }
        SnackBarHost(
            Modifier.align(Alignment.BottomCenter).padding(bottom = 54.dp),
            snackState,
        )
    }
}

@Composable
fun WaitingInviteBottom(
    onClickRefreshState: () -> Unit,
    onClickResendInvitation: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Text(
            text = stringResource(id = R.string.invite_resend_desc),
            textAlign = TextAlign.Center,
            style = TwoTooTheme.typography.bodyNormal14,
            color = TwoTooTheme.color.gray600,
        )
        TwoTooTextButton(
            text = stringResource(id = R.string.refresh),
            onClick = {
                onClickRefreshState()
            },
        )
        TwoTooOutlineTextButton(
            text = stringResource(id = R.string.resend_invite),
            onClick = {
                onClickResendInvitation()
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaitAcceptToolbar(
    title: String = stringResource(id = R.string.app_name),
    onClickOutButton: () -> Unit,
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = title,
                    color = TwotooPink,
                    style = TwoTooTheme.typography.headLineNormal28,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.offset(x = (-16).dp).padding(horizontal = 24.dp),
                )
                TextButton(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Color.Transparent)
                        .align(Alignment.CenterEnd)
                        .padding(end = 24.dp),
                    onClick = { onClickOutButton() },
                ) {
                    Text(
                        text = stringResource(id = R.string.out_onboarding),
                        color = MainBrown,
                        style = TwoTooTheme.typography.headLineNormal18,
                        textAlign = TextAlign.End,
                    )
                }
            }
        },
        modifier = Modifier.height(56.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
        ),
    )
}

@Preview
@Composable
private fun WaitingAcceptPreview() {
     WaitingAcceptPair(
         snackState = SnackbarHostState(),
         onClickRefreshState = {},
         onClickResendInvitation = {}
     )
}
