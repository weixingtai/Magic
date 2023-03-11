package com.suromo.magic.strategy

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/26
 * desc   :
 */
open class BaseStrategy {

    val historyList = mutableListOf<OpenResult>()
    val recommendList = mutableListOf<OpenResult>()
    val smallBallHistoryList = mutableListOf<OpenResult>()
    val recommendLotteries = mutableListOf<RecommendLottery>()
    val strategyList = mutableListOf<OpenResult>()

    val hitList = mutableListOf<Int>()

    data class OpenResult (
        val longPeriod : Int,
        val numbers : MutableList<Int>
    )

    data class Recommend(
        var longperiod: Int,
        var numbers: MutableSet<Int>
    )
}