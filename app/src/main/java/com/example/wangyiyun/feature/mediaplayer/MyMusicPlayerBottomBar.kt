package com.example.wangyiyun.feature.mediaplayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.component.MyMusicCircularProgressBar
import com.example.wangyiyun.core.design.component.MySweetError
import com.example.wangyiyun.core.design.theme.SpaceExtraSmall2
import com.example.wangyiyun.core.design.theme.SpaceExtraSmallHeight
import com.example.wangyiyun.core.design.theme.SpaceLargeWidth
import com.example.wangyiyun.core.design.theme.SpaceMediumWidth
import com.example.wangyiyun.core.design.theme.SpaceOuter
import com.example.wangyiyun.core.design.theme.SpaceSmallWidth
import com.example.wangyiyun.core.design.theme.WangyiyunTheme
import com.example.wangyiyun.core.extension.clickableNoRipple
import com.example.wangyiyun.feature.base.DevelopUiState


/**
 * 全局底部音乐播放控制栏
 */
@Composable
fun MyMusicPlayerBottomBar(
    title: String,
    artist: String,
    icon: String = "",
    isPlaying: Boolean = false,
    currentPosition: Float = 50F,
    duration: Float = 100F,
    recordRotation: Float = 0F,
    onPlayOrPauseClick: () -> Unit = {},
    onMusicListClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    devState: DevelopUiState = DevelopUiState.Finished,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SpaceExtraSmallHeight()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = SpaceOuter, vertical = SpaceExtraSmall2)
        ) {
            Box(
                modifier = modifier
                    .size(42.dp)
                    .graphicsLayer(rotationZ = recordRotation)
            ) {
                MyRecordImage(
                    icon = icon, modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(0.64f)
                )

                Image(
                    painter =
                    painterResource(
                        id = R.drawable.music_record_ring,
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                )
            }

            SpaceMediumWidth()
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
            )
            SpaceSmallWidth()
            Text(
                text = "-",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
            )
            SpaceSmallWidth()

            Text(
                text = artist,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.weight(1f)
            )
            Box(modifier = Modifier.height(26.dp)) {
                MyMusicCircularProgressBar(
                    progress = currentPosition,
                    progressMax = duration,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                )

                Image(
                    painter =
                    painterResource(
                        id =
                        if (isPlaying)
                            R.drawable.music_pause
                        else
                            R.drawable.music_play,
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(18.dp)
                        .clickableNoRipple {
                            onPlayOrPauseClick()
                        }
                )
            }

            SpaceLargeWidth()

            Image(
                painter =
                painterResource(
                    id =
                    R.drawable.music_list_small,
                ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .size(25.dp)
                    .clickableNoRipple {
                        onMusicListClick()
                    }
            )

            if (devState == DevelopUiState.UnFinished){
                MySweetError(message = stringResource(id = R.string.unfinished_fun))
            }

        }
    }
}


@Preview(showBackground = true, apiLevel = 33)
@Composable
fun MyMusicPlayerBottomBarPreview(): Unit {
    WangyiyunTheme {
        MyMusicPlayerBottomBar(
            title = "爱的代价",
            artist = "李宗盛"
        )
    }
}