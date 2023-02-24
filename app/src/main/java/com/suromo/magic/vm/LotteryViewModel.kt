package com.suromo.magic.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.suromo.magic.repo.LotteryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/23 下午7:28
 * desc   :
 */
@HiltViewModel
class LotteryViewModel @Inject constructor(
    repository: LotteryRepository
) : ViewModel() {

    val lotteries = repository.getLotteries().asLiveData()

}