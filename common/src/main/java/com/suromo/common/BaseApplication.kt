package com.suromo.common

import android.app.Application
import com.orhanobut.logger.*
import com.suromo.common.log.MLog

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
        MLog.initLogger()
    }

}