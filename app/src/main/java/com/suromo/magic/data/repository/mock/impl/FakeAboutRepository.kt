package com.suromo.magic.data.repository.mock.impl

import com.suromo.magic.data.repository.AboutRepository
import com.suromo.magic.data.source.local.impl.about
import com.suromo.magic.ui.bean.About
import com.suromo.magic.ui.bean.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   :
 */
class FakeAboutRepository : AboutRepository {
    override suspend fun getAbout(): Result<About> {
        return Result.Success(about)
    }
}