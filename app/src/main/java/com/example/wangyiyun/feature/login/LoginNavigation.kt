package com.example.wangyiyun.feature.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val LOGIN_ROUTE = "login"

fun NavController.navigateToLogin() = navigate(LOGIN_ROUTE)

fun NavGraphBuilder.loginScreen(
    finishPage: () -> Unit,
    toRegister: () -> Unit,
    toSetPassword: () -> Unit,
    finishAllLoginPages: () -> Unit,
) {
    composable(LOGIN_ROUTE) {
        LoginRoute(
            finishPage = finishPage,
            toRegister = toRegister,
            toSetPassword = toSetPassword,
            finishAllLoginPages = finishAllLoginPages,
        )
    }
}