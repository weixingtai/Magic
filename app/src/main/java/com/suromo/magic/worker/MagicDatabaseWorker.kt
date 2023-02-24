package com.suromo.magic.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.suromo.magic.db.AppDatabase
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.log.MLog
import com.suromo.magic.net.api.LotteryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
class MagicDatabaseWorker (
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        MLog.d("进入 doWork")
        try {

            val lotteries = LotteryService.create().getLotteryHistoryByYear().body.item

            val lotteriesDb = mutableListOf<Lottery>()
            for (lottery in lotteries) {
                val lotteryDb = Lottery(
                    longperiod = lottery.longperiod,
                    period = lottery.period,
                    numbers = lottery.numbers,
                    sx = lottery.sx,
                    wx = lottery.wx,
                    date = lottery.date
                )
                lotteriesDb.add(lotteryDb)
            }
            val database = AppDatabase.getInstance(applicationContext)
            database.lotteryDao().insertAll(lotteriesDb)
            MLog.d( "从网络获取数据存到数据库成功")
            Result.success()
        } catch (exception: Exception) {
            MLog.d( "从网络获取数据存到数据库失败，异常：$exception")
            Result.failure()
        }
    }

}