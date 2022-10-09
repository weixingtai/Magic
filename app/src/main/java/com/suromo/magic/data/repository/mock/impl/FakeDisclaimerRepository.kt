package com.suromo.magic.data.repository.mock.impl

import com.suromo.magic.data.repository.DisclaimerRepository
import com.suromo.magic.data.repository.PostsRepository
import com.suromo.magic.data.source.local.impl.disclaimer
import com.suromo.magic.data.source.local.impl.posts
import com.suromo.magic.ui.bean.Disclaimer
import com.suromo.magic.ui.bean.Post
import com.suromo.magic.ui.bean.PostsFeed
import com.suromo.magic.ui.bean.Result
import com.suromo.magic.util.addOrRemove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
class FakeDisclaimerRepository : DisclaimerRepository {
    override suspend fun getDisclaimer(): Result<Disclaimer> {
        return Result.Success(disclaimer)
    }
}