package com.suromo.magic.ui.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suromo.magic.data.repository.AboutRepository
import com.suromo.magic.data.repository.MessageRepository

/**
 * author : weixingtai
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/2022/4/30
 * desc   :
 */
class MessageViewModel (
    private val messageRepository: MessageRepository
) : ViewModel() {

    /**
     * Factory for HomeViewModel that takes PostsRepository as a dependency
     */
    companion object {
        fun provideFactory(
            messageRepository: MessageRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MessageViewModel(messageRepository) as T
            }
        }
    }
}