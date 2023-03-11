package com.suromo.magic.repo

import com.suromo.magic.db.AppDatabase
import com.suromo.magic.db.dao.HistoryDao
import com.suromo.magic.db.dao.LotteryDao
import com.suromo.magic.db.entity.History
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.log.MLog
import com.suromo.magic.net.api.LotteryService
import com.suromo.magic.ui.bean.RequestResult
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
    private val lotteryDao: LotteryDao,
    private val historyDao: HistoryDao
) {

    suspend fun getLotteriesFromNetwork() : RequestResult<List<com.suromo.magic.net.response.Lottery>>{
        return withContext(Dispatchers.IO) {
            val result = LotteryService.create().getLotteryHistoryByYear().body.item
            RequestResult.Success(result)
        }
    }

    suspend fun getLotteryRecommendByYearFromNetwork() : RequestResult<List<com.suromo.magic.net.response.Lottery>>{
        return withContext(Dispatchers.IO) {
            val result = LotteryService.create().getLotteryRecommendByYear().body.item
            RequestResult.Success(result)
        }
    }

    suspend fun getLotteriesFromDb() : RequestResult<List<Lottery>> {
        return withContext(Dispatchers.IO) {
            val result = lotteryDao.getLotteries()
            RequestResult.Success(result)
        }
    }

    suspend fun getHistories() : RequestResult<List<History>> {
        return withContext(Dispatchers.IO) {
            val result = historyDao.getHistories()
            RequestResult.Success(result)
        }
    }

    suspend fun getHistoryByLongPeriod(longPeriod: Int) : RequestResult<History> {
        return withContext(Dispatchers.IO) {
            val result = historyDao.getHistoryByLongPeriod(longPeriod)
            RequestResult.Success(result)
        }
    }

}