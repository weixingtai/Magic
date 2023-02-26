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
    val strategy1 = mutableListOf<Int>()
    val strategy2 = mutableListOf<Int>()
    val strategy3 = mutableListOf<Int>()
    val strategy4 = mutableListOf<Int>()
    val strategy5 = mutableListOf<Int>()


    override fun getStrategyName(): String {
        return "七不中"
    }

    override fun initHistory(lotteries: List<Lottery>) {
        if (lotteries.isNotEmpty()) {
            for (lottery in lotteries) {
                val longPeriod = lottery.longperiod
                val numbers = lottery.numbers.split(",")

                val normalNumber = numbers.take(6)

                val resultList = mutableListOf<Int>()

                for (num in normalNumber) {
                    resultList.add(num.toInt())
                }

                val openResult = OpenResult(longPeriod,resultList)

                historyList.add(openResult)
            }

            generateStrategy()
        }
    }

    override fun generateStrategy() {
        //取最后开奖的五期
        val historyList : List<OpenResult> = historyList.takeLast(5)

        //策略一：取上期开奖结果
        for (num in historyList[4].numbers) {
            strategy1.add(num)
        }
        Log.d("wxt","策略一的选号：$strategy1")


        //策略二：连续两期来的号码
        for (i in 1 until historyList.size){
            for (num in historyList[i].numbers) {
                val index = historyList[i-1].numbers.indexOf(num)
                if (index != -1) {
                    strategy2.add(num)
                }
            }
        }
        Log.d("wxt","策略二的选号：$strategy2")

        //策略三：上期的中间号码
        for (num in historyList[4].numbers) {
            val index1 = historyList[4].numbers.indexOf(num + 2)
            if (index1 != -1) {
                strategy3.add(num + 1)
            }
            val index2 = historyList[4].numbers.indexOf(num - 2)
            if (index2 != -1) {
                strategy3.add(num - 1)
            }
        }
        Log.d("wxt","策略三的选号：$strategy3")

        //策略四：上期跟上上期的中间号码
        for (num in historyList[4].numbers) {
            val index1 = historyList[3].numbers.indexOf(num + 2)
            if (index1 != -1) {
                strategy4.add(num + 1)
            }
            val index2 = historyList[3].numbers.indexOf(num - 2)
            if (index2 != -1) {
                strategy4.add(num - 1)
            }
        }
        Log.d("wxt","策略四的选号：$strategy4")

        //策略五：上期大区或小区的相邻号码
        for (num in historyList[4].numbers) {
            if (num >= 45) {
                if (num + 1 <= 49){
                    strategy5.add(num + 1)
                }
                strategy5.add(num - 1)
            }
        }
        if (strategy5.isEmpty()){
            for (num in historyList[4].numbers) {
                if (num <= 5) {
                    if (num - 1 >= 0){
                        strategy5.add(num - 1)
                    }
                    strategy5.add(num + 1)
                }
            }
        }
        Log.d("wxt","策略五的选号：$strategy5")

        generateNumList.addAll(strategy1)
        generateNumList.addAll(strategy2)
        generateNumList.addAll(strategy3)
        generateNumList.addAll(strategy4)
        generateNumList.addAll(strategy5)

        Log.d("wxt","所有策略的选号：$generateNumList")

        val num = generateNumList.shuffled().take(7).joinToString(",")

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