package com.suromo.magic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.suromo.magic.db.dao.HistoryDao
import com.suromo.magic.db.dao.LotteryDao
import com.suromo.magic.db.entity.History
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.db.util.Converters
import com.suromo.magic.db.util.DATABASE_NAME
import com.suromo.magic.log.MLog
import com.suromo.magic.worker.MagicDatabaseWorker

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23 下午6:56
 * desc   :
 */
@Database(entities = [Lottery::class, History::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lotteryDao(): LotteryDao
    abstract fun historyDao(): HistoryDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            MLog.d("database getInstance")
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            MLog.d("buildDatabase")
                            val request = OneTimeWorkRequestBuilder<MagicDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }

}
