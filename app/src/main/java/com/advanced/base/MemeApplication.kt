package com.advanced.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MemeApplication  : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}