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

    var recommend = Recommend(0,"")

    val hitList = mutableListOf<Int>()

    data class OpenResult (
        val longPeriod : Int,
        val numbers : MutableList<Int>
    )

    data class Recommend(
        var longperiod: Int,
        var num: String
    )
}