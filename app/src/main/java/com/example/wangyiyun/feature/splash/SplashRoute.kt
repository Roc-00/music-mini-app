package com.example.wangyiyun.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.theme.SpaceExtraMedium
import com.example.wangyiyun.core.design.theme.SpaceLarge
import com.example.wangyiyun.core.design.theme.SpaceMedium
import com.example.wangyiyun.core.design.theme.WangyiyunTheme
import com.example.wangyiyun.core.design.theme.button_transparent_88
import com.example.wangyiyun.core.extension.clickableNoRipple
import com.example.wangyiyun.util.SuperDateUtil

@Composable
fun SplashRoute(
    toMain: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val timeLeft by viewModel.timeLeft.collectAsStateWithLifecycle()
    val navigateToMain by viewModel.navigateToMain.collectAsState()

    SplashScreen(
        year = SuperDateUtil.currentYear(),
        toMain = toMain,
        timeLeft = timeLeft,
    )

    if (navigateToMain) {
        LaunchedEffect(key1 = true) {
            toMain()
        }
    }
}

@Composable
fun SplashScreen(
    year: Int = 2024,
    toMain: () -> Unit = {},
    timeLeft: Long = 0,
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary))
    {
        //region 启动界面banner
        Image(
            painter = painterResource(id = R.drawable.splash_banner),
            contentDescription = null, // 给盲人描述的文本
            modifier = Modifier
                .padding(top = 150.dp)
                .align(Alignment.TopCenter)
        )
        //endregion

        Text(
            text = stringResource(id = R.string.skip_ad_count, timeLeft),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = SpaceLarge, top = 50.dp)
                .background(button_transparent_88, MaterialTheme.shapes.extraLarge)
                .clickableNoRipple {
                    toMain()
//                    Log.d("tiaoguo", "SplashScreen: dianle")
                }
                .padding(horizontal = SpaceMedium, vertical = SpaceExtraMedium)
        )


        //region 启动界面logo
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null, // 给盲人描述的文本
            modifier = Modifier
                .padding(bottom = 100.dp)
                .align(Alignment.BottomCenter)
                .clickable {
                    toMain()
                }
        )
        //endregion

        //模拟版权
        Text(
            text = stringResource(id = R.string.copyright, year),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 70.dp)
        )
    }
}

//预览
@Composable
@Preview(showBackground = true)
fun SplashRoutePreview():Unit{
    WangyiyunTheme{
        SplashScreen()
    }
}