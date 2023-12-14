package com.mashup.twotoo.presenter.mypage.model

import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

/**
 * @Created by 김현국 2023/06/19
 */
enum class MyPageItem(@StringRes val value: Int, val route: String) {
    Announcement(value = R.string.announcement, route = GuideUrlItem.Announcement.name),
    UsingGuide(value = R.string.using_guide, route = GuideUrlItem.UsingGuide.name),
    Inquiry(value = R.string.inquiry, route = GuideUrlItem.Inquiry.name),
    Makers(value = R.string.makers, route = GuideUrlItem.Makers.name),
    DeletePartner(value = R.string.delete_matching, route = "deletePartner"),
    SignOut(value = R.string.sign_out, route = "signOut")
}

enum class GuideUrlItem(val url: String, val title: String) {
    Announcement(url = "https://two2too2.github.io/", title = "공지사항"),
    UsingGuide(url = "https://two2too2.github.io/", title = "투투 설명서"),
    Inquiry(
        url = "https://docs.google.com/forms/d/e/1FAIpQLSeUGNUGzl3MnGUAIR-rtfgYYrDYRIoKh_Ozpd4prqA1qIBKRw/viewform?usp=sf_link",
        title = "투투에 문의하기",
    ),
    Makers(
        url = "https://twotoo-landing.vercel.app/makers",
        title = "만든이들",
    ), ;

    companion object {
        fun findUrlBy(route: String): String {
            return GuideUrlItem.values().firstOrNull { it.name == route }?.url ?: Announcement.url
        }

        fun findTitleBy(route: String): String {
            return GuideUrlItem.values().firstOrNull { it.name == route }?.title
                ?: Announcement.title
        }
    }
}
