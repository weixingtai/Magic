package com.suromo.magic.ui.bean

import androidx.annotation.DrawableRes

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
data class About(
    val id: String,
    val url: String,
    val name: String,
    val occupation: String,
    val introduction: String,
    val email: String,
    val youtube: String,
    val instagram: String,
    val facebook: String,
    val tiktok: String,
    val wechat: String,
    val twitter: String,
    @DrawableRes val imageId: Int,
    @DrawableRes val imageThumbId: Int
)
