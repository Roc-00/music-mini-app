package com.example.wangyiyun.feature.mediaplayer

import com.example.wangyiyun.core.data.repository.SongRepository
import com.example.wangyiyun.core.data.repository.UserDataRepository
import com.example.wangyiyun.core.media.MediaServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicPlayViewModel @Inject constructor(
    mediaServiceConnection: MediaServiceConnection,
    songRepository: SongRepository,
    userDataRepository: UserDataRepository,
//    lyricManager: LyricManager,
) : BaseMediaPlayerViewModel(
    mediaServiceConnection,
    songRepository,
    userDataRepository,
//    lyricManager
) {

}