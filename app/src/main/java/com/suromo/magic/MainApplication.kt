package com.suromo.magic

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.suromo.magic.log.MLog
import dagger.hilt.android.HiltAndroidApp

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23 下午7:24
 * desc   :
 */
@HiltAndroidApp
class MainApplication : Application() {

    companion object {
        lateinit var mApplicationContext:MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationContext = this
        MLog.initLogger()
        Hawk.init(mApplicationContext).build();


//        val request = OneTimeWorkRequestBuilder<MagicDatabaseWorker>().build()
//        WorkManager.getInstance(mApplicationContext).enqueue(request)

    }

}