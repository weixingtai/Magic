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
@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "longperiod") val longperiod: Int,
    @ColumnInfo(name = "numbers") val numbers:String,
    @ColumnInfo(name = "bet") val bet:Int,
    @ColumnInfo(name = "win") val win:Int)
