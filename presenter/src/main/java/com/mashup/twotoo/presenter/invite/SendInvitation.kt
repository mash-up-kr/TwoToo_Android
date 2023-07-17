package com.mashup.twotoo.presenter.invite

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.util.createInviteDeepLink

@Composable
fun SendInvitationRoute(
    inviteViewModel: InviteViewModel,
    sendInvitationButtonClick: () -> Unit = {}
) {
    SendInvitation(inviteViewModel, sendInvitationButtonClick)
}

@Composable
fun SendInvitation(
    inviteViewModel: InviteViewModel,
    sendInvitationButtonClick: () -> Unit = {}
) {
    val context = LocalContext.current
    Scaffold(
        topBar = { TwoTooMainToolbar() },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .paint(
                    painterResource(id = R.drawable.image_background),
                    contentScale = ContentScale.FillBounds,
                ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = stringResource(id = R.string.invite_title),
                    modifier = Modifier.padding(top = 137.dp, bottom = 24.dp),
                    style = TwoTooTheme.typography.headLineNormal28,
                    color = TwoTooTheme.color.mainBrown,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = stringResource(id = R.string.invite_desc),
                    style = TwoTooTheme.typography.bodyNormal16,
                    color = TwoTooTheme.color.gray600,
                    textAlign = TextAlign.Center,
                )
                TwoTooImageView(
                    modifier = Modifier
                        .padding(top = 47.dp)
                        .size(183.dp, 138.dp),
                    previewPlaceholder = R.drawable.img_send_invitation_flower,
                    model = R.drawable.img_send_invitation_flower,
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.weight(1f))
                TwoTooTextButton(
                    text = stringResource(id = R.string.send_invite),
                ) {
                    // sendInvitationButtonClick()
                    createInviteCode(context, 0, "")
                }
                Spacer(modifier = Modifier.height(55.dp))
            }
        }
    }
}

fun createInviteCode(context: Context, userNo: Int, nickname: String) {
    createInviteDeepLink(userNo, nickname) { uri ->
        uri?.let { link ->
            context.startActivity(shareInviteUrl(link))
        }
    }
}

fun shareInviteUrl(inviteLink: Uri?): Intent? {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, inviteLink.toString())
        type = "text/plain"
    }
    return Intent.createChooser(sendIntent, null)
}

@Preview
@Composable
private fun SendInvitationPreview() {
    // SendInvitation()
}
