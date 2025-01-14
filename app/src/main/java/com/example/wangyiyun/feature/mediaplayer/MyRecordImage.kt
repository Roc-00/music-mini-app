package com.example.wangyiyun.feature.mediaplayer

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.wangyiyun.R
import com.example.wangyiyun.util.ResourceUtil
import com.google.common.base.Strings

@Composable
fun MyRecordImage(icon: String, modifier: Modifier = Modifier) {
    if (Strings.isNullOrEmpty(icon)) {
        Image(
            painter = painterResource(id = R.drawable.default_music_cover),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
    } else {
        AsyncImage(
            model = ResourceUtil.r2(icon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
    }
}