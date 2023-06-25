package com.mashup.twotoo.presenter.home

import com.mashup.twotoo.presenter.MainActivity
import dagger.Subcomponent

/**
 * Component
 * 연결된 Module을 이용하여 의존성 객체를 생성하고,
 * Inject로 요청받은 인스턴스에 생성한 객체를 주입합니다.
 */

@Subcomponent()
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(mainActivity: MainActivity)

    fun getHomeViewModel(): HomeViewModel
}

interface HomeComponentProvider {
    fun provideHomeComponent(): HomeComponent
}
