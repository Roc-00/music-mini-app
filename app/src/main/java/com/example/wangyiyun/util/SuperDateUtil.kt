package com.example.wangyiyun.util

import android.icu.util.Calendar

object SuperDateUtil {

    /**
     * 获取当前日期的年份
     */
    fun currentYear(): Int{
        return Calendar.getInstance().get(Calendar.YEAR) // 获取当前日期的年份
    }

    /**
     * 获取当前日期的日
     */
    fun currentDayOfMonth(): Int{
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }
}