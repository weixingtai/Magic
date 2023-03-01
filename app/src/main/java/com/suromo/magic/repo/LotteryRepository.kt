package com.suromo.magic.repo

import com.suromo.magic.db.dao.LotteryDao
import com.suromo.magic.db.entity.Lottery
import kotlinx.coroutines.flow.Flow
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

    fun getLotteries() : Flow<List<Lottery>> {
        return dao.getLotteries()
    }

}