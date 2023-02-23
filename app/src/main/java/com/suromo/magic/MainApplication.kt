package com.suromo.magic

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23 下午7:24
 * desc   :
 */
@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}