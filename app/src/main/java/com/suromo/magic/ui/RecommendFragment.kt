package com.suromo.magic.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.suromo.magic.databinding.FragmentRecommendBinding
import com.suromo.magic.strategy.*
import com.suromo.magic.vm.RecommendViewModel

class RecommendFragment : Fragment() {

    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val recommendViewModel : RecommendViewModel by activityViewModels()

        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val strategy = LuckyMissSevenNumStrategy()

        recommendViewModel.lotteries.observe(viewLifecycleOwner) {
            val lotteries = it
            strategy.initHistory(lotteries)
//            val strategy1 = LuckyMissSevenNumStrategy()
//            strategy1.calculateHistory(lotteries)

//            val strategy2 = LuckyMissTenNumStrategy()
//            strategy2.calculateHistory(lotteries)

//            val strategy3 = LuckyMissFiveNumStrategy()
//            strategy3.calculateHistory(lotteries)




//            binding.strategyTitle.text = "第${strategy.getNextRecommend().longperiod}期心水推荐："
//            binding.strategy1.text = "策略一(取上期开奖结果)选号：${strategy.strategy1}"
//            binding.strategy2.text = "策略二(上期开出的幸运号码)选号：${strategy.strategy2}"
//            binding.strategy3.text = "策略三(小码连续来两期)选号：${strategy.strategy3}"
//            binding.strategy4.text = "策略四(上期跳号)选号：${strategy.strategy4}"
//            binding.strategy5.text = "策略五(上期与上上期跳号)选号：${strategy.strategy5}"
//            binding.strategy6.text = "策略六(上期特尾数乘二)选号：${strategy.strategy6}"
//            binding.strategy7.text = "策略七(上期最小两个号码相乘)选号：${strategy.strategy7}"
//            binding.strategy8.text = "策略八(上期最小两个号码相加)选号：${strategy.strategy8}"
//            binding.strategy9.text = "策略九(上期开1，49，这期买49，1)选号：${strategy.strategy9}"
//            binding.strategyAll.text = "所有策略选号推荐：${strategy.generateNumList}"
//            binding.strategyRecommend.text = "本期七不中推荐：${strategy.getNextRecommend()}"

//        }

//        recommendViewModel.history.observe(viewLifecycleOwner) {
//            val history = it
//            binding.strategyRecommend.text = "本期七不中推荐：${history.numbers},下注金额：${history.bet}"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}