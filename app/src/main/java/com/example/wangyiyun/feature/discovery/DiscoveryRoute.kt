package com.example.wangyiyun.feature.discovery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.HorizontalPagerIndicator
import com.example.wangyiyun.core.design.component.MySweetError
import com.example.wangyiyun.core.design.theme.ArrowIcon
import com.example.wangyiyun.core.design.theme.LocalDividerColor
import com.example.wangyiyun.core.design.theme.SpaceExtraMedium
import com.example.wangyiyun.core.design.theme.SpaceMedium
import com.example.wangyiyun.core.design.theme.SpaceOuter
import com.example.wangyiyun.core.design.theme.WangyiyunTheme
import com.example.wangyiyun.core.model.Ad
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.model.ViewData
import com.example.wangyiyun.core.ui.FakeDatasProvider
import com.example.wangyiyun.feature.base.DevelopUiState
import com.example.wangyiyun.feature.sheet.ItemSheetGrid
import com.example.wangyiyun.feature.song.component.ItemSong
import com.example.wangyiyun.util.ResourceUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DiscoveryRoute(
    viewModel: DiscoveryViewModel = hiltViewModel(),
    toSheetDetail: (String) -> Unit,
    toggleDrawer: () -> Unit,
    toMusicPlayer: () -> Unit,
    toUri: (String) -> Unit,
) {
    val topDatum by viewModel.topdatum.collectAsState()
    val devState by viewModel.developState.collectAsState()

    DiscoveryScreen(
        topDatum = topDatum,
        toSheetDetail = toSheetDetail,
        toggleDrawer = toggleDrawer,
        onSongClick = viewModel::onSongClick,
        toUri = toUri,
        devState = devState,
        toSearch = viewModel::unfinishedFunClick
    )

    LaunchedEffect(viewModel.toMusicPlayer.value) {
        if (viewModel.toMusicPlayer.value) {
            toMusicPlayer()
            viewModel.clearMusicPlayer()
        }
    }
}

@Composable
fun DiscoveryScreen(
    toggleDrawer: () -> Unit = {},
    toSearch: () -> Unit = {},
    topDatum: List<ViewData> = listOf(),
    toSheetDetail: (String) -> Unit = {},
    onSongClick: (List<Song>, Int) -> Unit = { _, _ -> },
    toUri: (String) -> Unit = {},
    devState: DevelopUiState = DevelopUiState.Finished,
) {
    val gridState = rememberLazyGridState()

    Scaffold(
        topBar = {
            DiscoveryTopBar(
                toggleDrawer, toSearch, devState = devState
            )
        },
        // 排除底部导航栏边距
        contentWindowInsets = ScaffoldDefaults
            .contentWindowInsets
            .exclude(WindowInsets.navigationBars)

    ) { paddingValues -> // 这个padding相当于一个安全区，不会被顶部，底部的内容覆盖需要的padding
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(paddingValues = paddingValues)
        ) {
            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    horizontal = SpaceOuter
                ),
                verticalArrangement = Arrangement.spacedBy(SpaceExtraMedium),
                horizontalArrangement = Arrangement.spacedBy(SpaceExtraMedium),
                modifier = Modifier.fillMaxSize()
            ) {
                topDatum.forEach { viewData ->
                    if (viewData.ads != null) {
                        //轮播图
                        item(span = {
                            GridItemSpan(
                                maxLineSpan
                            )
                        }) {
                            DiscoveryBanner(
                                data = viewData.ads!!,
                                onAdClick = { ad ->
                                    ad.uri?.let { adUri ->
                                        toUri(adUri)
                                    }
                                }
                            )
                        }
                    } else if (viewData.sheets != null) {
                        item(
                            span = {
                                GridItemSpan(
                                    maxLineSpan
                                )
                            }
                        ) {
                            ItemDiscoveryTitle(
                                title = R.string.recommend_sheet
                            )
                        }

                        items(viewData.sheets) { sheet ->
                            ItemSheetGrid(
                                data = sheet,
                                toSheetDetail = {
                                    toSheetDetail(sheet.id)
                                },
                            )
                        }
                    } else if (viewData.songs != null) {
                        item(
                            span = {
                                GridItemSpan(
                                    maxLineSpan
                                )
                            }
                        ) {
                            ItemDiscoveryTitle(
                                title = R.string.recommend_song
                            )
                        }

                        itemsIndexed(viewData.songs,
                            span = { _, _ ->
                                GridItemSpan(maxLineSpan)
                            }) { index, item ->
                            ItemSong(data = item, modifier = Modifier.clickable {
                                onSongClick(viewData.songs, index)
                            })
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiscoveryBanner(
    data: List<Ad>,
    onAdClick: (Ad) -> Unit = {},
) {
    Card(
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxWidth()
                //图片的宽度/高度
                .aspectRatio(2.571f)
                .clip(MaterialTheme.shapes.extraSmall)
        ) {
            val pagerState = rememberPagerState(pageCount = {
                data.size
            })

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
            ) { page ->
                val item = data[page]
                AsyncImage(
                    model = ResourceUtil.r(item.icon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onAdClick(item)
                        },
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = data.size,
                modifier = Modifier.padding(SpaceMedium),
            )

            val scope = rememberCoroutineScope()
            val lifecycle = LocalLifecycleOwner.current.lifecycle
            LaunchedEffect(pagerState) {
                var job: Job? = null

                val observer = object : DefaultLifecycleObserver {
                    override fun onResume(owner: LifecycleOwner) {
                        job = scope.launch {
                            while (true) {
                                delay(3000L)
                                val nextPage = (pagerState.currentPage + 1) % data.size
                                pagerState.animateScrollToPage(nextPage)
                            }
                        }
                    }

                    override fun onPause(owner: LifecycleOwner) {
                        super.onPause(owner)
                        job?.cancel()
                    }
                }

                lifecycle.addObserver(observer)
            }
        }
    }

}

@Composable
fun ItemDiscoveryTitle(title: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(vertical = SpaceOuter)
                .weight(1f),
        )

        Text(
            text = stringResource(id = R.string.show_more),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier
        )

        ArrowIcon()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiscoveryTopBar(
    toggleDrawer: () -> Unit,
    toSearch: () -> Unit,
    devState: DevelopUiState,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = toggleDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }
        },
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(LocalDividerColor.current)
                    .clickable {
                        toSearch()
                    }

            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(18.dp),
                )
                Text(
                    text = stringResource(id = R.string.search),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                )

                if (devState == DevelopUiState.UnFinished){
                    MySweetError(message = stringResource(id = R.string.unfinished_fun))
                }
            }
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.message),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
//                tint = Color.Red,
                modifier = Modifier
                    .padding(horizontal = SpaceExtraMedium)
                    .size(28.dp),
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DiscoveryScreenPreview(
    @PreviewParameter(FakeDatasProvider::class)
    songs: List<Song>,
): Unit {
    WangyiyunTheme {
        DiscoveryScreen()
    }
}