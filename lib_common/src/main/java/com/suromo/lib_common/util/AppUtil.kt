package com.suromo.lib_common.util

import android.annotation.SuppressLint
import android.app.Application
import java.lang.reflect.InvocationTargetException

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time  : 2022/04/2022/4/26
 * desc  :
 */
object AppUtil {

    @SuppressLint("PrivateApi")
    fun getApplication(): Application? {
        try {
            return Class.forName("android.app.ActivityThread")
                .getMethod("currentApplication")
                .invoke(null ) as Application
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return null
    }
}