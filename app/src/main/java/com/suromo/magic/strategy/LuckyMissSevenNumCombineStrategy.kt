package com.suromo.magic.strategy

import android.util.Log
import com.suromo.magic.db.entity.Lottery

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/3/10
 * desc   :
 */
class LuckyMissSevenNumCombineStrategy : BaseStrategy(), ILotteryStrategy {

    private val recommendNumList = mutableSetOf<Int>()
    private val openList: MutableList<MutableList<Int>> = mutableListOf()
    private val allResultList: MutableList<MutableList<Int>> = mutableListOf()
    private var noOpenList: MutableList<MutableList<Int>> = mutableListOf()

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
        TODO("Not yet implemented")
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

                openList.add(resultList)
                historyList.add(openResult)
                smallBallHistoryList.add(smallBallOpenResult)
            }

            generateStrategy()
        }
    }

    override fun generateStrategy() {

//        combinationSelect(arrayOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49"),7)
        combinationSelect(arrayOf("14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"),7)

        noOpenList = allResultList
        for (result in openList){
            if (allResultList.contains(result)){
                noOpenList.remove(result)
            }
        }

        val numbers = noOpenList.random().take(1).joinToString { "," }
        Log.d("wxt", "noOpenList:$noOpenList")
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

    private fun factorial(n: Int) : Long{
        return if (n>1){
            n * factorial(n-1)
        } else {
            1
        }
    }

    private fun combination(n: Int, m: Int): Long{
        return if(n > m) {
            factorial(n)/factorial(m)
        }else{
            0
        }
    }

    private fun combinationSelect(dataList: Array<String>, n: Int){
        combinationSelect(dataList,0, arrayOfNulls(n),0)
    }

    private fun combinationSelect(dataList: Array<String>, dataIndex: Int,resultList:Array<String?>,resultIndex:Int){
        val resultLen = resultList.size
        val resultCount = resultIndex + 1
        if (resultCount > resultLen){
            val result = mutableListOf<Int>()
            for (num in resultList){
                if (num != null) {
                    result.add(num.toInt())
                }
            }
            allResultList.add(result)
            return
        }
        for (i in dataIndex until  (dataList.size + resultCount - resultLen)){
            resultList[resultIndex] = dataList[i]
            combinationSelect(dataList,i+1,resultList,resultIndex+1)
        }
    }
}

