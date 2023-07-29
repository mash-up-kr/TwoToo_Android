package com.mashup.twotoo.presenter.mypage.model

import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

/**
 * @Created by 김현국 2023/06/19
 */
enum class MyPageItem(@StringRes val value: Int, val route: String) {
    Announcement(value = R.string.announcement, route = GuideUrlItem.Announcement.route),
    UsingGuide(value = R.string.using_guide, route = GuideUrlItem.UsingGuide.route),
    Inquiry(value = R.string.inquiry, route = GuideUrlItem.Inquiry.route),
    Makers(value = R.string.makers, route = GuideUrlItem.Makers.route),
}

enum class GuideUrlItem(val url: String, val route: String) {
    Announcement(url = "https://two2too2.github.io/", route = "announcement"),
    UsingGuide(url = "https://two2too2.github.io/", route = "usingGuide"),
    Inquiry(url = "https://forms.gle/S4rB86H8DcwLCPoZA", route = "inquiry"),
    Makers(url = "https://two2too2.github.io/creater.html", route = "makers"), ;

    companion object {
        fun findUrlBy(route: String): String {
            return GuideUrlItem.values().firstOrNull { it.route == route }?.url ?: Announcement.url
        }
    }
}
