package com.suromo.magic.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.suromo.magic.db.entity.History
import com.suromo.magic.db.entity.Lottery
import kotlinx.coroutines.flow.Flow

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/23 下午7:04
 * desc   :
 */
@Dao
interface HistoryDao {
    @Query("SELECT * FROM history ORDER BY longperiod")
    fun getHistory(): List<History>

    @Query("SELECT * FROM history WHERE longperiod = :longperiod ORDER BY numbers")
    fun getHistoryByLongPeriod(longperiod: Int): Flow<List<History>>

    @Query("SELECT * FROM history WHERE date = :date ORDER BY numbers")
    fun getHistoryByDate(date: String): History

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: History)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(history: List<History>)
    @Query("DELETE FROM history")
    suspend fun clearAll()
}