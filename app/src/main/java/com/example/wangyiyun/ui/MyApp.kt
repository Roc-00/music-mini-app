package com.example.wangyiyun.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.wangyiyun.feature.login.loginScreen
import com.example.wangyiyun.feature.login.navigateToLogin
import com.example.wangyiyun.feature.loginhome.finishAllLoginPages
import com.example.wangyiyun.feature.loginhome.loginHomeScreen
import com.example.wangyiyun.feature.loginhome.navigateToLoginHome
import com.example.wangyiyun.feature.main.mainScreen
import com.example.wangyiyun.feature.main.navigateToMain
import com.example.wangyiyun.feature.mediaplayer.musicPlayerScreen
import com.example.wangyiyun.feature.mediaplayer.navigateToMusicPlayer
import com.example.wangyiyun.feature.splash.SPLASH_ROUTE
import com.example.wangyiyun.feature.splash.splashScreen
import com.quick.app.feature.sheetdetail.navigateToSheetDetail
import com.quick.app.feature.sheetdetail.sheetDetailScreen

@Composable
fun MyApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = SPLASH_ROUTE) {
        fun processUriClick(data: String) {
            navController.navigateToMain()

            Log.d(TAG, "processUriClick: $data")

        }
        splashScreen(
            //            toMain = {
//                //更多逻辑处理
//                navController.navigateToMain()
//            }
            toMain = navController::navigateToMain // 更快
        )
        mainScreen(
            finishPage = navController::popBackStack,
            toSheetDetail = navController::navigateToSheetDetail,
            toLogin = navController::navigateToLoginHome,
            toMusicPlayer = navController::navigateToMusicPlayer,
            toUri = ::processUriClick,
        )

        sheetDetailScreen(
            finishPage = navController::popBackStack,
            toMusicPlayer = navController::navigateToMusicPlayer,
        )

        musicPlayerScreen(
            finishPage = navController::popBackStack,
        )

        loginHomeScreen(
            finishPage = navController::popBackStack,
            toLogin = navController::navigateToLogin,
            toCodeLogin = {},
            finishAllLoginPages = navController::finishAllLoginPages
        )

        loginScreen(
            finishPage = navController::popBackStack,
            toRegister = {},
            toSetPassword = {},
            finishAllLoginPages = navController::finishAllLoginPages,
        )
    }
}
private const val TAG = "MyApp"