package com.quick.app.feature.sheetdetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SHEET_DETAIL_ROUTE = "sheet_detail"
const val SHEET_ID = "sheet_id"

fun NavController.navigateToSheetDetail(sheetId: String): Unit {
    navigate("${SHEET_DETAIL_ROUTE}/$sheetId")
}

fun NavGraphBuilder.sheetDetailScreen(
    finishPage: () -> Unit,
    toMusicPlayer: () -> Unit,
) {
    composable("${SHEET_DETAIL_ROUTE}/{$SHEET_ID}") {
        SheetDetailRoute(
            finishPage = finishPage,
            toMusicPlayer = toMusicPlayer,
        )
    }
}