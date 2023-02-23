package com.suromo.magic.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/23 下午6:59
 * desc   :
 */
@Entity(tableName = "lottery")
data class Lottery(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "longperiod") val longperiod: Int,
    @ColumnInfo(name = "period") val period: String,
    @ColumnInfo(name = "numbers") val numbers:String,
    @ColumnInfo(name = "sx") val sx:String,
    @ColumnInfo(name = "wx") val wx:String,
    @ColumnInfo(name = "date") val date:String)