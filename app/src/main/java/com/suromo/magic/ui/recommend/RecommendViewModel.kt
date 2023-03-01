package com.suromo.magic.ui.recommend

import androidx.lifecycle.*
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.repo.LotteryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val repository: LotteryRepository,
) : ViewModel() {

    private val _text = MutableLiveData<Lottery>()

    val text: LiveData<Lottery> = _text

    fun refreshLotteries() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLotteries()
        }
    }

}