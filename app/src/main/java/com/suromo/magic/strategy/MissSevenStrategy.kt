package com.suromo.magic.strategy

import android.util.Log
import com.suromo.magic.db.entity.Lottery

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


    override fun getStrategyName(): String {
        return "七不中"
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

    override fun generateStrategy() {
        //取最后开奖的五期
        val historyList : List<OpenResult> = historyList.takeLast(5)
        val smallBallHistoryList : List<OpenResult> = smallBallHistoryList.takeLast(5)

        //策略一：取上期开奖结果
        for (num in historyList[4].numbers) {
            strategy1.add(num)
        }
        Log.d("wxt","策略一(取上期开奖结果)选号：$strategy1")

        //策略二：上期开出的幸运号码
        for (num in historyList[4].numbers) {
            if (num == 1 || num == 5 || num == 8 || num == 20 || num == 27 || num == 6 || num == 12 || num == 32) {
                strategy2.add(num)
            }
        }
        Log.d("wxt","策略二(上期开出的幸运号码)选号：$strategy1")


        //策略三：连续两期来的号码(不包括特)
        for (i in 1 until smallBallHistoryList.size){
            for (num in smallBallHistoryList[i].numbers) {
                val index = smallBallHistoryList[i-1].numbers.indexOf(num)
                if (index != -1) {
                    strategy3.add(num)
                }
            }
        }
        Log.d("wxt","策略三(小码连续来两期)选号：$strategy3")

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
        Log.d("wxt","策略四(上期跳号)选号：$strategy4")

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
        Log.d("wxt","策略五(上期与上上期跳号)选号：$strategy4")

        //策略六：上期特特尾数乘二
        val specialNumber = historyList[4].numbers[6]
        strategy6.add((specialNumber.toString().takeLast(1).toInt()) * 2)
        Log.d("wxt","策略六(上期特尾数乘二)选号：$strategy6")


        //策略七：上期最小两个号码相乘
        historyList[4].numbers.sort()
        val minPlus = historyList[4].numbers[0] * historyList[4].numbers[1]
        if (minPlus <= 49){
            strategy7.add(minPlus)
        }
        Log.d("wxt","策略七(上期最小两个号码相乘)选号：$strategy7")

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

        Log.d("wxt","所有策略的选号：$generateNumList")


        //优先策略二到七，再从策略一随机补到七个
        if ((recommendNumList.size + strategy2.size) <= 7){
            recommendNumList.addAll(strategy2)
        }else {
            recommendNumList.addAll(strategy3.take((strategy3.size - recommendNumList.size)))
        }
        if ((recommendNumList.size + strategy3.size) <= 7){
            recommendNumList.addAll(strategy3)
        }else {
            recommendNumList.addAll(strategy3.take((strategy3.size - recommendNumList.size)))
        }
        if ((recommendNumList.size + strategy4.size) <= 7){
            recommendNumList.addAll(strategy4)
        }else {
            recommendNumList.addAll(strategy4.take((strategy4.size - recommendNumList.size)))
        }
        if ((recommendNumList.size + strategy5.size) <= 7){
            recommendNumList.addAll(strategy5)
        }else {
            recommendNumList.addAll(strategy5.take((strategy5.size - recommendNumList.size)))
        }
        if ((recommendNumList.size + strategy6.size) <= 7){
            recommendNumList.addAll(strategy6)
        }else {
            recommendNumList.addAll(strategy6.take((strategy6.size - recommendNumList.size)))
        }
        if ((recommendNumList.size + strategy7.size) <= 7){
            recommendNumList.addAll(strategy7)
        }else {
            recommendNumList.addAll(strategy7.take((strategy7.size - recommendNumList.size)))
        }



        if ((recommendNumList.size + strategy1.size) <= 7){
            recommendNumList.addAll(strategy1)
        }else {
            strategy1.shuffle()
            for (num in strategy1) {
                if (recommendNumList.size < 7){
                    recommendNumList.add(num)
                }
            }
        }

        Log.d("wxt","size:${recommendNumList.size}")
        val num = recommendNumList.take(7).joinToString(",")
        Log.d("wxt","本期七不中推荐：$num")

        recommend = Recommend(
            longperiod = historyList[4].longPeriod + 1,
            num = num
        )

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