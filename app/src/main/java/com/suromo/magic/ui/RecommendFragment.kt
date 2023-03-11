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
                    if (lottery.longperiod > 2022011){
//                        Log.d("wxt","lotteriesTest:${lotteriesTest.size}")
                        strategy.initHistory(lotteriesTest)
                    }
                }

                val historyList = mutableListOf<BaseStrategy.Recommend>()
                val result = mutableListOf<MutableSet<Int>>()
                for (lottery in lotteriesCopy){
                    if (lottery.longperiod > 2022012){
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

                val recommendList = strategy.strategy2
//                Log.d("wxt","预测号码：$recommendList")
                recommendList.removeLast()
//
//                Log.d("wxt","开奖号码：$historyList")

                for (i in 0 until recommendList.size){
                    var mIsWin = true
                    for (num in recommendList[i].numbers){
                        if (historyList[i].numbers.contains(num)){
                            mIsWin = false
                        }
                    }
                    if (mIsWin){
                        Log.d("wxt","第${historyList[i].longperiod}期：策略中")
                    } else {
                        Log.d("wxt","第${historyList[i].longperiod}期：策略爆")
                    }
                }
            }

        }




//            lotteriesTest.removeLast()
//            lotteriesTest.removeLast()

//            D/wxt: 最近连爆最多次的策略：28
//            D/wxt: 最近连爆最多次的策略:[2, 16, 5, 42, 44, 4, 8]

//            D/wxt: 最近连爆最多次的策略：13
//            D/wxt: 最近连爆最多次的策略:[22, 31, 19, 8, 12, 15, 41]

//            D/wxt: 最近连爆最多次的策略：13
//            D/wxt: 最近连爆最多次的策略:[7, 2, 32, 23, 26, 11, 34]

//            D/wxt: 最近连爆最多次的策略：14
//            D/wxt: 最近连爆最多次的策略:[7, 2, 32, 23, 26, 11, 34]

//            D/wxt: 最近连爆最多次的策略：13
//            D/wxt: 最近连爆最多次的策略:[34, 23, 26, 16, 44, 43, 11]
//            D/wxt: 最近连爆最多次的策略:[6, 13, 3, 11, 9, 38, 26]
//            D/wxt: 最近连爆最多次的策略:[8, 6, 29, 42, 4, 43, 5]
//            D/wxt: 最近连爆最多次的策略:[7, 16, 29, 43, 26, 5, 8]

//            D/wxt: 最近连爆最多次的策略：16
//            D/wxt: 最近连爆最多次的策略:[25, 16, 39, 24, 10, 3, 45]

//        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}