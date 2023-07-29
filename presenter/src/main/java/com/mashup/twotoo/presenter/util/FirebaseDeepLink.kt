package com.mashup.twotoo.presenter.util

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.firebase.dynamiclinks.ShortDynamicLink
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.iosParameters
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.dynamiclinks.ktx.socialMetaTagParameters
import com.google.firebase.ktx.Firebase
import com.mashup.twotoo.presenter.constant.TAG

fun createInviteDeepLink(userNo: Int, nickName: String, inviteLink: (Uri?) -> Unit) {
    Firebase.dynamicLinks.shortLinkAsync(ShortDynamicLink.Suffix.SHORT) {
        link = Uri.parse("$TWOTOO_INVITE_DEEP_LINK?userNo=$userNo&nickname=$nickName")
        domainUriPrefix = TWOTOO_DOMAIN_URI
        androidParameters(ANDROID_PACKAGE_NAME) {
            minimumVersion = 1
        }

        iosParameters(IOS_BUNDLE_ID) {
            appStoreId = IOS_APPSTORE_ID
        }

        socialMetaTagParameters {
            title = "TwoToo"
            description = "투투에 진입합니다."
            imageUrl = Uri.parse("")
        }
    }.addOnSuccessListener { shortLink ->

        Log.d(TAG, "createInviteCode: ${shortLink.shortLink}")
        inviteLink(shortLink.shortLink)
    }.addOnFailureListener {
    }
}

fun checkInviteLink(isInvite: Boolean = false, intent: Intent, partnerInfo: (String, Int) -> Unit, error: (Boolean?) -> Unit) {
    Firebase.dynamicLinks.getDynamicLink(intent).addOnSuccessListener { linkData ->
        var deepLink: Uri? = null
        linkData?.let { data ->
            deepLink = data.link
        }

        if (deepLink != null) {
            val nickname = deepLink!!.getQueryParameter("nickname") ?: ""
            val partnerNo = deepLink!!.getQueryParameter("userNo") ?: ""
            if (nickname.isNotEmpty() && partnerNo.isNotEmpty()) {
                partnerInfo(nickname, partnerNo.toInt())
                error(false)
            } else {
                error(true)
            }
        } else {
            if (!isInvite) error(true)
        }
    }
}

const val TWOTOO_INVITE_DEEP_LINK = "https://twotoo.mashup.com/invite"
const val TWOTOO_DOMAIN_URI = "https://twotoo.page.link"
const val IOS_BUNDLE_ID = "kr.mash-up.TwoToo"
const val ANDROID_PACKAGE_NAME = "com.mashup.twotoo"
const val IOS_APPSTORE_ID = "6455260918"
