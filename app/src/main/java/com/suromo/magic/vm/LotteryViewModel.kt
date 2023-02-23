package com.suromo.magic.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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
    savedStateHandle: SavedStateHandle,
    repository: LotteryRepository
) : ViewModel() {

    private var year:Int? = savedStateHandle["year"]

//    val lotteries = repository.getLotteryHistoryByYear(year?: 2023).cachedIn(viewModelScope)
    val lotteries = repository.getLotteries()



}