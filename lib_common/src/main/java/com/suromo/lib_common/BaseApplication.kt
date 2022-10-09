package com.suromo.lib_common

import android.app.Application

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time  : 2022/04/26
 * desc  : 应用基类
 */
open class BaseApplication : Application() {
    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        MLog.initLogger()
    }

}