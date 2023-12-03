package com.mashup.twotoo.presenter.invite

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.util.checkInviteLink
import com.mashup.twotoo.presenter.util.findActivity
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun WaitingAcceptPairRoute(
    inviteViewModel: InviteViewModel,
    onSuccessMatchingPartner: () -> Unit
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
    snackState: SnackbarHostState,
    onClickRefreshState: () -> Unit,
    onClickResendInvitation: () -> Unit,
) {
    Box(modifier = Modifier.navigationBarsPadding()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            TwoTooMainToolbar()
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
    onClickResendInvitation: () -> Unit,
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

@Preview
@Composable
private fun WaitingAcceptPreview() {
     WaitingAcceptPair(
         snackState = SnackbarHostState(),
         onClickRefreshState = {},
         onClickResendInvitation = {}
     )
}
