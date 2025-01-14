package com.example.wangyiyun.core.design.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.theme.LocalDividerColor
import com.example.wangyiyun.util.ResourceUtil

@Composable
fun MyAsyncImage(
    model:String?,
    modifier: Modifier = Modifier
): Unit {
    if(model == null)
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(MaterialTheme.shapes.small)
                .background(LocalDividerColor.current)
        )
    else
        AsyncImage(
            model = ResourceUtil.r2(model),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(MaterialTheme.shapes.small)
                .background(LocalDividerColor.current)
        )
}