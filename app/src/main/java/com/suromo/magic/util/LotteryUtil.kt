package com.suromo.magic.util

import java.text.DecimalFormat
import java.util.*

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/3/4
 * desc   :
 */
fun getNetLongPeriod(): Int {
    val tms = Calendar.getInstance()

    val period = if (tms.get(Calendar.HOUR_OF_DAY) > 21 && tms.get(Calendar.MINUTE) > 30) {
        //九点半以后开奖了下一期是当前天数加1
        Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + 1
    } else {
        //九点半以前下一期是当前天数
        Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    }

    val decimalFormat = DecimalFormat("000")
    val strPeriod = decimalFormat.format(period)

    return (Calendar.getInstance().get(Calendar.YEAR).toString() + strPeriod).toInt()
}

fun getPreviousLongPeriod(): Int {
    val tms = Calendar.getInstance()

    val period = if (tms.get(Calendar.HOUR_OF_DAY) > 21 && tms.get(Calendar.MINUTE) > 30) {
        //九点半以后开奖了上一期是当前天数
        Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    } else {
        //九点半以前上一期是当前减1
        Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - 1
    }

    val decimalFormat = DecimalFormat("000")
    val strPeriod = decimalFormat.format(period)

    return (Calendar.getInstance().get(Calendar.YEAR).toString() + strPeriod).toInt()
}

fun checkIsWin(new: String,history: String) : Boolean{
    var result = true
    var newNum = new.split(",")
    val historyNum = history.split(",")
    for (num in historyNum){
        if (newNum.contains(num)){
            result = false
        }
    }
    return result
}