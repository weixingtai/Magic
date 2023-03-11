package com.suromo.magic.strategy

import android.util.Log
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.log.MLog
import java.util.Calendar
import kotlin.contracts.contract

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/3/10
 * desc   :
 */
class LuckyMissSevenNumStrategy : BaseStrategy(), ILotteryStrategy {

    private val recommendNumList = mutableSetOf<Int>()

    override fun getStrategyName(): String {
        return "七不中"
    }



    fun calculateHistory(lotteries: List<Lottery>) {
        initHistory(lotteries)
        var winCount = 0
        var loseCount = 0
        for (history in historyList){
            var mIsWin = true
            for (num in recommendNumList){
                if (history.numbers.contains(num)){
                    mIsWin = false
                }
            }
            if (mIsWin){
                winCount++
                Log.d("wxt","第${history.longPeriod}期：策略中")
            } else {
                loseCount++
                Log.d("wxt","第${history.longPeriod}期：策略不中")
            }
        }
        Log.d("wxt","策略一共中：$winCount 次")
        Log.d("wxt","策略一共不中：$loseCount 次")
    }

    fun calculateHistory() {
        val allCount = mutableListOf<Int>()
        val missCount = mutableListOf<Int>()
        historyList.reversed()

        val recommendLotteries = mutableListOf<RecommendLottery>()

        for (recommend in recommendList){

//            Log.d("wxt","第${recommend.longPeriod}期:${recommend.numbers}")
            var winCount = 0
            var loseCount = 0
            val count = mutableListOf<String>()
            for (history in historyList) {
//                Log.d("wxt","第${history.longPeriod}期:${history.numbers}")
                var mIsWin = true
                for (num in recommend.numbers){
                    if (history.numbers.contains(num)){
//                        Log.d("wxt","爆的号码：$num")
                        mIsWin = false
                    }
                }
                if (mIsWin){
                    winCount++
                    count.add("中")
                }else{
                    loseCount++
                    count.add("爆")
                }
            }
//            Log.d("wxt","${recommend.longPeriod}期：中$winCount 次，爆$loseCount 次")
//            Log.d("wxt","详情：$count")
//            Log.d("wxt","策略最近爆的次数：${count.indexOf("中")}")
            missCount.add(count.indexOf("中"))
            allCount.add(count.count { it == "爆" })

            var maxContinueCount = 0
            var itemCount = 0
            for (item in count){
                if (item == "爆"){
                    itemCount++
                } else {
                    if (maxContinueCount < itemCount){
                        maxContinueCount = itemCount
                    }
                    itemCount = 0
                }
            }


            val recommendLottery = RecommendLottery(
                id = recommendList.indexOf(recommend)+1,
                numbers = recommend.numbers,
                allMissCount = loseCount,
                recentlyMissCount = count.indexOf("中"),
                continueMissCount = maxContinueCount
            )

            recommendLotteries.add(recommendLottery)

        }

//        for (recommendLottery in recommendLotteries){
////            Log.d("wxt","策略id：${recommendLottery.id}")
////            Log.d("wxt","策略号码：${recommendLottery.numbers}")
////            Log.d("wxt","策略共爆次数：${recommendLottery.allMissCount}")
////            Log.d("wxt","策略最近爆次数：${recommendLottery.recentlyMissCount}")
////            Log.d("wxt","策略最多连爆次数：${recommendLottery.continueMissCount}")
//            Log.d("wxt",recommendLottery.toString())
//        }

        Log.d("wxt","爆最少次的策略：" + recommendLotteries.minOf { it.allMissCount })
        Log.d("wxt","爆最多次的策略：" + recommendLotteries.maxOf { it.allMissCount })
        Log.d("wxt","连爆最多次的策略：" + recommendLotteries.maxOf { it.continueMissCount })
        Log.d("wxt","最近连爆最多次的策略：" + recommendLotteries.maxOf { it.recentlyMissCount })
        for (recommendLottery in recommendLotteries){
            if (recommendLottery.allMissCount == recommendLotteries.minOf { it.allMissCount }){
                Log.d("wxt","爆最少次的策略:"+recommendLottery.numbers)
            }
            if (recommendLottery.allMissCount == recommendLotteries.maxOf { it.allMissCount }){
                Log.d("wxt","爆最多次的策略:"+recommendLottery.numbers)
            }
            if (recommendLottery.continueMissCount == recommendLotteries.maxOf { it.continueMissCount }){
                Log.d("wxt","连爆最多次的策略:"+recommendLottery.numbers)
            }
            if (recommendLottery.recentlyMissCount == recommendLotteries.maxOf { it.recentlyMissCount }){
                Log.d("wxt","最近连爆最多次的策略:"+recommendLottery.numbers)
            }
        }


//        [22, 15, 1, 14, 7, 17, 4]
//        [34, 23, 26, 16, 44, 43, 11]
//        [25, 16, 39, 24, 10, 3, 45]
//        [12, 46, 9, 36, 49, 27, 6]

//        //策略：最近爆最多期的，并且爆的期数最多的，并且连爆次数最多的
//        //排除掉概率大于赔率的策略
//        val hitCount = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) / 3 * 2 - 4
//        Log.d("wxt","概率：$hitCount")
//        val hitLotteries = mutableListOf<RecommendLottery>()
//        val numbers = mutableSetOf<Int>()
//        val missNumbers = mutableSetOf<Int>()
//        for (recommendLottery in recommendLotteries){
//            if (recommendLottery.allMissCount < hitCount){
//                hitLotteries.add(recommendLottery)
//                for (num in recommendLottery.numbers){
//                    numbers.add(num)
//                }
//            }
//        }
////        Log.d("wxt",hitLotteries.toString())
////        Log.d("wxt",numbers.toString())
//        for (i in 1..49){
//            missNumbers.add(i)
//        }
//        for (num in numbers){
//            if (missNumbers.contains(num)){
//                missNumbers.remove(num)
//            }
//        }
//        //反推出可以买的号码
//        Log.d("wxt",missNumbers.toString())
//
//        //正推算出可以买的号码
//        val missCounts = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) / 3 * 2 + 7
//        Log.d("wxt","概率：$hitCount")
//        val missLotteries = mutableListOf<RecommendLottery>()
//        val hitNums = mutableSetOf<Int>()
//        val missNums = mutableSetOf<Int>()
//        for (recommendLottery in recommendLotteries){
//            if (recommendLottery.allMissCount > missCounts){
//                missLotteries.add(recommendLottery)
//                for (num in recommendLottery.numbers){
//                    hitNums.add(num)
//                }
//            }
//        }
////        Log.d("wxt",hitLotteries.toString())
////        Log.d("wxt",numbers.toString())
//        for (i in 1..49){
//            missNums.add(i)
//        }
//        for (num in hitNums){
//            if (missNums.contains(num)){
//                missNums.remove(num)
//            }
//        }
//        //反推出可以买的号码
//        Log.d("wxt",missNums.toString())



//
//        for (history in historyList){
//            var mIsWin = true
//            var winCount = 0
//            var loseCount = 0
//            for (recommend in recommendList){
//                for (num in recommend.numbers){
//                    if (history.numbers.contains(num)){
//                        mIsWin = false
//                    }
//                }
//            }
//            if (mIsWin){
//                winCount++
//                recommendMissCount.add(winCount)
//                Log.d("wxt","第${history.longPeriod}期：策略中")
//            } else {
//                loseCount++
//                recommendHitCount.add(loseCount)
//                Log.d("wxt","第${history.longPeriod}期：策略不中")
//            }
//            Log.d("wxt","策略一共中：$winCount 次")
//            Log.d("wxt","策略一共不中：$loseCount 次")
//
//        }






    }

    override fun initHistory(lotteries: List<Lottery>) {
        if (lotteries.isNotEmpty()) {
            for (lottery in lotteries) {
                val longPeriod = lottery.longperiod
                val numbers = lottery.numbers.split(",")
                val smallBallNumber = lottery.numbers.split(",").take(6)

                val resultList = mutableListOf<Int>()
                val smallBallResultList = mutableListOf<Int>()

                //特也算进七不中，所以特也需要一起算
                for (num in numbers) {
                    resultList.add(num.toInt())
                }

                for (num in smallBallNumber) {
                    smallBallResultList.add(num.toInt())
                }

                val openResult = OpenResult(longPeriod,resultList)
                val smallBallOpenResult = OpenResult(longPeriod,smallBallResultList)

                historyList.add(openResult)
                smallBallHistoryList.add(smallBallOpenResult)
            }

            generateStrategy()
        }
    }

    fun initRecommend(lotteries: List<Lottery>) {
        if (lotteries.isNotEmpty()) {
            for (lottery in lotteries) {
                val longPeriod = lottery.longperiod
                val numbers = lottery.numbers.split(",")

                val resultList = mutableListOf<Int>()

                //特也算进七不中，所以特也需要一起算
                for (num in numbers) {
                    resultList.add(num.toInt())
                }

                val openResult = OpenResult(longPeriod,resultList)

                recommendList.add(openResult)
            }

            generateStrategy()
        }
    }

    override fun generateStrategy() {


    }

    override fun runStrategy() {
        TODO("Not yet implemented")
    }

    override fun getMissNum(): Int {
        TODO("Not yet implemented")
    }

    override fun getNextRecommend(): Recommend {
        return recommend
    }
}