package com.example.wangyiyun.core.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wangyiyun.core.design.theme.SpaceExtraMedium
import com.example.wangyiyun.core.design.theme.SpaceSmallHeight
import com.example.wangyiyun.core.extension.clickableNoRipple
import com.example.wangyiyun.feature.main.TopLevelDestination

/**
 * 底部导航
 */
@Composable
fun MyNavigationBar(
    // 底部导航栏
    destination: List<TopLevelDestination>, // 枚举类，有多少个页面
    currentDestination: String, // 当前页面
    onNavigateToDestination: (Int) -> Unit, // 回调函数，这里只负责展示，具体执行回调
    modifier: Modifier = Modifier, // 设置样式
): Unit {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .navigationBarsPadding(),//底部padding，防止和返回键重合
    ) {
        destination.forEachIndexed { index, destination ->
            val selected = destination.route == currentDestination  // 判断当前页面是否是选中页面
            val color = if (selected) // 字体颜色
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = SpaceExtraMedium)
                    .clickableNoRipple {
                        onNavigateToDestination(index)
                    }
            ) {
                Image(
                    painter = painterResource(
                        id =
                        if (selected)
                            destination.selectedIcon
                        else
                            destination.unselectedIcon
                    ),
                    contentDescription = stringResource(id = destination.titleTextId),
                    modifier = Modifier.size(25.dp)
                )

                SpaceSmallHeight()

                Text(
                    text = stringResource(id = destination.titleTextId),
                    style = MaterialTheme.typography.bodySmall,
                    color = color
                )
            }
        }
    }
}