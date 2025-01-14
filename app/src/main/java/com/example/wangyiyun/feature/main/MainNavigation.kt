package com.example.wangyiyun.feature.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.wangyiyun.feature.splash.SPLASH_ROUTE

const val MAIN_ROUTE = "main"

/**
 * 跳转
 */
fun NavController.navigateToMain(): Unit {
    navigate(MAIN_ROUTE) {
        launchSingleTop = true // 单例模式
        //关闭splash，以及之前所有界面
        popUpTo(SPLASH_ROUTE) {
            inclusive = true
        }
    }
}

/**
 * 配置导航
 */
fun NavGraphBuilder.mainScreen(
    finishPage: () -> Unit,
    toSheetDetail: (String) -> Unit,
    toLogin: () -> Unit,
    toMusicPlayer: () -> Unit,
    toUri: (String) -> Unit,
) {
    composable(MAIN_ROUTE) {
        MainRoute(
            finishPage = finishPage,
            toSheetDetail = toSheetDetail,
            toLogin = toLogin,
            toMusicPlayer = toMusicPlayer,
            toUri = toUri,
        )
    }
}

