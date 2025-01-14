package com.quick.app.feature.sheetdetail


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.component.MyCenterTopAppBar
import com.example.wangyiyun.core.design.component.MyErrorView
import com.example.wangyiyun.core.design.component.MyLoading
import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.design.theme.WangyiyunTheme
import com.example.wangyiyun.feature.sheetdetail.SheetDetailUiState
import com.example.wangyiyun.feature.sheetdetail.SheetDetailViewModel
import com.example.wangyiyun.feature.song.component.ItemSongSheet

@Composable
fun SheetDetailRoute(
    viewModel: SheetDetailViewModel = hiltViewModel(),
    toMusicPlayer : () -> Unit,
    finishPage: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    SheetDetailScreen(
        state = state,
        finishPage = finishPage,
        onRetryClick = viewModel::onRetryClick,
        onSongClick = viewModel::onSongClick,
        toMusicPlayer = toMusicPlayer
    )

    LaunchedEffect(viewModel.toMusicPlayer.value) {
        if(viewModel.toMusicPlayer.value){
            toMusicPlayer()
            viewModel.clearMusicPlayer()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SheetDetailScreen(
    state: SheetDetailUiState = SheetDetailUiState.Loading,
    finishPage: () -> Unit = {},
    onRetryClick: () -> Unit = {},
    onSongClick: (Int) -> Unit = {},
    toMusicPlayer: () -> Unit = {},
) {

    when (state) {
        is SheetDetailUiState.Loading -> {
            MyLoading()
        }

        is SheetDetailUiState.Error -> {
            MyErrorView(
                exception = state.exception,
                onRetryClick = onRetryClick
            )
        }

        is SheetDetailUiState.Success -> {
            ContentView(
                finishPage = finishPage,
                data = state.data,
                onSongClick = onSongClick,
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentView(
    finishPage: () -> Unit,
    data: Sheet,
    onSongClick: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            MyCenterTopAppBar(
                finishPage = finishPage,
                titleText = stringResource(id = R.string.sheet)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            //if (data.songs != null)
            data.songs?.let {//与上一行等价
                    songs ->
                itemsIndexed(songs) { index, data ->
                    ItemSongSheet(data = data, index = index, modifier = Modifier.clickable{
                        onSongClick(index)
                    })
                }

            }

        }
    }
}

//预览
@Composable
@Preview(showBackground = true)
fun SheetDetailScreenPreview(): Unit {
    WangyiyunTheme {
        SheetDetailScreen()
    }
}