package com.suromo.magic.strategy

import com.suromo.magic.db.entity.Lottery

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/26
 * desc   :
 */
class RecommendStrategy {


    fun initStrategy(lotteries: List<Lottery>){
//        val strategyAllOdd : ILotteryStrategy = AllOddStrategy()
//        strategyAllOdd.initHistory(lotteries)
//
//        val allOddMissNum = strategyAllOdd.getMissNum()
//
//        Log.d("wxt","策略：${strategyAllOdd.getStrategyName()},已爆：${strategyAllOdd.getMissNum()}期")
//
//        val strategyAllEven : ILotteryStrategy = AllEvenStrategy()
//        strategyAllEven.initHistory(lotteries)
//
//        val allEvenMissNum = strategyAllEven.getMissNum()
//
//        Log.d("wxt","策略：${strategyAllEven.getStrategyName()},已爆：${strategyAllEven.getMissNum()}期")


        val strategy : ILotteryStrategy = MissSevenStrategy()
        strategy.initHistory(lotteries)


    }

}