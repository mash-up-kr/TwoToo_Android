package com.mashup.twotoo
import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.mashup.twotoo.di.ApplicationComponent
import com.mashup.twotoo.di.DaggerApplicationComponent
import com.mashup.twotoo.presenter.di.ViewModelFactoryComponent
import com.mashup.twotoo.presenter.di.ViewModelFactoryComponentProvider

class Application : Application(), ViewModelFactoryComponentProvider {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.NATIVE_APP_KEY)
    }

    override fun provideViewModelFactoryComponent(): ViewModelFactoryComponent {
        return applicationComponent.viewModelComponent().create()
    }
}
