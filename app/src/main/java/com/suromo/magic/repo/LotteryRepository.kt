package com.suromo.magic.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.suromo.magic.db.dao.LotteryDao
import com.suromo.magic.net.api.LotteryService
import com.suromo.magic.net.pager.LotteryPagingSource
import com.suromo.magic.net.response.Lottery
import com.suromo.magic.net.response.LotteryResponse
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
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
    private val dao: LotteryDao,
    private val service: LotteryService
    ) {

    fun getLotteryHistoryByYear(year: Int):Flow<PagingData<Lottery>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { LotteryPagingSource(service, year) }
        ).flow
    }

    fun getLotteries(){
        runBlocking {
            runCatching {
                service.getLotteryHistoryByYear()
            }.onSuccess {
                Log.d("wxt","result:")
            }.onFailure {
                Log.d("wxt","请求失败")
            }
        }

    }

    fun getLotteryByPeriod(period: Int) = dao.getLotteryByPeriod(period)


    companion object {

        private const val NETWORK_PAGE_SIZE = 365

        // For Singleton instantiation
        @Volatile private var instance: LotteryRepository? = null

        fun getInstance(lotteryDao: LotteryDao,service: LotteryService) =
            instance ?: synchronized(this) {
                instance ?: LotteryRepository(lotteryDao,service).also { instance = it }
            }
    }
}