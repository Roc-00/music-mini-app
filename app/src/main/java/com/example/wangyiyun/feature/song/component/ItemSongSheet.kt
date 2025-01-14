package com.example.wangyiyun.feature.song.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.design.theme.SpaceSmall


/**
 * 歌单详情音乐item
 *
 * @param data 音乐
 * @param index 序号，从0开始
 * @param currentPlayMediaId 当前播放的音乐id
 */
@Composable
fun ItemSongSheet(
    data: Song,
    index: Int,
//    isPlaying: Boolean,
    currentPlayMediaId: String = "",
    modifier: Modifier = Modifier
): Unit {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(
                end = SpaceSmall,
                top = SpaceSmall,
                bottom = SpaceSmall
            ),
    ) {
        Box(modifier = Modifier.size(50.dp)) {
            Text(
                text = "${index + 1}",
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = data.title,
                color = if (currentPlayMediaId == data.id)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
            )
            Text(
                text = "${data.artist} - ${data.album}",
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
            )
        }

        IconButton(onClick = {

        }) {

        }
    }
}