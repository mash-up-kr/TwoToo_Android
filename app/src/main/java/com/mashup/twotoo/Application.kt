package com.mashup.twotoo
import android.app.Application
import com.mashup.twotoo.di.ApplicationComponent
import com.mashup.twotoo.di.DaggerApplicationComponent

class Application : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}
