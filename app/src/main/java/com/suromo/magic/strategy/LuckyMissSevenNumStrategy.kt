package com.suromo.magic.strategy

import android.util.Log
import com.suromo.magic.db.entity.Lottery

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/26
 * desc   :
 */
class LuckyMissSevenNumStrategy : BaseStrategy(), ILotteryStrategy {
    val strategy1 = mutableListOf<Recommend>()
    val strategy2 = mutableListOf<Recommend>()
    val strategy3 = mutableListOf<Int>()
    private val recommendNumList = mutableSetOf<Int>()

    override fun getStrategyName(): String {
        return "七不中"
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
        generateStrategy()
    }
    private var addNum = 0
    public fun initAddNum(num: Int){
        addNum = num
    }

    fun initRecommend(lotteries: List<Lottery>) {
        recommendList.clear()
        if (lotteries.isNotEmpty()) {
            for (lottery in lotteries) {
                val longPeriod = lottery.longperiod
                val numbers = lottery.numbers.split(",")

                val resultList = mutableListOf<Int>()

                //特也算进七不中，所以特也需要一起算
                resultList.add(5)
                resultList.add(6)
                resultList.add(12)
                resultList.add(20)
                resultList.add(25)
                resultList.add(27)
                resultList.add(32)

                val openResult = OpenResult(longPeriod,resultList)

                recommendList.add(openResult)
            }


        }
    }
    private val allCount = mutableListOf<Int>()
    private val missCount = mutableListOf<Int>()
    override fun generateStrategy() {
        recommendLotteries.clear()
        allCount.clear()
        missCount.clear()

        historyList.reverse()



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
//            Log.d("wxt","号码：${recommend.numbers}")
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

//        Log.d("wxt","爆最少次的策略：" + recommendLotteries.minOf { it.allMissCount })
//        Log.d("wxt","爆最多次的策略：" + recommendLotteries.maxOf { it.allMissCount })
//        Log.d("wxt","连爆最多次的策略：" + recommendLotteries.maxOf { it.continueMissCount })
//        Log.d("wxt","最近连爆最多次的策略：" + recommendLotteries.maxOf { it.recentlyMissCount })

        val averageCount = (recommendLotteries.minOf { it.allMissCount } + recommendLotteries.maxOf { it.allMissCount })/2
//        Log.d("wxt", "爆次数为平均值的策略：$averageCount")

        val numMap = mutableMapOf<Int,Int>()
        for (recommendLottery in recommendLotteries){
//            if (recommendLottery.allMissCount == recommendLotteries.minOf { it.allMissCount }){
//                Log.d("wxt","爆最少次的策略:"+recommendLottery.numbers)
//            }
//            if (recommendLottery.allMissCount == recommendLotteries.maxOf { it.allMissCount }){
//                Log.d("wxt","爆最多次的策略:"+recommendLottery.numbers)
//            }
//            if (recommendLottery.continueMissCount == recommendLotteries.maxOf { it.continueMissCount }){
//                Log.d("wxt","连爆最多次的策略:"+recommendLottery.numbers)
//            }
//            if (recommendLottery.recentlyMissCount == recommendLotteries.maxOf { it.recentlyMissCount }){
//                Log.d("wxt","最近连爆最多次的策略:"+recommendLottery.numbers)
//            }
            if (recommendLottery.allMissCount == averageCount){
                for (num in recommendLottery.numbers){
                    if (numMap[num] == null){
                        numMap[num] = 1
                    } else {
                        val count = numMap[num]
                        if (count != null) {
                            numMap[num] = count + 1
                        }
                    }

                }
//                Log.d("wxt","爆次数为平均值的策略:"+recommendLottery.numbers)
            }
        }
//        Log.d("wxt","所有号码:${numMap.toSortedMap()}")

        val averageValue = (numMap.values.max() + numMap.values.min())/2

        val numbers =  mutableSetOf<Int>()

        for (num in numMap){
            if (num.value == numMap.values.max()){
                if (numbers.count() < 7){
                    numbers.add(num.key)
                }
//                Log.d("wxt","出现最多次数的数：${num.key},共出现：${num.value}")
            }
//            if (num.value == numMap.values.max()-1){
//                if (numbers.count() < 7){
//                    numbers.add(num.key)
//                }
////                Log.d("wxt","出现最多次数的数：${num.key},共出现：${num.value}")
//            }
            if (num.value == numMap.values.min()){
                if (numbers.count() < 7){
                    numbers.add(num.key)
                }
//                Log.d("wxt","出现最少次数的数：${num.key},共出现：${num.value}")
            }
//            if (num.value == numMap.values.min()+1){
//                if (numbers.count() < 7){
//                    numbers.add(num.key)
//                }
////                Log.d("wxt","出现最少次数的数：${num.key},共出现：${num.value}")
//            }
            if (num.value == averageValue){
                if (numbers.count() < 7){
                    numbers.add(num.key)
                }
//                Log.d("wxt","出现平均次数的数：${num.key},共出现：${num.value}")
            }
        }
        if (numbers.count() < 7 && !historyList.first().numbers.contains(1)){
            numbers.add(49)
        }
        if (numbers.count() < 7 && !historyList.first().numbers.contains(49)){
            numbers.add(1)
        }
        if (numbers.count() < 7){
            var specialNum = historyList.first().numbers.last()
            while (specialNum > 10){
                specialNum -= 10
            }
            if (specialNum!=0){
                numbers.add(specialNum)
            }
        }
        if (numbers.count() < 7){
            numbers.add(6)
        }
        if (numbers.count() < 7){
            numbers.add(12)
        }
        if (numbers.count() < 7){
            numbers.add(20)
        }
        if (numbers.count() < 7){
            numbers.add(27)
        }


        val recommend = Recommend(
            longperiod = historyList.first().longPeriod + 1,
            numbers = numbers
        )

//        Log.d("wxt","推荐号码：$recommend")
        strategy1.add(recommend)

//        strategy1.add(numbers)

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