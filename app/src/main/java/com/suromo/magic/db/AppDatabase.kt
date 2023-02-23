package com.suromo.magic.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.suromo.magic.db.dao.LotteryDao
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.db.util.Converters
import com.suromo.magic.db.util.DATABASE_NAME

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23 下午6:56
 * desc   :
 */
@Database(entities = [Lottery::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lotteryDao(): LotteryDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
//                .addCallback(
//                    object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
//                                .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
//                                .build()
//                            WorkManager.getInstance(context).enqueue(request)
//                        }
//                    }
//                )
                .build()
        }
    }
}
