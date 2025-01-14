package com.example.wangyiyun.feature.mediaplayer

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import com.example.wangyiyun.R
import com.example.wangyiyun.core.model.PlaybackMode
import com.example.wangyiyun.core.design.theme.SpaceLarge
import com.example.wangyiyun.core.design.theme.SpaceOuter
import com.example.wangyiyun.core.design.theme.SpacerOuterHeight
import com.example.wangyiyun.core.design.theme.WangyiyunTheme
import com.example.wangyiyun.core.extension.asFormatTimeString
import com.example.wangyiyun.core.extension.clickableNoRipple
import com.example.wangyiyun.core.media.EMPTY_PLAYBACK_STATE
import com.example.wangyiyun.core.media.PlaybackState
import com.example.wangyiyun.feature.mediaplayer.MusicPlayViewModel


@Composable
fun MusicPlayerRoute(
    finishPage: () -> Unit,
    viewModel: MusicPlayViewModel = hiltViewModel()
) {
    val nowPlaying by viewModel.nowPlaying.collectAsStateWithLifecycle()
    val playbackState by viewModel.playbackState.collectAsStateWithLifecycle()
    val currentPosition by viewModel.currentPosition.collectAsStateWithLifecycle()
//    val playRepeatMode by viewModel.playRepeatMode.collectAsStateWithLifecycle()

    MusicPlayerScreen(
        finishPage = finishPage,
        nowPlaying = nowPlaying,
        playbackState = playbackState,
        currentPosition = currentPosition,
        onSeek = viewModel::onSeek,
//        playRepeatMode = playRepeatMode,
//        onChangeRepeatModeClick = viewModel::onChangeRepeatModeClick,
        onPreviousClick = viewModel::onPreviousClick,
        onPlayOrPauseClick = viewModel::onPlayOrPauseClick,
        onNextClick = viewModel::onNextClick,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerScreen(
    finishPage: () -> Unit = {},
    nowPlaying: MediaItem = MediaItem.EMPTY,
    playbackState: PlaybackState = EMPTY_PLAYBACK_STATE,
    currentPosition: Long = 0,
    onSeek: (Float) -> Unit = { },
    playRepeatMode: PlaybackMode = PlaybackMode.REPEAT_LIST,
    onChangeRepeatModeClick: () -> Unit = {},
    onPreviousClick: () -> Unit = {},
    onPlayOrPauseClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = finishPage) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = nowPlaying.mediaMetadata.title.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                        )
                        Text(
                            text = nowPlaying.mediaMetadata.artist.toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (musicPlayerBackground, recordThumb, recordBackground) = createRefs()

                //歌曲封面背景图片
                BackgroundContent(
                    data = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(musicPlayerBackground) {
                            //宽高和父布局一样
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
//                        .padding(paddingValues)
                        .padding(bottom = SpaceLarge)
                ) {
                    Surface(
                        color = Color.Transparent,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {

                    }

                    //其他按钮
                    PlayMediaOtherButtons(

                    )

                    SpacerOuterHeight()

                    //进度信息
                    ProgressInfo(
                        currentPosition = currentPosition,
                        duration = playbackState.durationFormat,
                        onSeek = onSeek,
                    )
                    SpacerOuterHeight()

                    //播放控制按钮
                    PlayerMediaButtons(
                        isPlaying = playbackState.isPlaying,
                        playRepeatMode = playRepeatMode,
                        onChangeRepeatModeClick = onChangeRepeatModeClick,
                        onPreviousClick = onPreviousClick,
                        onPlayOrPauseClick = onPlayOrPauseClick,
                        onNextClick = onNextClick,
//                        onMusicListClick = toggleShowMusicListDialog,
//                        onChangeRepeatModeClick = {},
//                        onPreviousClick = {},
//                        onNextClick = {},
//                        onPlayOrPauseClick = {},
                        onMusicListClick = {},
                    )
                }
            }
        }
    }
}

@Composable
fun PlayerMediaButtons(
    isPlaying: Boolean,
    playRepeatMode: PlaybackMode,
    onChangeRepeatModeClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onPlayOrPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onMusicListClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MusicControlButton(
            when (playRepeatMode) {
                PlaybackMode.REPEAT_LIST -> R.drawable.music_repeat_list
                PlaybackMode.REPEAT_ONE -> R.drawable.music_repeat_single
                PlaybackMode.REPEAT_SHUFFLE, PlaybackMode.REPEAT_UNSPECIFIED -> R.drawable.music_repeat_single
            },
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {
                    onChangeRepeatModeClick()
                }
        )
        MusicControlMiddleButton(
            R.drawable.music_previous,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {
                    onPreviousClick()
                }
        )
        Image(
            painter =
            painterResource(
                id =
                if (isPlaying)
                    R.drawable.music_pause
                else
                    R.drawable.music_play
            ),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {
                    onPlayOrPauseClick()
                }
        )
        MusicControlMiddleButton(
            R.drawable.music_next,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {
                    onNextClick()
                }
        )
        MusicControlButton(
            R.drawable.music_list,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {
                    onMusicListClick()
                }
        )
    }
}

@Composable
fun MusicControlMiddleButton(icon: Int, modifier: Modifier) {
    Image(
        painter =
        painterResource(
            id = icon
        ),
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f)
            .padding(19.dp)
//            .background(Color.Red)
    )
}

@Composable
fun MusicControlButton(icon: Int, modifier: Modifier) {
    Image(
        painter =
        painterResource(
            id = icon
        ),
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f)
            .padding(SpaceOuter)
//            .background(Color.Red)
    )
}

@Composable
fun ProgressInfo(
    currentPosition: Long,
    duration: Long,
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = SpaceOuter),
    ) {
        Slider(
            modifier = Modifier
                .fillMaxWidth(),
            value = currentPosition.toFloat(),
            valueRange = 0f..duration.toFloat(),
            onValueChange = onSeek,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentPosition.asFormatTimeString(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Text(
                text = duration.asFormatTimeString(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}


@Composable
fun PlayMediaOtherButtons(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MusicControlSmallButton(
            R.drawable.music_download,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {

                }
        )
        MusicControlSmallButton(
            R.drawable.music_collect,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {

                }
        )
        MusicControlSmallButton(
            R.drawable.music_comment,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {

                }
        )
        MusicControlSmallButton(
            R.drawable.music_sing,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {

                }
        )
        MusicControlSmallButton(
            R.drawable.music_equalizer,
            modifier = Modifier
                .weight(1f)
                .clickableNoRipple {

                }
        )
    }
}

@Composable
fun MusicControlSmallButton(icon: Int, modifier: Modifier) {
    Image(
        painter =
        painterResource(
            id = icon
        ),
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f)
            .padding(SpaceOuter)
    )
}

@Composable
fun BackgroundContent(
    data: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.music_player_background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun SplashRoutePreview(): Unit {
    WangyiyunTheme {
        MusicPlayerScreen(

        )
    }
}