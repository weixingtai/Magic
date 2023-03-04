package com.suromo.magic.strategy

import com.suromo.magic.db.entity.Lottery

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/26
 * desc   :
 */
interface ILotteryStrategy {

    //获取策略名称
    fun getStrategyName(): String
//    fun calculateHistory(lotteries: List<Lottery>)
    //初始化开奖结果
    fun initHistory(lotteries: List<Lottery>)

    //生成策略开奖结果
    fun generateStrategy()

    //运行策略
    fun runStrategy()

    //获取策略爆了的期数
    fun getMissNum(): Int

    //获取下一期推荐开奖
    fun getNextRecommend(): BaseStrategy.Recommend

}