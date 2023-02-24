package com.suromo.magic.net.response

import com.google.gson.annotations.SerializedName

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/25
 * desc   :
 */
data class Lotteries (
    @field:SerializedName("year") val year: Int,
    @field:SerializedName("item") val item: List<Lottery>
)