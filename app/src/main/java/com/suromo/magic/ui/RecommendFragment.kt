package com.suromo.magic.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.suromo.magic.databinding.FragmentRecommendBinding
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.strategy.*
import com.suromo.magic.vm.RecommendViewModel

class RecommendFragment : Fragment() {

    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val recommendViewModel : RecommendViewModel by activityViewModels()

        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val strategy = MissSevenStrategy()

        recommendViewModel.lotteriesRecommend.observe(viewLifecycleOwner) {lotteriesRecommend->
            strategy.initRecommend(lotteriesRecommend)

            recommendViewModel.lotteries.observe(viewLifecycleOwner) { lotteries ->
                val lotteriesCopy = mutableListOf<Lottery>()
                lotteriesCopy.addAll(lotteries)
                lotteriesCopy.reverse()
                val lotteriesTest = mutableListOf<Lottery>()

                for (lottery in lotteriesCopy){
                    lotteriesTest.add(lottery)
                    if (lottery.longperiod > 2023011){
//                        Log.d("wxt","lotteriesTest:${lotteriesTest.size}")
                        strategy.initHistory(lotteriesTest)
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

                val recommendList = strategy.strategy1
                Log.d("wxt","预测号码：$recommendList")
                val recommend = recommendList.removeLast()
                binding.strategyTitle.text = "第${recommend.longperiod}期号码推荐："
                binding.strategy1.text = "号码推荐：${recommend.numbers}"
//
                Log.d("wxt","开奖号码：$historyList")

                val openResult = mutableListOf<String>()

                for (i in 0 until recommendList.size){
                    var mIsWin = true
                    for (num in recommendList[i].numbers){
                        if (historyList[i].numbers.contains(num)){
                            mIsWin = false
                        }
                    }
                    if (mIsWin){
                        openResult.add("中")
                        Log.d("wxt","第${historyList[i].longperiod}期：策略中")
                    } else {
                        openResult.add("爆")
                        Log.d("wxt","第${historyList[i].longperiod}期：策略爆")
                    }
                }
                binding.strategy2.text = openResult.toString()
                binding.strategy3.text = "已爆${openResult.size - 1 - openResult.lastIndexOf("中")}期"
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}