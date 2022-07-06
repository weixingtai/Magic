package com.suromo.common.log

import com.orhanobut.logger.*
import com.suromo.common.BuildConfig


/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/07/2022/7/2
 * desc   : 日志框架，复用Logger
 *          1.需在依赖中引入Logger日志框架 implementation 'com.orhanobut:logger:2.2.0'；
 *          2.需在Application基类中进行初始化，调用initLogger()方法，更多功能可在此方法中增加；
 *          3.在需要用到的地方使用MLog.d("日志内容")即可。
 */
object MLog {

    fun initLogger(){

//        Logger.addLogAdapter(AndroidLogAdapter())

        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
//            .showThreadInfo(true)                // 是否显示线程信息，默认显示(Thread: main)
//            .methodCount(2)                      // 显示方法的调用深度，默认显示2个
//            .methodOffset(5)                     // 隐藏内部方法的调用， 默认隐藏5个
//            .logStrategy(locat)                  // 修改日志打印策略，默认显示LogCat
            .tag("MLog")                      // 全局日志标签显示，默认显示PRETTY_LOGGER
            .build()


        Logger.addLogAdapter(object : DiskLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG        //只在项目为调试模式时打印日志
            }
        })
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

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    fun wtf(message: String, vararg args: Any?) {
        Logger.wtf(message, *args)
    }

    /**
     * Formats the given json content and print it
     */
    fun json(json: String?) {
        Logger.json(json)
    }

    /**
     * Formats the given xml content and print it
     */
    fun xml(xml: String?) {
        Logger.xml(xml)
    }

}