package com.suromo.magic.data.source.local.room.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/07/2022/7/6
 * desc   : 用户对应实体类
 */
@Entity(
    tableName = "user"
)
data class User (
    @ColumnInfo(name = "name") val name: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var userId: Long = 0
}