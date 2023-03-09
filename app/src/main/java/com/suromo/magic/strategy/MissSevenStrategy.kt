package com.suromo.magic.strategy

import android.util.Log
import com.orhanobut.hawk.Hawk
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.log.MLog
import com.suromo.magic.util.getNetLongPeriod

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/26
 * desc   :
 */
class MissSevenStrategy : BaseStrategy(), ILotteryStrategy {

    val generateNumList = mutableSetOf<Int>()
    private val recommendNumList = mutableSetOf<Int>()
    val strategy1 = mutableListOf<Int>()
    val strategy2 = mutableListOf<Int>()
    val strategy3 = mutableListOf<Int>()
    val strategy4 = mutableListOf<Int>()
    val strategy5 = mutableListOf<Int>()
    val strategy6 = mutableListOf<Int>()
    val strategy7 = mutableListOf<Int>()
    val strategy8 = mutableListOf<Int>()
    val strategy9 = mutableListOf<Int>()


    override fun getStrategyName(): String {
        return "七不中"
    }

//    override fun calculateHistory(lotteries: List<Lottery>){
//        var winCount = 0
//        var loseCount = 0
//
//        var count1 = 0
//        var count2 = 0
//        var count3 = 0
//        var count4 = 0
//        var count5 = 0
//        var count6 = 0
//        var count7 = 0
//        var count8 = 0
//        var count9 = 0
//        if (lotteries.isNotEmpty()){
//            MLog.d("lotteries.isNotEmpty():"+lotteries.size)
//            val tempLotteries : MutableList<Lottery> = mutableListOf()
//            for (lottery in lotteries) {
//                MLog.d(lottery)
//                MLog.d("tempLotteries:"+tempLotteries.size)
//                if (tempLotteries.size < 5){
//                    tempLotteries.add(lottery)
//                    MLog.d("tempLotteries add:"+tempLotteries.size)
//                    if (tempLotteries.size == 5){
//                        MLog.d("lotteries.size == 5")
//                        initHistory(tempLotteries)
//                        MLog.d("getNextRecommend:" + getNextRecommend())
//                        if (lotteries.size - 1 > lotteries.indexOf(lottery)) {
//                            MLog.d("getNextOpen:" + lotteries[(lotteries.indexOf(lottery) + 1)].numbers)
//                            var mIsWin = true
//                            MLog.d("lotteries[(lotteries.indexOf(lottery)):"+lotteries[(lotteries.indexOf(lottery)+1)])
//                            for (num in getNextRecommend().num.split(",")){
//                                if (lotteries[(lotteries.indexOf(lottery)+1)].numbers.split(",").contains(num)){
//                                    mIsWin = false
//                                    if (strategy1.contains(num.toInt())){
//                                        MLog.d("策略1爆:num:$num")
//                                        count1++
//                                    }
//                                    if (strategy2.contains(num.toInt())){
//                                        MLog.d("策略2爆:num:$num")
//                                        count2++
//                                    }
//                                    if (strategy3.contains(num.toInt())){
//                                        MLog.d("策略3爆:num:$num")
//                                        count3++
//                                    }
//                                    if (strategy4.contains(num.toInt())){
//                                        MLog.d("策略4爆:num:$num")
//                                        count4++
//                                    }
//                                    if (strategy5.contains(num.toInt())){
//                                        MLog.d("策略5爆:num:$num")
//                                        count5++
//                                    }
//                                    if (strategy6.contains(num.toInt())){
//                                        MLog.d("策略6爆:num:$num")
//                                        count6++
//                                    }
//                                    if (strategy7.contains(num.toInt())){
//                                        MLog.d("策略7爆:num:$num")
//                                        count7++
//                                    }
//                                    if (strategy8.contains(num.toInt())){
//                                        MLog.d("策略8爆:num:$num")
//                                        count8++
//                                    }
//                                    if (strategy9.contains(num.toInt())){
//                                        MLog.d("策略9爆:num:$num")
//                                        count9++
//                                    }
//
//                                }
//                            }
//                            if (mIsWin) {
//                                winCount++
//                                MLog.d("本期赢")
//                            }else {
//                                loseCount++
//                                MLog.d("本期输")
//                            }
//                        }
//                        tempLotteries.removeFirst()
//                    }
//                }
//            }
//            MLog.d("winCount:$winCount")
//            MLog.d("loseCount:$loseCount")
//            MLog.d("count1:$count1")
//            MLog.d("count2:$count2")
//            MLog.d("count3:$count3")
//            MLog.d("count4:$count4")
//            MLog.d("count5:$count5")
//            MLog.d("count6:$count6")
//            MLog.d("count7:$count7")
//            MLog.d("count8:$count8")
//            MLog.d("count9:$count9")
//        }
//    }

    override fun initHistory(lotteries: List<Lottery>) {
        historyList.clear()
        smallBallHistoryList.clear()
        recommendNumList.clear()
        strategy1.clear()
        strategy2.clear()
        strategy3.clear()
        strategy4.clear()
        strategy5.clear()
        strategy6.clear()
        strategy7.clear()
        strategy8.clear()
        strategy9.clear()
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

    override fun generateStrategy() {
        //取最后开奖的五期
        val historyList : List<OpenResult> = historyList.takeLast(5)
        val smallBallHistoryList : List<OpenResult> = smallBallHistoryList.takeLast(5)

        //策略一：取上期开奖结果
        for (num in historyList[4].numbers) {
            strategy1.add(num)
        }
//        Log.d("wxt","策略一(取上期开奖结果)选号：$strategy1")

        //策略二：上期开出的幸运号码
        for (num in historyList[4].numbers) {
            if (num == 1 || num == 5 || num == 8 || num == 20 || num == 27 || num == 6 || num == 12 || num == 32) {
                strategy2.add(num)
            }
        }
//        Log.d("wxt","策略二(上期开出的幸运号码)选号：$strategy2")


        //策略三：连续两期来的号码(不包括特)
        for (i in 1 until smallBallHistoryList.size){
            for (num in smallBallHistoryList[i].numbers) {
                val index = smallBallHistoryList[i-1].numbers.indexOf(num)
                if (index != -1) {
                    strategy3.add(num)
                }
            }
        }
//        Log.d("wxt","策略三(小码连续来两期)选号：$strategy3")

        //策略四：上期的中间号码
        for (num in historyList[4].numbers) {
            val index1 = historyList[4].numbers.indexOf(num + 2)
            if (index1 != -1) {
                strategy4.add(num + 1)
            }
//            val index2 = historyList[4].numbers.indexOf(num - 2)
//            if (index2 != -1) {
//                strategy4.add(num - 1)
//            }
        }
//        Log.d("wxt","策略四(上期跳号)选号：$strategy4")

        //策略五：上期跟上上期的中间号码
        for (num in historyList[4].numbers) {
            val index1 = historyList[3].numbers.indexOf(num + 2)
            if (index1 != -1) {
                strategy5.add(num + 1)
            }
            val index2 = historyList[3].numbers.indexOf(num - 2)
            if (index2 != -1) {
                strategy5.add(num - 1)
            }
        }
//        Log.d("wxt","策略五(上期与上上期跳号)选号：$strategy5")

        //策略六：上期特特尾数乘二
        val specialNumber = historyList[4].numbers[6]
        strategy6.add((specialNumber.toString().takeLast(1).toInt()) * 2)
//        Log.d("wxt","策略六(上期特尾数乘二)选号：$strategy6")


        //策略七：上期最小两个号码相乘
        historyList[4].numbers.sort()
        val minPlus = historyList[4].numbers[0] * historyList[4].numbers[1]
        if (minPlus <= 49){
            strategy7.add(minPlus)
        }
//        Log.d("wxt","策略七(上期最小两个号码相乘)选号：$strategy7")

        //策略八：上期最小两个号码相加
        historyList[4].numbers.sort()
        val minAdd = historyList[4].numbers[0] + historyList[4].numbers[1]
        if (minAdd <= 49){
            strategy8.add(minAdd)
        }
//        Log.d("wxt","策略八(上期最小两个号码相加)选号：$strategy8")

        //策略九：上期开1，49，这期买49，1
        historyList[4].numbers.sort()
        if (historyList[4].numbers.contains(1)){
            strategy9.add(49)
        }
        if (historyList[4].numbers.contains(49)){
            strategy9.add(1)
        }
//        Log.d("wxt","策略九(上期开1，9，这期买9，1)选号：$strategy9")


        //策略N：上期大区或小区的相邻号码 暂时不用
//        for (num in historyList[4].numbers) {
//            if (num >= 45) {
//                if (num + 1 <= 49){
//                    strategy5.add(num + 1)
//                }
//                strategy5.add(num - 1)
//            }
//        }
//        if (strategy5.isEmpty()){
//            for (num in historyList[4].numbers) {
//                if (num <= 5) {
//                    if (num - 1 >= 0){
//                        strategy5.add(num - 1)
//                    }
//                    strategy5.add(num + 1)
//                }
//            }
//        }
//        Log.d("wxt","策略五的选号：$strategy5")

        generateNumList.addAll(strategy1)
        generateNumList.addAll(strategy2)
        generateNumList.addAll(strategy3)
        generateNumList.addAll(strategy4)
        generateNumList.addAll(strategy5)
        generateNumList.addAll(strategy6)
        generateNumList.addAll(strategy7)
        generateNumList.addAll(strategy8)
        generateNumList.addAll(strategy9)

//        Log.d("wxt","所有策略的选号：$generateNumList")


        //优先策略二到七，再从策略一随机补到七个
        if ((recommendNumList.size + strategy2.size) <= 7){
            recommendNumList.addAll(strategy2)
        }else if (recommendNumList.size < 7) {
            recommendNumList.addAll(strategy3.take(7 - recommendNumList.size))
        }
        if ((recommendNumList.size + strategy3.size) <= 7){
            recommendNumList.addAll(strategy3)
        }else if (recommendNumList.size < 7) {
            recommendNumList.addAll(strategy3.take(7 - recommendNumList.size))
        }
        if ((recommendNumList.size + strategy4.size) <= 7){
            recommendNumList.addAll(strategy4)
        }else if (recommendNumList.size < 7) {
            recommendNumList.addAll(strategy4.take(7 - recommendNumList.size))
        }
        if ((recommendNumList.size + strategy5.size) <= 7){
            recommendNumList.addAll(strategy5)
        }else if (recommendNumList.size < 7) {
            recommendNumList.addAll(strategy5.take(7 - recommendNumList.size))
        }
        if ((recommendNumList.size + strategy6.size) <= 7){
            recommendNumList.addAll(strategy6)
        }else if (recommendNumList.size < 7) {
            recommendNumList.addAll(strategy6.take(7 - recommendNumList.size))
        }
        if ((recommendNumList.size + strategy7.size) <= 7){
            recommendNumList.addAll(strategy7)
        }else if (recommendNumList.size < 7) {
            recommendNumList.addAll(strategy7.take(7 - recommendNumList.size))
        }

        if ((recommendNumList.size + strategy8.size) <= 7){
            recommendNumList.addAll(strategy8)
        }else if (recommendNumList.size < 7) {
            recommendNumList.addAll(strategy8.take(7 - recommendNumList.size))
        }

        if ((recommendNumList.size + strategy9.size) <= 7){
            recommendNumList.addAll(strategy9)
        }else if (recommendNumList.size < 7) {
            recommendNumList.addAll(strategy9.take(7 - recommendNumList.size))
        }


        if ((recommendNumList.size + strategy1.size) <= 7){
            recommendNumList.addAll(strategy1)
        }else if (recommendNumList.size < 7) {
            strategy1.shuffle()
            for (num in strategy1) {
                if (recommendNumList.size < 7){
                    recommendNumList.add(num)
                }
            }
        }

        if (recommendNumList.isNotEmpty()) {
            if ((Hawk.get<Recommend>("recommend")!=null) && (Hawk.get<Recommend>("recommend").longperiod == getNetLongPeriod())) {
                recommend = Hawk.get("recommend")
            } else {
                val num = recommendNumList.take(7).joinToString(",")
                recommend = Recommend(
                    longperiod = historyList[4].longPeriod + 1,
                    num = num
                )
                Hawk.put("recommend",recommend)
            }
        }
    }

    override fun runStrategy() {

    }

    override fun getMissNum(): Int {
        return 0
    }

    override fun getNextRecommend(): Recommend {
        return recommend
    }
}