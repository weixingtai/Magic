package com.suromo.magic.repo

import com.suromo.magic.db.dao.LotteryDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/23 下午7:28
 * desc   :
 */
@Singleton
class LotteryRepository @Inject constructor(private val lotteryDao: LotteryDao) {

    fun getLotteries() = lotteryDao.getLotteries()

    fun getLotteryByPeriod(period: Int) = lotteryDao.getLotteryByPeriod(period)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: LotteryRepository? = null

        fun getInstance(lotteryDao: LotteryDao) =
            instance ?: synchronized(this) {
                instance ?: LotteryRepository(lotteryDao).also { instance = it }
            }
    }
}