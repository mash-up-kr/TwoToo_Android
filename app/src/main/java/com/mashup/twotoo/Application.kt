package com.mashup.twotoo
import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.mashup.twotoo.di.ApplicationComponent
import com.mashup.twotoo.di.DaggerApplicationComponent
import com.mashup.twotoo.presenter.di.ScreenComponentProvider
import com.mashup.twotoo.presenter.history.di.HistoryComponent
import com.mashup.twotoo.presenter.home.di.HomeComponent
import com.mashup.twotoo.presenter.mypage.di.UserComponent

class Application : Application(), ScreenComponentProvider {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY)
    }

    override fun provideHomeComponent(): HomeComponent {
        return applicationComponent.homeComponent().create()
    }

    override fun provideUserComponent(): UserComponent {
        return applicationComponent.userComponent().create()
    }
    override fun provideHistoryComponent(): HistoryComponent {
        return applicationComponent.historyComponent().create()
    }
}
