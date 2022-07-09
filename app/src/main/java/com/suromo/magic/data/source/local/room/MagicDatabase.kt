package com.suromo.magic.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suromo.magic.data.source.local.room.bean.User
import com.suromo.magic.data.source.local.room.dao.UserDao

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/27
 * desc   : room数据库的使用方法
 *          1.在bean文件夹中创建表对应的实体类
 *          2.在dao文件夹中创建对应的数据库查询类
 *          3.在本类中添加新的实体类::class
 *          4.在本类中添加新的dao实例
 *          5.在使用处调用getInstance()方法并获取dao实例
 *          6.进行增删改查
 */

@Database(
    entities = [
        // 1.添加新的实体类::class
        User::class
    ],
    version = 1,
    exportSchema = false
)



abstract class MagicDatabase : RoomDatabase() {

    // 2.添加新的dao实例
    abstract fun userDao() : UserDao

    companion object {

        private const val DATABASE_NAME = "magic-db"

        @Volatile private var instance: MagicDatabase ?= null
        // 3.在使用处调用并获取dao实例
        fun getInstance(context: Context): MagicDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it}
            }
        }

        private fun buildDatabase(context: Context): MagicDatabase {
            return Room.databaseBuilder(context, MagicDatabase::class.java, DATABASE_NAME)
//                .addCallback(
//                    object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            super.onCreate(db)
//                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
//                                .setInputData(workDataOf(KEY_FILENAME to DATA_NAME))
//                                .build()
//                            WorkManager.getInstance(context).enqueue(request)
//                        }
//                    }
//                )
                .build()
        }
    }

}