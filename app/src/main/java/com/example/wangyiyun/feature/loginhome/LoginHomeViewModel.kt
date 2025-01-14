package com.example.wangyiyun.feature.loginhome

import com.example.wangyiyun.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginHomeViewModel @Inject constructor(

): BaseViewModel() {
    val uiState: MutableStateFlow<LoginHomeUiState> =
        MutableStateFlow<LoginHomeUiState>(LoginHomeUiState.None)
}

