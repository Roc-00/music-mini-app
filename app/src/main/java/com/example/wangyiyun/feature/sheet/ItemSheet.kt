package com.example.wangyiyun.feature.sheet

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.component.MyAsyncImage
import com.example.wangyiyun.core.model.SHEET
import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.design.theme.SpaceMedium
import com.example.wangyiyun.core.design.theme.SpaceOuter
import com.example.wangyiyun.core.design.theme.SpaceSmallHeight
import com.example.wangyiyun.core.design.theme.SpacerOuterWidth
import com.example.wangyiyun.core.design.theme.WangyiyunTheme


@Composable
fun ItemSheet(
    data: Sheet,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = SpaceOuter, vertical = SpaceMedium)
    ) {
        MyAsyncImage(
            model = data.icon, modifier = Modifier
                .size(55.dp)
                .clip(MaterialTheme.shapes.extraSmall)
        )

        SpacerOuterWidth()

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = data.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )

            SpaceSmallHeight()

            Text(
                text = stringResource(id = R.string.song_count, data.songsCount),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@Preview
@Composable
fun ItemSheetPreview(): Unit {
    WangyiyunTheme {
        ItemSheet(
            data = SHEET,
            modifier = Modifier
        )
    }
}