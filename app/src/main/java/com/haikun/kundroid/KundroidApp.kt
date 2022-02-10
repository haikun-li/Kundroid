package com.haikun.kundroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class KundroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}