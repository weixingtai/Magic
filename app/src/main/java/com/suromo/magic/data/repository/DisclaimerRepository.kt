package com.suromo.magic.data.repository

import com.suromo.magic.ui.bean.Disclaimer
import com.suromo.magic.ui.bean.Post
import com.suromo.magic.ui.bean.Result

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
interface DisclaimerRepository {
    /**
     * Get a specific JetNews post.
     */
    suspend fun getDisclaimer(): Result<Disclaimer>
}