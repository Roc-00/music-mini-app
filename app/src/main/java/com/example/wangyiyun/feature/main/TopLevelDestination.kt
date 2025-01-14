package com.example.wangyiyun.feature.main

import com.example.wangyiyun.R
import com.example.wangyiyun.feature.discovery.DISCOVERY_ROUTE
import com.example.wangyiyun.feature.feed.FEED_ROUTE
import com.example.wangyiyun.feature.me.ME_ROUTE
import com.example.wangyiyun.feature.shortvideo.VIDEO_ROUTE

enum class TopLevelDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val titleTextId: Int,
    val route: String,
) {
    // 发现界面
    DISCOVERY(
        selectedIcon = R.drawable.tab_mall_main_selected,
        unselectedIcon = R.drawable.tab_mall_main,
        titleTextId = R.string.home,
        route = DISCOVERY_ROUTE,
    ),
    SHORT_VIDEO(
        selectedIcon = R.drawable.tab_mall_video_selected,
        unselectedIcon = R.drawable.tab_mall_video,
        titleTextId = R.string.video,
        route = VIDEO_ROUTE,
    ),
    ME(
        selectedIcon = R.drawable.tab_mall_me_selected,
        unselectedIcon = R.drawable.tab_mall_me,
        titleTextId = R.string.me,
        route = ME_ROUTE,
    ),
    FEED(
        selectedIcon = R.drawable.tab_mall_feed_selected,
        unselectedIcon = R.drawable.tab_mall_feed,
        titleTextId = R.string.feed,
        route = FEED_ROUTE,
    ),
}