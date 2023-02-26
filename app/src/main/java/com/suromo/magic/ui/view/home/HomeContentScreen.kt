package com.suromo.magic.ui.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.suromo.magic.db.entity.Lottery
import com.suromo.magic.strategy.MissSevenStrategy

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
@Composable
fun HomeContentScreen(
    lotteries: List<Lottery>,
    modifier: Modifier
) {

    val strategy = MissSevenStrategy()
    strategy.initHistory(lotteries)

    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = "第${strategy.getNextRecommend().longperiod}期心水推荐：")
        Text(text = "策略一选号推荐：${strategy.strategy1}")
        Text(text = "策略二选号推荐：${strategy.strategy2}")
        Text(text = "策略三选号推荐：${strategy.strategy3}")
        Text(text = "策略四选号推荐：${strategy.strategy4}")
        Text(text = "策略五选号推荐：${strategy.strategy5}")
        Text(text = "所有策略选号推荐：${strategy.generateNumList}")
        Text(text = "本期七不中推荐：${strategy.getNextRecommend().num}")
    }


}