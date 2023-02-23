package com.suromo.magic.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suromo.magic.db.entity.Lottery
import kotlinx.coroutines.flow.Flow

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/23 下午7:04
 * desc   :
 */
@Dao
interface LotteryDao {
    @Query("SELECT * FROM lottery ORDER BY numbers")
    fun getLotteries(): List<Lottery>

    @Query("SELECT * FROM lottery WHERE period = :period ORDER BY numbers")
    fun getLotteryByPeriod(period: Int): Flow<List<Lottery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lottery: Lottery)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lotteries: List<Lottery>)
}