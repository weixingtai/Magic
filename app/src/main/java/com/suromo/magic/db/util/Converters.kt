package com.suromo.magic.db.util

import androidx.room.TypeConverter
import java.util.*

/**
 * author : Samuel
 * e-mail : weixingtai@meizu.com
 * time   : 2023/2/23 下午7:03
 * desc   :
 */
class Converters {
 @TypeConverter
 fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

 @TypeConverter
 fun datestampToCalendar(value: Long): Calendar =
  Calendar.getInstance().apply { timeInMillis = value }
}
