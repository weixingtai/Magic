package com.suromo.common

import android.app.Application
import com.orhanobut.logger.*

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
        initLogger()
    }

    /**
     * 初始化logger日志工具，自定义tag为MLog,debug版本打开日志，release版本关闭日志
     */
    private fun initLogger(){
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("MLog")
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}