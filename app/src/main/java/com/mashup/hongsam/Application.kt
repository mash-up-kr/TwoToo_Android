package com.mashup.hongsam
import android.app.Application
import com.mashup.hongsam.di.ApplicationComponent
import com.mashup.hongsam.di.DaggerApplicationComponent

class Application : Application() {
    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create()
    }
}
