package com.suromo.magic.net.response

import com.google.gson.annotations.SerializedName

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/24
 * desc   :
 */
data class Lottery(
    @field:SerializedName("longperiod") val longperiod: Int,
    @field:SerializedName("period") val period: String,
    @field:SerializedName("numbers") val numbers:String,
    @field:SerializedName("sx") val sx:String,
    @field:SerializedName("wx") val wx:String,
    @field:SerializedName("date") val date:String
    )