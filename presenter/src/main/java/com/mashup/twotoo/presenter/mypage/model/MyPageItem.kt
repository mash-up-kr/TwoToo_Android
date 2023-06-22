package com.mashup.twotoo.presenter.mypage.model

import androidx.annotation.StringRes
import com.mashup.twotoo.presenter.R

/**
 * @Created by 김현국 2023/06/19
 */
enum class MyPageItem(@StringRes val value: Int) {
    Announcement(value = R.string.announcement),
    UsingGuide(value = R.string.using_guide),
    Inquiry(value = R.string.inquiry),
    Makers(value = R.string.makers),
}

