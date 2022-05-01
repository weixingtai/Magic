package com.suromo.magic.data.repository

import android.content.Context
import com.suromo.magic.data.repository.mock.impl.*

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
    val postsRepository: PostsRepository
    val aboutRepository: AboutRepository
    val disclaimerRepository: DisclaimerRepository
    val messageRepository: MessageRepository
    val collectionRepository: CollectionRepository
    val settingRepository: SettingRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val postsRepository: PostsRepository by lazy {
        FakePostsRepository()
    }

    override val aboutRepository: AboutRepository by lazy {
        FakeAboutRepository()
    }

    override val disclaimerRepository: DisclaimerRepository by lazy {
        FakeDisclaimerRepository()
    }

    override val messageRepository: MessageRepository by lazy {
        FakeMessageRepository()
    }

    override val collectionRepository: CollectionRepository by lazy {
        FakeCollectionRepository()
    }

    override val settingRepository: SettingRepository by lazy {
        FakeSettingRepository()
    }
}