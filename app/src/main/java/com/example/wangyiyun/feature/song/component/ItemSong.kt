package com.example.wangyiyun.feature.song.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.component.MyAsyncImage
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.design.theme.LocalDividerColor
import com.example.wangyiyun.core.design.theme.SpaceMedium
import com.example.wangyiyun.core.design.theme.SpaceSmallHeight
import com.example.wangyiyun.core.design.theme.WangyiyunTheme
import com.example.wangyiyun.core.ui.FakeDatas.SONG
import com.example.wangyiyun.util.ResourceUtil

/**
 * 单个歌曲显示组件
 */
@Composable
fun ItemSong(data: Song, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        MyAsyncImage(data.icon, modifier = Modifier.size(50.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = SpaceMedium)
        ) {
            Text(
                text = data.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            SpaceSmallHeight()

            Text(
                text = "${data.artist} - ${data.album}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview
@Composable
fun ItemSongPreview(): Unit {
    WangyiyunTheme {
        ItemSong(
            data = SONG,
            modifier = Modifier
        )
    }
}