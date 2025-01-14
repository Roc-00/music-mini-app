package com.example.wangyiyun.core.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.theme.Space3XLargeHeight
import com.example.wangyiyun.core.design.theme.SpaceOuter
import com.example.wangyiyun.core.design.theme.WangyiyunTheme
import com.example.wangyiyun.core.design.theme.bodyXLarge
import com.example.wangyiyun.core.extension.clickableNoRipple

/**
 * 未开发视图
 */
@Composable
fun MyEmptyView(
    modifier: Modifier = Modifier,
): Unit {
    MyEmptyScreen(
        modifier = modifier,
    )
}

@Composable
fun MyEmptyScreen(
    modifier: Modifier = Modifier,
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .clickableNoRipple {
            }
            .padding(SpaceOuter)
    ) {
        Image(
            modifier = Modifier.size(300.dp),
            painter = painterResource(id = R.drawable.bg_empty),
            contentDescription = null,
        )

        Space3XLargeHeight()

        Text(
            text = stringResource(id = R.string.unfinished_page),
            style = MaterialTheme.typography.bodyXLarge,
            color = MaterialTheme.colorScheme.outline,
        )

        Space3XLargeHeight()

    }
}

@Preview(showBackground = true)
@Composable
fun MyEmptyScreenPreview(){
    WangyiyunTheme {
        MyEmptyScreen()
    }
}