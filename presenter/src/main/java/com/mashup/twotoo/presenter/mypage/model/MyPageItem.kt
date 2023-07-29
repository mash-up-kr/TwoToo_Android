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
}

enum class GuideUrlItem(val url: String) {
    Announcement(url = "https://two2too2.github.io/"),
    UsingGuide(url = "https://two2too2.github.io/"),
    Inquiry(url = "https://forms.gle/S4rB86H8DcwLCPoZA"),
    Makers(url = "https://two2too2.github.io/creater.html"), ;

    companion object {
        fun findUrlBy(route: String): String {
            return GuideUrlItem.values().firstOrNull { it.name == route }?.url ?: Announcement.url
        }
    }
}
