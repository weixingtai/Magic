//package com.suromo.magic.strategy
//
//import com.suromo.magic.db.entity.Lottery
//
///**
// * author : Samuel
// * e-mail : xingtai.wei@icloud.com
// * time   : 2023/2/26
// * desc   :
// */
//class AllEvenStrategy : BaseStrategy(),ILotteryStrategy {
//
//
//    override fun getStrategyName(): String {
//        return "一直双"
//    }
//
//    override fun initHistory(lotteries: List<Lottery>) {
//        if (lotteries.isNotEmpty()) {
//            for (lottery in lotteries) {
//                val longPeriod = lottery.longperiod
//                val numbers = lottery.numbers.split(",")
//
//                val specialNumber = numbers.last().toInt()
//
//                val resultList = mutableListOf<Int>()
//                resultList.add(specialNumber)
//
//                val openResult = OpenResult(longPeriod,resultList)
//
//                historyList.add(openResult)
//            }
//
//            generateStrategy()
//        }
//    }
//
//    override fun generateStrategy() {
//
//        for (history in historyList){
//            val resultList = mutableListOf<Int>()
//            resultList.add(2)
//            val strategy = OpenResult(history.longPeriod,resultList)
//            strategyList.add(strategy)
//        }
//
//        runStrategy()
//
//    }
//
//    override fun runStrategy() {
//
//        for (i in 0 until historyList.size) {
//
//            val strategy = strategyList[i]
//            val history = historyList[i]
//
//            if ((strategy.numbers[0] % 2) == (history.numbers[0] % 2)) {
//                hitList.add(1)
//            } else {
//                hitList.add(0)
//            }
//
////            Log.d("wxt","第${history.longPeriod}期，" +
////                    "策略预测为：${if (strategy.numbers[0]%2==0) "双" else "单"}," +
////                    "开奖结果为：${if (history.numbers[0]%2==0) "双" else "单"}," +
////                    if ((strategy.numbers[0] % 2) == (history.numbers[0] % 2)) "策略开" else "策略爆"
////            )
//
//        }
//    }
//
//    override fun getMissNum(): Int {
//        val missNum = hitList.size -  hitList.lastIndexOf(1) -1
////        Log.d("wxt","最近爆的期数：$missNum")
//        return missNum
//    }
//
//
//    override fun getNextRecommend(): Recommend {
//        return Recommend(2028082,"双")
//    }
//}