package com.mashup.twotoo.presenter.di

import com.mashup.twotoo.presenter.home.di.HomeComponentProvider
import com.mashup.twotoo.presenter.mypage.di.UserComponentProvider

/**
 * App Module에서 Component를 알기 위한 인터페이스를 정의합니다.
 */
interface ScreenComponentProvider : HomeComponentProvider, UserComponentProvider
