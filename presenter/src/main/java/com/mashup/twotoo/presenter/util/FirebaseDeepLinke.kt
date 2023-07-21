package com.mashup.twotoo.presenter.util

import android.net.Uri
import android.util.Log
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.mashup.twotoo.presenter.constant.TAG

fun createInviteDeepLink(userNo: Int, nickName: String, inviteLink: (Uri?) -> Unit) {
    val link = "$TWOTOO_INVITE_DEEP_LINK?userNo=$userNo&nickname=$nickName"
    val dynamicLink = FirebaseDynamicLinks
        .getInstance()
        .createDynamicLink()
        .setLink(Uri.parse(link)).setDomainUriPrefix(TWOTOO_DOMAIN_URI)
        .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
        .buildShortDynamicLink()

    dynamicLink.addOnSuccessListener {
        Log.d(TAG, "createInviteCode: ${it.shortLink}")
        inviteLink(it.shortLink)
    }
}

const val TWOTOO_INVITE_DEEP_LINK = "https://twotoo.page.link/invite"
const val TWOTOO_DOMAIN_URI = "https://twotoo.page.link"
