package com.suromo.magic.strategy

import android.util.Log
import com.suromo.magic.db.entity.Lottery
import kotlin.math.max

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/26
 * desc   :
 */
class MissAverageTenStrategy : BaseStrategy(), ILotteryStrategy {
    val strategy1 = mutableListOf<MutableList<MutableList<Recommend>>>()
    val recommendNumMap = mutableMapOf<Int,Int>()
    val countMap = mutableMapOf<Int,MutableList<Int>>()
    val recommend1 = mutableListOf<Int>()
    val recommend2 = mutableListOf<Int>()
    val recommend3 = mutableListOf<Int>()
    val recommend4 = mutableListOf<Int>()
    val recommend5 = mutableListOf<Int>()
    val recommend6 = mutableListOf<Int>()
    val recommend7 = mutableListOf<Int>()
    val recommend8 = mutableListOf<Int>()
    val recommend9 = mutableListOf<Int>()
    val recommend10 = mutableListOf<Int>()

    override fun getStrategyName(): String {
        return "十不中"
    }

    override fun initHistory(lotteries: List<Lottery>) {
        historyList.clear()
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
        }

//        Log.d("wxt","historyList:$historyList")


        recommendList.clear()
        recommendNumMap.clear()
        recommend1.clear()
        recommend2.clear()
        recommend3.clear()
        recommend4.clear()
        recommend5.clear()
        recommend6.clear()
        recommend7.clear()
        recommend8.clear()
        recommend9.clear()
        recommend10.clear()
        countMap.clear()
        for (i in 1 .. 49){
            recommendNumMap[i] = 0
        }

        if (lotteries.isNotEmpty()) {
            for (lottery in lotteries) {
                val longPeriod = lottery.longperiod
                val numbers = lottery.numbers.split(",")

                val resultList = mutableListOf<Int>()

                //特也算进七不中，所以特也需要一起算
                for (num in numbers) {
                    val count = recommendNumMap[num.toInt()]
                    if (count != null) {
                        recommendNumMap[num.toInt()] = count + 1
                    }
                }
            }
//            Log.d("wxt","recommendNumList:${recommendNumMap}")

            recommendNumMap.values.max()
            recommendNumMap.values.min()

            for (value in recommendNumMap.values.min() .. recommendNumMap.values.max()){
                val keyList = mutableListOf<Int>()
                for (key in recommendNumMap.keys){
                    if (recommendNumMap[key] == value){
                        keyList.add(key)
                    }
                }
                countMap[value] = keyList
            }

//            Log.d("wxt","countMap:$countMap")

        }

        val minCount = countMap.keys.min()
        val maxCount = countMap.keys.max()
        val averageCount : Int = (minCount + maxCount) / 2
        val minNumList = countMap[minCount]
        val maxNumList = countMap[maxCount]
        val averageNumList = countMap[averageCount]
        val averageNumListAdd1 = countMap[averageCount+1]
        //策略1
        for (key in minCount .. maxCount){
            val numList = countMap[key]
//            Log.d("wxt","numList:${numList}")
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend1.size < 10){
//                        Log.d("wxt","num:${num}")
                        recommend1.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend1:${recommend1.toSortedSet()}")

        //策略2
        for (key in maxCount downTo minCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend2.size < 10){
                        recommend2.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend2:${recommend2.toSortedSet()}")

        //策略3
        for (key in minCount+1 .. maxCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend3.size < 10){
                        recommend3.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend3:${recommend3.toSortedSet()}")


        //策略4
        for (key in maxCount-1 downTo minCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend4.size < 10){
                        recommend4.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend4:${recommend4.toSortedSet()}")


        //策略5
        for (key in minCount+3 .. maxCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend5.size < 10){
                        recommend5.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend5:${recommend5.toSortedSet()}")


        //策略6
        for (key in maxCount-3 downTo minCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend6.size < 10){
                        recommend6.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend6:${recommend6.toSortedSet()}")


        //策略7
        if (!averageNumList.isNullOrEmpty()){
            for (num in averageNumList){
                if (recommend7.size < 10){
                    recommend7.add(num)
                }
            }
        }
        for (key in averageCount-2 .. maxCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend7.size < 10){
                        recommend7.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend7:${recommend7.toSortedSet()}")


        //策略8
        if (!averageNumList.isNullOrEmpty()){
            for (num in averageNumList){
                if (recommend8.size < 10){
                    recommend8.add(num)
                }
            }
        }
        for (key in averageCount+1 .. maxCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend8.size < 10){
                        recommend8.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend8:${recommend8.toSortedSet()}")


        //策略9
        if (!minNumList.isNullOrEmpty()){
            for (num in minNumList){
                if (recommend9.size < 10){
                    recommend9.add(num)
                }
            }
        }
        if (!maxNumList.isNullOrEmpty()){
            for (num in maxNumList){
                if (recommend9.size < 10){
                    recommend9.add(num)
                }
            }
        }
        if (!averageNumListAdd1.isNullOrEmpty()){
            for (num in averageNumListAdd1){
                if (recommend9.size < 10){
                    recommend9.add(num)
                }
            }
        }
        for (key in minCount+2 .. maxCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend9.size < 10){
                        recommend9.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend9:${recommend9.toSortedSet()}")

        //策略10
        if (!minNumList.isNullOrEmpty()){
            for (num in minNumList){
                if (recommend10.size < 10){
                    recommend10.add(num)
                }
            }
        }
        if (!maxNumList.isNullOrEmpty()){
            for (num in maxNumList){
                if (recommend10.size < 10){
                    recommend10.add(num)
                }
            }
        }
        if (!averageNumList.isNullOrEmpty()){
            for (num in averageNumList){
                if (recommend10.size < 10){
                    recommend10.add(num)
                }
            }
        }
        for (key in maxCount-2 .. maxCount){
            val numList = countMap[key]
            if (!numList.isNullOrEmpty()){
                for (num in numList){
                    if (recommend10.size < 10){
                        recommend10.add(num)
                    }
                }
            }
        }
//        Log.d("wxt","recommend10:${recommend10.toSortedSet()}")


        val recommendResult1 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend1.toMutableSet()
        )
        val recommendResult2 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend2.toMutableSet()
        )
        val recommendResult3 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend3.toMutableSet()
        )
        val recommendResult4 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend4.toMutableSet()
        )
        val recommendResult5 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend5.toMutableSet()
        )
        val recommendResult6 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend6.toMutableSet()
        )
        val recommendResult7 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend7.toMutableSet()
        )
        val recommendResult8 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend8.toMutableSet()
        )
        val recommendResult9 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend9.toMutableSet()
        )
        val recommendResult10 = Recommend(
            longperiod = historyList.last().longPeriod + 1,
            numbers = recommend10.toMutableSet()
        )

        val recommendList = mutableListOf<Recommend>()
//        recommendList.add(recommendResult1)
//        recommendList.add(recommendResult2)
//        recommendList.add(recommendResult3)
//        recommendList.add(recommendResult4)
//        recommendList.add(recommendResult5)
//        recommendList.add(recommendResult6)
//        recommendList.add(recommendResult7)
//        recommendList.add(recommendResult8)
//        recommendList.add(recommendResult9)
        recommendList.add(recommendResult10)
        val strategyList = mutableListOf<MutableList<Recommend>>()
        strategyList.add(recommendList)
        strategy1.add(strategyList)
//        Log.d("wxt","strategy1:${strategy1}")
    }
    private var addNum = 0
    public fun initAddNum(num: Int){
        addNum = num
    }

    fun initRecommend(lotteries: List<Lottery>) {


    }
    private val allCount = mutableListOf<Int>()
    private val missCount = mutableListOf<Int>()
    override fun generateStrategy() {
        recommendLotteries.clear()
        allCount.clear()
        missCount.clear()

//        historyList.reverse()
//
//
//
//        for (recommend in recommendList){
//
////            Log.d("wxt","第${recommend.longPeriod}期:${recommend.numbers}")
//            var winCount = 0
//            var loseCount = 0
//            val count = mutableListOf<String>()
//            for (history in historyList) {
////                Log.d("wxt","第${history.longPeriod}期:${history.numbers}")
//                var mIsWin = true
//                for (num in recommend.numbers){
//                    if (history.numbers.contains(num)){
////                        Log.d("wxt","爆的号码：$num")
//                        mIsWin = false
//                    }
//                }
//                if (mIsWin){
//                    winCount++
//                    count.add("中")
//                }else{
//                    loseCount++
//                    count.add("爆")
//                }
//            }
////            Log.d("wxt","${recommend.longPeriod}期：中$winCount 次，爆$loseCount 次")
////            Log.d("wxt","号码：${recommend.numbers}")
////            Log.d("wxt","详情：$count")
////            Log.d("wxt","策略最近爆的次数：${count.indexOf("中")}")
//            missCount.add(count.indexOf("中"))
//            allCount.add(count.count { it == "爆" })
//
//            var maxContinueCount = 0
//            var itemCount = 0
//            for (item in count){
//                if (item == "爆"){
//                    itemCount++
//                } else {
//                    if (maxContinueCount < itemCount){
//                        maxContinueCount = itemCount
//                    }
//                    itemCount = 0
//                }
//            }
//
//
//            val recommendLottery = RecommendLottery(
//                id = recommendList.indexOf(recommend)+1,
//                numbers = recommend.numbers,
//                allMissCount = loseCount,
//                recentlyMissCount = count.indexOf("中"),
//                continueMissCount = maxContinueCount
//            )
//
//            recommendLotteries.add(recommendLottery)
//
//        }
//
////        for (recommendLottery in recommendLotteries){
//////            Log.d("wxt","策略id：${recommendLottery.id}")
//////            Log.d("wxt","策略号码：${recommendLottery.numbers}")
//////            Log.d("wxt","策略共爆次数：${recommendLottery.allMissCount}")
//////            Log.d("wxt","策略最近爆次数：${recommendLottery.recentlyMissCount}")
//////            Log.d("wxt","策略最多连爆次数：${recommendLottery.continueMissCount}")
////            Log.d("wxt",recommendLottery.toString())
////        }
//
////        Log.d("wxt","爆最少次的策略：" + recommendLotteries.minOf { it.allMissCount })
////        Log.d("wxt","爆最多次的策略：" + recommendLotteries.maxOf { it.allMissCount })
////        Log.d("wxt","连爆最多次的策略：" + recommendLotteries.maxOf { it.continueMissCount })
////        Log.d("wxt","最近连爆最多次的策略：" + recommendLotteries.maxOf { it.recentlyMissCount })
//
//        val averageCount = (recommendLotteries.minOf { it.allMissCount } + recommendLotteries.maxOf { it.allMissCount })/2
////        Log.d("wxt", "爆次数为平均值的策略：$averageCount")
//
//        val numMap = mutableMapOf<Int,Int>()
//        for (recommendLottery in recommendLotteries){
////            if (recommendLottery.allMissCount == recommendLotteries.minOf { it.allMissCount }){
////                Log.d("wxt","爆最少次的策略:"+recommendLottery.numbers)
////            }
////            if (recommendLottery.allMissCount == recommendLotteries.maxOf { it.allMissCount }){
////                Log.d("wxt","爆最多次的策略:"+recommendLottery.numbers)
////            }
////            if (recommendLottery.continueMissCount == recommendLotteries.maxOf { it.continueMissCount }){
////                Log.d("wxt","连爆最多次的策略:"+recommendLottery.numbers)
////            }
////            if (recommendLottery.recentlyMissCount == recommendLotteries.maxOf { it.recentlyMissCount }){
////                Log.d("wxt","最近连爆最多次的策略:"+recommendLottery.numbers)
////            }
//            if (recommendLottery.allMissCount == averageCount){
//                for (num in recommendLottery.numbers){
//                    if (numMap[num] == null){
//                        numMap[num] = 1
//                    } else {
//                        val count = numMap[num]
//                        if (count != null) {
//                            numMap[num] = count + 1
//                        }
//                    }
//
//                }
////                Log.d("wxt","爆次数为平均值的策略:"+recommendLottery.numbers)
//            }
//        }
////        Log.d("wxt","所有号码:${numMap.toSortedMap()}")
//
//        val averageValue = (numMap.values.max() + numMap.values.min())/2 + addNum
//
//        val numbers =  mutableSetOf<Int>()
//
//        for (num in numMap){
//            if (num.value == numMap.values.max()){
//                if (numbers.count() < 7){
//                    numbers.add(num.key)
//                }
////                Log.d("wxt","出现最多次数的数：${num.key},共出现：${num.value}")
//            }
////            if (num.value == numMap.values.max()-1){
////                if (numbers.count() < 7){
////                    numbers.add(num.key)
////                }
//////                Log.d("wxt","出现最多次数的数：${num.key},共出现：${num.value}")
////            }
//            if (num.value == numMap.values.min()){
//                if (numbers.count() < 7){
//                    numbers.add(num.key)
//                }
////                Log.d("wxt","出现最少次数的数：${num.key},共出现：${num.value}")
//            }
////            if (num.value == numMap.values.min()+1){
////                if (numbers.count() < 7){
////                    numbers.add(num.key)
////                }
//////                Log.d("wxt","出现最少次数的数：${num.key},共出现：${num.value}")
////            }
//            if (num.value == averageValue){
//                if (numbers.count() < 7){
//                    numbers.add(num.key)
//                }
////                Log.d("wxt","出现平均次数的数：${num.key},共出现：${num.value}")
//            }
//        }
//        if (numbers.count() < 7 && !historyList.first().numbers.contains(1)){
//            numbers.add(49)
//        }
//        if (numbers.count() < 7 && !historyList.first().numbers.contains(49)){
//            numbers.add(1)
//        }
//        if (numbers.count() < 7){
//            var specialNum = historyList.first().numbers.last()
//            while (specialNum > 10){
//                specialNum -= 10
//            }
//            if (specialNum!=0){
//                numbers.add(specialNum)
//            }
//        }
//        if (numbers.count() < 7){
//            numbers.add(6)
//        }
//        if (numbers.count() < 7){
//            numbers.add(12)
//        }
//        if (numbers.count() < 7){
//            numbers.add(20)
//        }
//        if (numbers.count() < 7){
//            numbers.add(27)
//        }
//
//
//        val recommend = Recommend(
//            longperiod = historyList.first().longPeriod + 1,
//            numbers = numbers
//        )
//
////        Log.d("wxt","推荐号码：$recommend")
//        strategy1.add(recommend)
//
////        strategy1.add(numbers)

    }

    override fun runStrategy() {
        TODO("Not yet implemented")
    }

    override fun getMissNum(): Int {
        TODO("Not yet implemented")
    }

//    fun getNextRecommend(): Recommend {
//        return recommend
//    }
}