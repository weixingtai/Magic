package com.suromo.magic.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.suromo.magic.databinding.FragmentHistoryBinding
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.strategy.BaseStrategy
import com.suromo.magic.strategy.LuckyMissSevenNumStrategy
import com.suromo.magic.strategy.MissSevenStrategy
import com.suromo.magic.vm.HistoryViewModel

class StatisticsFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val historyViewModel : HistoryViewModel by activityViewModels()

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val strategy1 = LuckyMissSevenNumStrategy()

        historyViewModel.lotteriesRecommend.observe(viewLifecycleOwner) {lotteriesRecommend->
            strategy1.initRecommend(lotteriesRecommend)

            historyViewModel.lotteries.observe(viewLifecycleOwner) { lotteries ->
                val lotteriesCopy = mutableListOf<Lottery>()
                lotteriesCopy.addAll(lotteries)
                lotteriesCopy.reverse()
                val lotteriesTest = mutableListOf<Lottery>()

                for (lottery in lotteriesCopy){
                    lotteriesTest.add(lottery)
                    if (lottery.longperiod > 2023011){
//                        Log.d("wxt","lotteriesTest:${lotteriesTest.size}")
                        strategy1.initHistory(lotteriesTest)
                    }
                }

                val historyList = mutableListOf<BaseStrategy.Recommend>()
                val result = mutableListOf<MutableSet<Int>>()
                for (lottery in lotteriesCopy){
                    if (lottery.longperiod > 2023012){
                        val openList = mutableSetOf<Int>()
                        for (num in lottery.numbers.split(",")){
                            openList.add(num.toInt())
                        }
//                        Log.d("wxt","第${lottery.longperiod} 期")
                        result.add(openList)

                        val open = BaseStrategy.Recommend(
                            longperiod = lottery.longperiod,
                            numbers = openList
                        )
                        historyList.add(open)
                    }
                }

                val recommendList = strategy1.strategy1
//                Log.d("wxt","预测号码：$recommendList")
                val recommend = recommendList.removeLast()
                binding.strategyTitle.text = "第${recommend.longperiod}期号码推荐："
                binding.strategy1.text = "号码推荐：${recommend.numbers}"
//
//                Log.d("wxt","开奖号码：$historyList")

                val openResult = mutableListOf<String>()
                val openResultShow = mutableListOf<String>()

                for (i in 0 until recommendList.size){
                    var mIsWin = true
                    var mLostNum = 0
                    for (num in recommendList[i].numbers){
                        if (historyList[i].numbers.contains(num)){
                            mIsWin = false
                            mLostNum++
                        }
                    }
                    if (mIsWin){
                        openResultShow.add("中")
                        openResult.add("中 $mLostNum")
//                        Log.d("wxt","第${historyList[i].longperiod}期：策略中")
                    } else {
                        openResultShow.add("爆")
                        openResult.add("爆 $mLostNum")
//                        Log.d("wxt","第${historyList[i].longperiod}期：策略爆")
                    }
                }
                binding.strategy2.text = openResult.toString()
                binding.strategy3.text = "已爆${openResultShow.size - 1 - openResultShow.lastIndexOf("中")}期"
            }

        }



//        val strategy2 = MissSevenStrategy()
//        strategy2.initAddNum(1)
//
//        historyViewModel.lotteriesRecommend.observe(viewLifecycleOwner) {lotteriesRecommend->
//            strategy2.initRecommend(lotteriesRecommend)
//
//            historyViewModel.lotteries.observe(viewLifecycleOwner) { lotteries ->
//                val lotteriesCopy = mutableListOf<Lottery>()
//                lotteriesCopy.addAll(lotteries)
//                lotteriesCopy.reverse()
//                val lotteriesTest = mutableListOf<Lottery>()
//
//                for (lottery in lotteriesCopy){
//                    lotteriesTest.add(lottery)
//                    if (lottery.longperiod > 2023011){
////                        Log.d("wxt","lotteriesTest:${lotteriesTest.size}")
//                        strategy2.initHistory(lotteriesTest)
//                    }
//                }
//
//                val historyList = mutableListOf<BaseStrategy.Recommend>()
//                val result = mutableListOf<MutableSet<Int>>()
//                for (lottery in lotteriesCopy){
//                    if (lottery.longperiod > 2023012){
//                        val openList = mutableSetOf<Int>()
//                        for (num in lottery.numbers.split(",")){
//                            openList.add(num.toInt())
//                        }
////                        Log.d("wxt","第${lottery.longperiod} 期")
//                        result.add(openList)
//
//                        val open = BaseStrategy.Recommend(
//                            longperiod = lottery.longperiod,
//                            numbers = openList
//                        )
//                        historyList.add(open)
//                    }
//                }
//
//                val recommendList = strategy2.strategy1
////                Log.d("wxt","预测号码：$recommendList")
//                val recommend = recommendList.removeLast()
//                binding.strategy4.text = "第${recommend.longperiod}期号码推荐："
//                binding.strategy5.text = "号码推荐：${recommend.numbers}"
////
////                Log.d("wxt","开奖号码：$historyList")
//
//                val openResult = mutableListOf<String>()
//                val openResultShow = mutableListOf<String>()
//
//                for (i in 0 until recommendList.size){
//                    var mIsWin = true
//                    var mLostNum = 0
//                    for (num in recommendList[i].numbers){
//                        if (historyList[i].numbers.contains(num)){
//                            mIsWin = false
//                            mLostNum++
//                        }
//                    }
//                    if (mIsWin){
//                        openResultShow.add("中")
//                        openResult.add("中 $mLostNum")
////                        Log.d("wxt","第${historyList[i].longperiod}期：策略中")
//                    } else {
//                        openResultShow.add("爆")
//                        openResult.add("爆 $mLostNum")
////                        Log.d("wxt","第${historyList[i].longperiod}期：策略爆")
//                    }
//                }
//                binding.strategy6.text = openResult.toString()
//                binding.strategy7.text = "已爆${openResultShow.size - 1 - openResultShow.lastIndexOf("中")}期"
//            }
//
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}