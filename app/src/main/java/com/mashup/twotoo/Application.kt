package com.mashup.twotoo
import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.mashup.twotoo.di.ApplicationComponent
import com.mashup.twotoo.di.DaggerApplicationComponent
import com.mashup.twotoo.presenter.home.HomeComponent
import com.mashup.twotoo.presenter.home.HomeComponentProvider

class Application : Application(), HomeComponentProvider {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create()
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY)
    }

    override fun provideHomeComponent(): HomeComponent {
        return applicationComponent.homeComponent().create()
    }
}
