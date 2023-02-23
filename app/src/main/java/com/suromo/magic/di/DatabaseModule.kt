package com.suromo.magic.di

import android.content.Context
import com.suromo.magic.db.AppDatabase
import com.suromo.magic.db.dao.LotteryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23
 * desc   :
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideLotteryDao(appDatabase: AppDatabase): LotteryDao {
        return appDatabase.lotteryDao()
    }
}