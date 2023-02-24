package com.suromo.magic.log

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/21
 * desc   : 日志框架，复用Logger
 */
object MLog {

    fun initLogger(){

        Logger.addLogAdapter(AndroidLogAdapter())

//        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
//            .showThreadInfo(true) // 是否显示线程信息，默认为true
//            .methodCount(2) // (可选) 显示方法的行数，默认为2
//            .methodOffset(5) // (可选) 显示方法的深度，默认为5
//            .logStrategy(LogcatLogStrategy()) // (可选) 改变打印策略，默认为LogCat
//            .tag("wxt") // (可选) 全局标签. 默认为PRETTY_LOGGER
//            .build()
//
//        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
//            override fun isLoggable(priority: Int, tag: String?): Boolean {
//                return BuildConfig.DEBUG
//            }
//        })
    }

    fun log(priority: Int, tag: String?, message: String?, throwable: Throwable?) {
        Logger.log(priority, tag, message, throwable)
    }

    fun d(message: String, vararg args: Any?) {
        Logger.d(message, *args)
    }

    fun d(`object`: Any?) {
        Logger.d(`object`)
    }

    fun e(message: String, vararg args: Any?) {
        Logger.e(null, message, *args)
    }

    fun e(throwable: Throwable?, message: String, vararg args: Any?) {
        Logger.e(throwable, message, *args)
    }

    fun i(message: String, vararg args: Any?) {
        Logger.i(message, *args)
    }

    fun v(message: String, vararg args: Any?) {
        Logger.v(message, *args)
    }

    fun w(message: String, vararg args: Any?) {
        Logger.w(message, *args)
    }

    fun wtf(message: String, vararg args: Any?) {
        Logger.wtf(message, *args)
    }

    fun json(json: String?) {
        Logger.json(json)
    }

    fun xml(xml: String?) {
        Logger.xml(xml)
    }


}