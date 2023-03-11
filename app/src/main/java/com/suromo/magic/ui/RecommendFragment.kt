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
                var lotteriesCopy = mutableListOf<Lottery>()
                lotteriesCopy.addAll(lotteries)
                lotteriesCopy.reverse()
                val lotteriesTest = mutableListOf<Lottery>()

                for (lottery in lotteriesCopy){
                    lotteriesTest.add(lottery)
                    if (lottery.longperiod in 2023031..2023069){
                        Log.d("wxt","lotteriesTest:${lotteriesTest.size}")
                        strategy.initHistory(lotteriesTest)
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