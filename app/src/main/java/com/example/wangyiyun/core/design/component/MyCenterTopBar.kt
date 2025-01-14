package com.example.wangyiyun.core.design.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

/**
 * 居中的TopAppBar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCenterTopAppBar(
    titleText: String = "",
    title: (@Composable () -> Unit)? = null,
    colors: TopAppBarColors? = null,
    actions: @Composable (RowScope.() -> kotlin.Unit) = {},
    finishPage: (() -> Unit)? = null,
    navigationIcon: @Composable () -> Unit = {},
){
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (finishPage != null) {//如果返回页存在
                IconButton(onClick = finishPage) {//则显示一个返回箭头的 IconButton，点击该按钮时会触发 finishPage 回调。
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = null
                    )
                }
            } else {//如果返回页不存在，navigationIcon() 是一个预定义的方法或组合函数，用来提供默认的导航图标或行为
                navigationIcon()
            }
        },
        title = {
            Text(
                text = titleText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = actions,//参数允许你在应用栏的右侧添加额外的操作按钮。这个参数应该接收一个 lambda 表达式，该表达式包含零个或多个可组合的内容。例如，你可以在这里放置菜单按钮、搜索图标等。
        colors = colors ?: TopAppBarDefaults.centerAlignedTopAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    )
}