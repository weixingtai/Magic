package com.suromo.magic.data.repository

import com.suromo.magic.data.source.local.room.dao.UserDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/7
 * desc   :
 */
@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

}