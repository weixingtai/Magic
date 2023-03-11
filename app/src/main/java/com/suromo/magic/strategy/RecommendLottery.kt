package com.suromo.magic.strategy

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/3/11
 * desc   :
 */
data class RecommendLottery(
    val id: Int,//策略id
    val numbers: MutableList<Int>,//策略号码
    val allMissCount: Int,//一共爆的次数
    val recentlyMissCount: Int,//最近爆的次数
    val continueMissCount: Int,//最多连爆的次数
)