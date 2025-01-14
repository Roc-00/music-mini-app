package com.example.wangyiyun.feature.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import com.example.wangyiyun.R
import com.example.wangyiyun.core.database.model.SongEntity
import com.example.wangyiyun.core.design.component.MyNavigationBar
import com.example.wangyiyun.core.design.theme.ArrowIcon
import com.example.wangyiyun.core.design.theme.SpaceExtraSmallHeight
import com.example.wangyiyun.core.design.theme.SpaceMediumWidth
import com.example.wangyiyun.core.design.theme.SpaceOuter
import com.example.wangyiyun.core.design.theme.SpacerOuterHeight
import com.example.wangyiyun.core.design.theme.bodyXLarge
import com.example.wangyiyun.core.design.theme.extraSmallRoundedCornerShape
import com.example.wangyiyun.core.extension.clickableNoRipple
import com.example.wangyiyun.core.media.EMPTY_PLAYBACK_STATE
import com.example.wangyiyun.core.media.PlaybackState
import com.example.wangyiyun.core.model.UserData
import com.example.wangyiyun.feature.base.DevelopUiState
import com.example.wangyiyun.feature.discovery.DiscoveryRoute
import com.example.wangyiyun.feature.feed.FeedRoute
import com.example.wangyiyun.feature.me.MeRoute
import com.example.wangyiyun.feature.mediaplayer.MyMusicPlayerBottomBar
import com.example.wangyiyun.feature.shortvideo.ShortVideoRoute
import kotlinx.coroutines.launch

@Composable
fun MainRoute(
    finishPage: () -> Unit,
    toSheetDetail: (String) -> Unit,
    toLogin: () -> Unit,
    toMusicPlayer: () -> Unit,
    toUri: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()

) {

    val nowPlaying by viewModel.nowPlaying.collectAsStateWithLifecycle()
    val playbackState by viewModel.playbackState.collectAsStateWithLifecycle()
    val currentPosition by viewModel.currentPosition.collectAsStateWithLifecycle()
//    val recordRotation by viewModel.recordRotation.collectAsStateWithLifecycle()
//    val musicDatum by viewModel.datum.collectAsStateWithLifecycle()
    val musicDatum by viewModel.datum.collectAsStateWithLifecycle()

    val devState by viewModel.developState.collectAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) //抽屉状态，默认closed
    val scope = rememberCoroutineScope() // 打开抽屉需要在协程中进行，所以定义一个协程scope

    val toggleDrawer: () -> Unit = { // 控制抽屉开关
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close() //如果开着就关上，如果关着就打开
            }
        }
    }
    /**
     * 侧滑菜单
     */
    ModalNavigationDrawer(
        drawerState = drawerState, //策划菜单状态
        drawerContent = {
            ModalDrawerSheet (
                drawerContainerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .fillMaxHeight()
            ){
                MyDrawerView( // 这里是策划界面的内容
                    userData = UserData(),
                    isLogin = false,
                    toLogin = toLogin,
                )
            }

        },
    ) {
        MainScreen( // 这里是正常界面的内容
            toSheetDetail = toSheetDetail,
            toggleDrawer = toggleDrawer,
            toMusicPlayer = toMusicPlayer,
            toUri = toUri,
            nowPlaying = nowPlaying,
            playbackState = playbackState,
            currentPosition = currentPosition.toFloat(),
            recordRotation = 45F,
            onPlayOrPauseClick = viewModel::onPlayOrPauseClick,
            onMusicListClick = viewModel::unfinishedFunClick,
            datum = musicDatum,
            devState = devState
        )
    }

}

@Composable
fun MyDrawerView(
    userData: UserData,
    isLogin: Boolean,
    toLogin: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = SpaceOuter)
    ){
        SpacerOuterHeight()

        MyUserInfoView(
            userData = userData,
            isLogin = isLogin,
//            toProfile = toProfile,
            toLogin = toLogin,
//            toScan = toScan,
            toProfile = {},
            toScan = {},
        )

        SpacerOuterHeight()

        Column(
            verticalArrangement = Arrangement.spacedBy(SpaceOuter),
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState) //使其可以滚动
        ) {
//            MyCard1(
//                toMessage = toMessage,
//                toFriend = toFriend,
//                toCode = toCode,
//            )
//
//            (1..2).forEach { _ ->
//                MyDrawerCard()
//            }
//
//            MySettingCard(
//                toSetting = toSetting,
//            )
//
//            MyAboutCard(
//                toAbout = toAbout,
//            )
//
//            if (isLogin) {
//                OutlinedButton(
//                    onClick = onLogoutClick,
//                    modifier = Modifier
//                        .padding(horizontal = SpaceOuter, vertical = 40.dp)
//                        .fillMaxWidth()
//                ) {
//                    Text(text = stringResource(id = R.string.logout))
//                }
//            }
        }
    }
}

/**
 * 用户信息
 */
@Composable
fun MyUserInfoView(
    userData: UserData,
    isLogin: Boolean,
    toProfile: () -> Unit,
    toScan: () -> Unit,
    toLogin: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (userData.isLogin()) {
            //用户信息
//            UserProfile(
//                userData.user,
//                toProfile = toProfile,
//                toScan = toScan,
//            )
        } else {
            DefaultUserProfile(
                toLogin = toLogin,
                toScan = toScan,
            )
        }
    }
}

@Composable
private fun UserProfile(
//    data: UserPreferences,
    toProfile: () -> Unit,
    toScan: () -> Unit,
) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickableNoRipple {
//                toProfile()
//            }
//    ) {
//        if (TextUtils.isEmpty(data.icon)) {
//            Image(
//                painter = painterResource(id = R.drawable.default_avatar),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = userIconModifier,
//            )
//        } else {
//            AsyncImage(
//                model = ResourceUtil.r(data.icon),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = userIconModifier,
//            )
//        }
//
//        SpaceMediumWidth()
//
//        Text(
//            text = data.nickname,
//            style = MaterialTheme.typography.bodyXLarge,
//            color = MaterialTheme.colorScheme.onSurface,
//        )
//
//
//        ArrowIcon()
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        IconButton(onClick = toScan) {
//            Icon(
//                painter = painterResource(id = R.drawable.scan),
//                contentDescription = null,
//                modifier = Modifier.size(36.dp),
//            )
//        }
//    }
}

private val userIconModifier = Modifier
    .size(30.dp)
    .clip(extraSmallRoundedCornerShape)

@Composable
private fun DefaultUserProfile(
    toLogin: () -> Unit,
    toScan: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoRipple { toLogin() },
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = userIconModifier,
        )

        SpaceMediumWidth()

        Text(
            text = stringResource(id = R.string.login_or_register),
            style = MaterialTheme.typography.bodyXLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        ArrowIcon()

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = toScan) {
            Icon(
                painter = painterResource(id = R.drawable.scan),
                contentDescription = null,
                modifier = Modifier.size(36.dp),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)  // 实验性api，可能有大变动
@Composable
fun MainScreen(
    finishPage: () -> Unit = {},
    toSheetDetail: (String) -> Unit = {},
    toggleDrawer: () -> Unit = {},
    toMusicPlayer: () -> Unit,
    toUri: (String) -> Unit,
    nowPlaying: MediaItem = MediaItem.EMPTY,
    playbackState: PlaybackState = EMPTY_PLAYBACK_STATE,
    currentPosition: Float = 0F,
    recordRotation: Float = 0F,
    onPlayOrPauseClick: () -> Unit = {},
    onMusicListClick: () -> Unit = {},
    datum: List<SongEntity> = listOf(),
    devState: DevelopUiState = DevelopUiState.Finished,
) {
    //当前选中的界面名称
    var currentDestination by rememberSaveable {
        mutableStateOf(TopLevelDestination.DISCOVERY.route)
    }

    val paperState = rememberPagerState {
        4
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(// 左右滑动切换页面
            state = paperState,
            userScrollEnabled = false, // 是否可以滑动切换界面
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            when (page) {
                0 -> DiscoveryRoute(
                    toSheetDetail = toSheetDetail,
                    toggleDrawer = toggleDrawer,
                    toMusicPlayer = toMusicPlayer,
                    toUri = toUri,
                )
                1 -> ShortVideoRoute()
                2 -> MeRoute()
                3 -> FeedRoute()
            }
        }
        //音乐控制
        if (datum.isNotEmpty()) {
            MyMusicPlayerBottomBar(
                title = nowPlaying.mediaMetadata.title.toString(),
                artist = nowPlaying.mediaMetadata.artist.toString(),
                icon = nowPlaying.mediaMetadata.artworkUri.toString(),
                isPlaying = playbackState.isPlaying,
                currentPosition = currentPosition,
                duration = playbackState.durationFormat.toFloat(),
                recordRotation = recordRotation,
                onPlayOrPauseClick = onPlayOrPauseClick,
//                onMusicListClick = onMusicListClick,
                modifier = Modifier.clickable {
                    toMusicPlayer()
                },
                devState = devState
            )
        }
//        MyMusicPlayerBottomBar(
//            title = nowPlaying.mediaMetadata.title.toString(),
//            artist = nowPlaying.mediaMetadata.artist.toString(),
//            icon = nowPlaying.mediaMetadata.artworkUri.toString(),
//            isPlaying = playbackState.isPlaying,
//            currentPosition = currentPosition,
//            duration = playbackState.durationFormat.toFloat(),
//            recordRotation = recordRotation,
//            onPlayOrPauseClick = onPlayOrPauseClick,
//            onMusicListClick = onMusicListClick,
//            modifier = Modifier.clickable {
//                toMusicPlayer()
//            },
//        )

        SpaceExtraSmallHeight()
        MyNavigationBar(
            destination = TopLevelDestination.entries, //.entries 是 Kotlin 中枚举类的一个属性，它返回一个包含该枚举类所有枚举常量的列表
            currentDestination = currentDestination,
            onNavigateToDestination = {index ->
                currentDestination = TopLevelDestination.values()[index].route
                scope.launch {
                    paperState.scrollToPage(index)
                }
                
            },
            modifier = Modifier
        )
    }


}
