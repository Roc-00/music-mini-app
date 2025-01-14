package com.example.wangyiyun.feature.sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wangyiyun.core.design.component.MyAsyncImage
import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.model.User
import com.example.wangyiyun.core.design.theme.SpaceExtraSmall2Width
import com.example.wangyiyun.core.design.theme.SpaceMedium
import com.example.wangyiyun.core.design.theme.SpaceSmall
import com.example.wangyiyun.core.design.theme.WangyiyunTheme
import com.example.wangyiyun.util.StringUtil


val sheetModifier = Modifier
    .fillMaxWidth()
    .aspectRatio(1f)

@Composable
fun ItemSheetGrid(
    data: Sheet,
    toSheetDetail: () -> Unit = {},
    modifier: Modifier = Modifier,
): Unit {
    Column(
        modifier
            .clip(MaterialTheme.shapes.small)
            .clickable {
                toSheetDetail()
            }
    ) {
        Box(
            modifier = sheetModifier
        ) {
            MyAsyncImage(
                model = data.icon,
                modifier = Modifier.fillMaxSize(),
            )

            //region 点击数
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = SpaceSmall, end = SpaceSmall)
            ) {
                Icon(
                    imageVector = Icons.Default.Headphones,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(13.dp),
                )

                SpaceExtraSmall2Width()

                Text(
                    text = StringUtil.formatCount(data.clicksCount),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontSize = 12.sp
                    ),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
            //endregion

        }
        Column(
            modifier = Modifier.padding(
                SpaceMedium
            )
        ) {
            Text(
                text = data.title,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun ItemProductPreview(): Unit {
    WangyiyunTheme {
        ItemSheetGrid(
            data = Sheet(
                id = "10",
                created = "2200-04-10T00:20:49.000Z",
                updated = "2024-03-15T04:06:54.000Z",
                title = "你开始懂得了歌词200",
                icon = "assets/2020gd.jpeg",
                detail = "这是歌单描述",
                clicksCount = 877,
                collectsCount = 1,
                commentsCount = 0,
                songsCount = 0,
                user = User(
                    id = "1",
                    nickname = "用户1",
                    icon = "http://thirdqq.qlogo.cn/qqopen/vYLjenVtwibnEbh6glpMFmRRN8MTCibicnh7LKyW5uKHBLdkicGtQ9u857tvibutVYozv/100?v=564a",
                )
            ),
        )
    }
}