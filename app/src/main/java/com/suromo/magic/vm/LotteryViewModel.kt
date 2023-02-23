package com.suromo.magic.vm

import androidx.lifecycle.ViewModel
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.repo.LotteryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/23 下午7:28
 * desc   :
 */
@HiltViewModel
class LotteryViewModel @Inject constructor(
    lotteryRepository: LotteryRepository
) : ViewModel() {


    val lotteries: Flow<List<Lottery>> = lotteryRepository.getLotteries()


}