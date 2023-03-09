package com.suromo.magic.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.hawk.Hawk
import com.suromo.magic.db.dao.HistoryDao
import com.suromo.magic.db.dao.LotteryDao
import com.suromo.magic.db.entity.History
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.log.MLog
import com.suromo.magic.repo.LotteryRepository
import com.suromo.magic.ui.bean.RequestResult
import com.suromo.magic.util.getPreviousLongPeriod
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val repository: LotteryRepository,
    private val historyDao: HistoryDao,
    private val lotteryDao: LotteryDao,
) : ViewModel() {

    private val _lotteries = MutableLiveData<List<Lottery>>()
    private val _history = MutableLiveData<History>()

    val lotteries: LiveData<List<Lottery>> = _lotteries
    val history: LiveData<History> = _history


    init {
        //如果数据还没有初始化或者没有更新到最新的数据，就从网络获取数据
//        if (!Hawk.get("init_history",false) || Hawk.get("init_long_period",0)!= getPreviousLongPeriod()){
            MLog.d("从网络获取数据")
            getLotteriesFromNetwork()
//        } else {
//            MLog.d("从本地获取数据")
//            getLotteriesFromDb()
//        }
    }

    private fun getLotteriesFromNetwork(){
        viewModelScope.launch {
            val source = async { repository.getLotteriesFromNetwork() }

            when(val result = source.await()) {
                is RequestResult.Success -> {
                    val lotteries = result.data
                    val lotteriesDb = mutableListOf<Lottery>()
                    for (lottery in lotteries) {
                        val lotteryDb = Lottery(
                            longperiod = lottery.longperiod,
                            period = lottery.period,
                            numbers = lottery.numbers,
                            sx = lottery.sx,
                            wx = lottery.wx,
                            date = lottery.date
                        )
                        lotteriesDb.add(lotteryDb)
                    }
                    lotteryDao.insertAll(lotteriesDb)
                    MLog.d( "从网络获取数据存到数据库成功")
                    Hawk.put("init_history",true)
                    Hawk.put("init_long_period",lotteries[0].longperiod)

                    _lotteries.apply {
                        value = lotteriesDb
                    }

//                    getLotteriesFromDb()
                }
                is RequestResult.Error -> {
                    MLog.d( "从网络获取数据存到数据库失败")
                }
            }
        }
    }

    private fun getLotteriesFromDb(){
        viewModelScope.launch {
            val source = async { repository.getLotteriesFromDb() }

            when(val result = source.await()) {
                is RequestResult.Success -> {
                    _lotteries.apply {
                        value = result.data
                    }
                }
                is RequestResult.Error -> {
                    MLog.d( "从数据库获取数据失败")
                }
            }
        }
    }

//    private fun LotteriesFromLocal(){
//
//        //获取当前下一期开奖期数
//        val longPeriod = getNetLongPeriod()
//
//        viewModelScope.launch {
//
//            val sourceHistory = async { repository.getHistories() }
//            val sourceLottery = async { repository.getLotteries() }
//
//            val resultHistory = sourceHistory.await()
//            val resultLottery = sourceLottery.await()
//
//            when(resultHistory) {
//                is RequestResult.Success -> {
//
//                }
//                is RequestResult.Error -> {
//                    val errorMessage = ErrorMessage(
//                        id = UUID.randomUUID().mostSignificantBits,
//                        messageId = R.string.load_error
//                    )
//                    Log.d("wxt", "errorMessage:" + errorMessage.messageId)
//                }
//            }
//
//            when(resultHistory) {
//                is RequestResult.Success ->{
//
//                    val histories: List<History> = resultHistory.data
//                    //最新一期推荐号码已存入数据库
//                    if (histories.isNotEmpty() && histories[histories.size-1].longperiod == longPeriod){
//                        _history.apply {
//                            value = histories[histories.size-1]
//                        }
//                    } else {
//                        when(resultLottery) {
//                            is RequestResult.Success -> {
//                                _lotteries.apply {
//                                    value = resultLottery.data
//                                }
//                                val lotteries: List<Lottery> = resultLottery.data
//                                if (lotteries.isNotEmpty() && histories.isNotEmpty()){
//                                    if (lotteries[lotteries.size-1].longperiod == getPreviousLongPeriod()) {
//                                        if (histories[histories.size-1].longperiod == getPreviousLongPeriod()) {
//                                            val mIsWin = checkIsWin(lotteries[lotteries.size-1].numbers,histories[histories.size-1].numbers)
//                                            val history: History = if (mIsWin) {
//                                                History(
//                                                    longperiod = histories[histories.size-1].longperiod,
//                                                    numbers = histories[histories.size-1].numbers,
//                                                    bet = histories[histories.size-1].bet,
//                                                    win = histories[histories.size-1].bet * 2
//                                                )
//                                            }else {
//                                                History(
//                                                    longperiod = histories[histories.size-1].longperiod,
//                                                    numbers = histories[histories.size-1].numbers,
//                                                    bet = histories[histories.size-1].bet,
//                                                    win = -histories[histories.size-1].bet
//                                                )
//                                            }
//                                            historyDao.update(history)
//                                        }
//                                    }
//
//
//                                    val bet: Int = if (histories.size > 2) {
//                                        //根据上期确定这次投注金额
//                                        if (histories[histories.size-2].win > 0){
//                                            histories[histories.size-2].win * 2
//                                        } else {
//                                            500
//                                        }
//                                    } else {
//                                        500
//                                    }
//
//                                    val strategy = MissSevenStrategy()
//                                    strategy.initHistory(lotteries)
//
//                                    val history = History(
//                                        longperiod = longPeriod,
//                                        numbers = strategy.getNextRecommend().num,
//                                        bet = bet,
//                                        win = 0
//                                    )
//
//                                    historyDao.insert(history)
//
//                                    _history.apply {
//                                        value = history
//                                    }
//                                }
//                            }
//                            is RequestResult.Error -> {
//                                val errorMessage = ErrorMessage(
//                                    id = UUID.randomUUID().mostSignificantBits,
//                                    messageId = R.string.load_error
//                                )
//                                Log.d("wxt","errorMessage:"+errorMessage.messageId)
//                            }
//                        }
//                    }
//                }
//                is RequestResult.Error -> {
//                    val errorMessage = ErrorMessage(
//                        id = UUID.randomUUID().mostSignificantBits,
//                        messageId = R.string.load_error
//                    )
//                    Log.d("wxt","errorMessage:"+errorMessage.messageId)
//                }
//            }
//        }
//    }
}