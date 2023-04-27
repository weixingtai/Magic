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
        val strategy1 = MissAverageTenStrategy()

        recommendViewModel.lotteries.observe(viewLifecycleOwner) { lotteries ->
            val lotteriesCopy = mutableListOf<Lottery>()
            lotteriesCopy.addAll(lotteries)
            lotteriesCopy.reverse()
            val lotteriesTest = mutableListOf<Lottery>()
            for (lottery in lotteriesCopy){
                lotteriesTest.add(lottery)
                if (lottery.longperiod > 2023021){
                    strategy1.initHistory(lotteriesTest)
                }
            }

            val historyList = mutableListOf<BaseStrategy.Recommend>()
            val result = mutableListOf<MutableSet<Int>>()
            for (lottery in lotteriesCopy){
                if (lottery.longperiod > 2023022){
                    val openList = mutableSetOf<Int>()
                    for (num in lottery.numbers.split(",")){
                        openList.add(num.toInt())
                    }
                    result.add(openList)
                    val open = BaseStrategy.Recommend(
                        longperiod = lottery.longperiod,
                        numbers = openList
                    )
                    historyList.add(open)
                }
            }
//            Log.d("wxt","开奖号码：${historyList}")


            val recommendStrategy = strategy1.strategy1
            val openResultShow = mutableListOf<String>()
            val openResult = mutableListOf<String>()
            for (i in 0 until recommendStrategy.size - 1){
//                Log.d("wxt","第：${historyList[i].longperiod}期")
//                Log.d("wxt","开奖号码：${historyList[i].numbers}")
                for (recommendList in recommendStrategy[i]){
//                    Log.d("wxt","第${recommendList[0].longperiod}期：")
                    for (j in 0 until recommendList.size){
//                        Log.d("wxt","号码推荐：${recommendList[j].numbers}")
                        var mIsWin = true
                        var mLostNum = 0
                        for (num in recommendList[j].numbers){
                            if (historyList[i].numbers.contains(num)){
                                mIsWin = false
                                mLostNum++
                            }
                        }
                        if (mIsWin){
                            openResultShow.add("中")
                            openResult.add("中 $mLostNum")
                            Log.d("wxt","第${historyList[i].longperiod}期：策略中")
                            Log.d("wxt","命中号码：$mLostNum 个")
                        } else {
                            openResultShow.add("爆")
                            openResult.add("爆 $mLostNum")
                            Log.d("wxt","第${historyList[i].longperiod}期：策略爆")
                            Log.d("wxt","命中号码：$mLostNum 个")
                        }
                    }
                }
            }
            for (recommendList in recommendStrategy.last()){
                Log.d("wxt","第${recommendList[0].longperiod}期：")
                binding.strategy1.text = recommendList[0].longperiod.toString()
                for (j in 0 until recommendList.size) {
                    Log.d("wxt", "号码推荐：${recommendList[j].numbers}")
                    binding.strategy2.text = recommendList[j].numbers.toString()
                }
            }
            binding.strategy3.text = openResult.toString()
            binding.strategy4.text = "已爆${openResultShow.size - 1 - openResultShow.lastIndexOf("中")}期"
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}