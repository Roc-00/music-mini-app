package com.example.wangyiyun.feature.main

import androidx.lifecycle.viewModelScope
import com.example.wangyiyun.core.data.repository.SongRepository
import com.example.wangyiyun.core.data.repository.UserDataRepository
import com.example.wangyiyun.core.media.MediaServiceConnection
import com.example.wangyiyun.feature.mediaplayer.BaseMediaPlayerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    songRepository: SongRepository,
    mediaServiceConnection: MediaServiceConnection,
    userDataRepository: UserDataRepository,
//    lyricManager: LyricManager,
) : BaseMediaPlayerViewModel(
    mediaServiceConnection,
    songRepository,
    userDataRepository,
//    lyricManager,
) {
    val isShowConfirmLogoutDialog = MutableStateFlow<Boolean>(false)

    fun dismissConfirmLogoutDialog() {
        isShowConfirmLogoutDialog.value = false
    }

    fun showConfirmLogoutDialog() {
        isShowConfirmLogoutDialog.value = true
    }

//    fun onLogoutClick() {
//        MyApplication.instance.logout()
//    }
    val datum = songRepository.getAllPlayList().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )
}