package com.suromo.magic.repo

import com.suromo.magic.db.dao.LotteryDao
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.ui.bean.RequestResult
import com.suromo.magic.ui.view.home.Recommend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/23 下午7:28
 * desc   :
 */
@Singleton
class LotteryRepository @Inject constructor(
    private val dao: LotteryDao
    ) {

    suspend fun getLotteries() : List<Lottery> {
        return withContext(Dispatchers.IO) {
            dao.getLotteries()
        }
    }

    fun getRecommend() : RequestResult<Recommend>{
        val recommend = Recommend(period = "2013")
        return RequestResult.Success(recommend)
    }
}