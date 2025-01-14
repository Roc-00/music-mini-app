package com.example.wangyiyun.feature.loginhome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wangyiyun.R
import com.example.wangyiyun.core.design.theme.Space4XLarge
import com.example.wangyiyun.core.design.theme.SpaceLarge
import com.example.wangyiyun.core.design.theme.WangyiyunTheme


@Composable
fun LoginHomeRoute(
    finishPage: () -> Unit,
    toLogin: () -> Unit,
    toCodeLogin: () -> Unit,
    finishAllLoginPages: () -> Unit,
    viewModel: LoginHomeViewModel = hiltViewModel()
) {
    val loginHomeUiState by viewModel.uiState.collectAsState()

    LoginHomeScreen(
        finishPage = finishPage,
        toLogin = toLogin,
        toCodeLogin = toCodeLogin,
//        toWebPage = toWebPage,
    )

    val isSuccess = loginHomeUiState is LoginHomeUiState.Success
    LaunchedEffect(key1 = isSuccess) {
        if (isSuccess) {
            finishAllLoginPages()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginHomeScreen(
    finishPage: () -> Unit = {},
    toLogin: () -> Unit = {},
    toCodeLogin: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = finishPage) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                },
                title = {

                },
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 60.dp)
                    .size(100.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
            )

            //底部容器
            BottomView(
                toLogin = toLogin,
                toCodeLogin = toCodeLogin,
//                toWebPage = toWebPage,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = SpaceLarge, end = SpaceLarge,
                        bottom = 150.dp
                    )
            )
        }
    }
}

@Composable
fun BottomView(
    toLogin: () -> Unit = {},
    toCodeLogin: () -> Unit = {},
    finishAllLoginPages: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = toCodeLogin,
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.code_login))
        }

        OutlinedButton(
            onClick = toLogin,
            modifier = Modifier
                .padding(top = Space4XLarge)
                .height(48.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.username_login))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginHomeRoutePreview(): Unit {
    WangyiyunTheme {
        LoginHomeScreen(
        )
    }
}