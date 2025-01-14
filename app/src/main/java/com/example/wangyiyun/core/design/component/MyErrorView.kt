package com.example.wangyiyun.core.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.theme.Space3XLargeHeight
import com.example.wangyiyun.core.design.theme.SpaceOuter
import com.example.wangyiyun.core.exception.CommonException
import com.example.wangyiyun.core.extension.clickableNoRipple

/**
 * 错误视图
 */
@Composable
fun MyErrorView(
    exception: CommonException,
    retryButtonText: Int = R.string.click_retry,
    onRetryClick: () -> Unit = {},
    modifier: Modifier = Modifier,
): Unit {
    MyErrorView(
        message = exception.tipString!!,
        icon = exception.tipIcon ?: R.drawable.bg_empty,
        retryButtonText = retryButtonText,
        onRetryClick = onRetryClick,
        modifier = modifier,
    )
}

@Composable
fun MyErrorView(
    message: String = stringResource(id = R.string.error_load),
    icon: Int = R.drawable.bg_error,
    retryButtonText: Int = R.string.click_retry,
    onRetryClick: () -> Unit = {},
    modifier: Modifier = Modifier,
): Unit {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .clickableNoRipple {
                onRetryClick()
            }
            .padding(SpaceOuter)
    ) {
        Image(
            modifier = Modifier.size(300.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
        )

        Space3XLargeHeight()

        Text(
            text = message,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline,
        )

        Space3XLargeHeight()

        // OutlinedButton 是 Jetpack Compose 中提供的一个按钮组件，它用于创建带有边框的按钮。
        // 与 Button 组件不同的是，OutlinedButton 默认情况下没有背景填充颜色，只有边框和文本，这使得它适合用于需要更轻量级视觉效果的场景。
        OutlinedButton(
            onClick = onRetryClick,
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outline), // 设置边框颜色
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .widthIn(200.dp)
        ) {
            Text(
                text = stringResource(id = retryButtonText),
            )
        }
    }
}