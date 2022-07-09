package com.suromo.magic.data.di

import android.content.Context
import com.suromo.magic.data.source.local.room.MagicDatabase
import com.suromo.magic.data.source.local.room.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/7
 * desc   :
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MagicDatabase {
        return MagicDatabase.getInstance(context)
    }

    @Provides
    fun provideUserDao(magicDatabase: MagicDatabase): UserDao {
        return magicDatabase.userDao()
    }

}