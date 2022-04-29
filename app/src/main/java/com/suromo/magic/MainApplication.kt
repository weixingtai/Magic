package com.suromo.magic

import com.suromo.common.BaseApplication
import com.suromo.magic.data.repository.AppContainer
import com.suromo.magic.data.repository.AppContainerImpl

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time  : 2022/04/26
 * desc  : 应用基类
 */
class MainApplication : BaseApplication() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}