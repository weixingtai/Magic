//package com.suromo.magic.strategy
//
//import android.util.Log
//import com.suromo.magic.db.entity.Lottery
//import com.suromo.magic.log.MLog
//
///**
// * author : Samuel
// * e-mail : xingtai.wei@icloud.com
// * time   : 2023/3/10
// * desc   :
// */
//class LuckyMissFiveNumStrategy : BaseStrategy(), ILotteryStrategy {
//
//    private val recommendNumList = mutableSetOf<Int>()
//
//    override fun getStrategyName(): String {
//        return "五不中"
//    }
//
//    fun calculateHistory(lotteries: List<Lottery>) {
//        initHistory(lotteries)
//        var winCount = 0
//        var loseCount = 0
//        for (history in historyList){
//            var mIsWin = true
//            for (num in recommendNumList){
//                if (history.numbers.contains(num)){
//                    mIsWin = false
//                }
//            }
//            if (mIsWin){
//                winCount++
//                Log.d("wxt","第${history.longPeriod}期：策略中")
//            } else {
//                loseCount++
//                Log.d("wxt","第${history.longPeriod}期：策略不中")
//            }
//        }
//        Log.d("wxt","策略一共中：$winCount 次")
//        Log.d("wxt","策略一共不中：$loseCount 次")
//    }
//
//    fun calculateHistory() {
//        TODO("Not yet implemented")
//    }
//
//    override fun initHistory(lotteries: List<Lottery>) {
//        if (lotteries.isNotEmpty()) {
//            for (lottery in lotteries) {
//                val longPeriod = lottery.longperiod
//                val numbers = lottery.numbers.split(",")
//                val smallBallNumber = lottery.numbers.split(",").take(6)
//
//                val resultList = mutableListOf<Int>()
//                val smallBallResultList = mutableListOf<Int>()
//
//                //特也算进七不中，所以特也需要一起算
//                for (num in numbers) {
//                    resultList.add(num.toInt())
//                }
//
//                for (num in smallBallNumber) {
//                    smallBallResultList.add(num.toInt())
//                }
//
//                val openResult = OpenResult(longPeriod,resultList)
//                val smallBallOpenResult = OpenResult(longPeriod,smallBallResultList)
//
//                historyList.add(openResult)
//                smallBallHistoryList.add(smallBallOpenResult)
//            }
//
//            generateStrategy()
//        }
//    }
//
//    override fun generateStrategy() {
//        recommendNumList.add(8)
//        recommendNumList.add(6)
//        recommendNumList.add(12)
//        recommendNumList.add(20)
//        recommendNumList.add(27)
//        val num = recommendNumList.joinToString(",")
//        recommend = Recommend(
//            longperiod = historyList[historyList.size-1].longPeriod + 1,
//            num = num
//        )
//    }
//
//    override fun runStrategy() {
//        TODO("Not yet implemented")
//    }
//
//    override fun getMissNum(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun getNextRecommend(): Recommend {
//        return recommend
//    }
//}