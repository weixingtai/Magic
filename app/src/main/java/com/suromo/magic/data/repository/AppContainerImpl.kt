package com.suromo.magic.data.repository

import android.content.Context

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/29
 * desc   :
 */
/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
//    val postsRepository: PostsRepository
//    val interestsRepository: InterestsRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : AppContainer {

//    override val postsRepository: PostsRepository by lazy {
//        FakePostsRepository()
//    }
//
//    override val interestsRepository: InterestsRepository by lazy {
//        FakeInterestsRepository()
//    }
}