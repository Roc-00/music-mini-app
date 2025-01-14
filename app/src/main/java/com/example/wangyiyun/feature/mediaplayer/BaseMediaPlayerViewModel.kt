package com.example.wangyiyun.feature.mediaplayer

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.MimeTypes
import com.example.wangyiyun.core.data.repository.SongRepository
import com.example.wangyiyun.core.data.repository.UserDataRepository
import com.example.wangyiyun.core.media.MediaServiceConnection
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.model.from
import com.example.wangyiyun.feature.base.BaseViewModel
import com.example.wangyiyun.util.ResourceUtil
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

open class BaseMediaPlayerViewModel (
    protected val mediaServiceConnection:MediaServiceConnection,
    protected val songRepository: SongRepository,
    protected val userDataRepository: UserDataRepository,
): BaseViewModel(){
    val toMusicPlayer = mutableStateOf<Boolean>(false)

    val nowPlaying = mediaServiceConnection.nowPlaying
    val playbackState = mediaServiceConnection.playbackState
    val currentPosition = mediaServiceConnection.currentPosition.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0,
    )
//    val playRepeatMode = userDataRepository.userData.map { it.playRepeatMode }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000),
//        initialValue = PlaybackMode.REPEAT_LIST,
//    )

//    val datum = songRepository.getAllPlayList().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000),
//        initialValue = emptyList(),
//    )

    public fun setMediasAndPlay(
        datum: List<Song>,
        index: Int,
        navigateToMusicPlayer: Boolean = false,
    ) {
        viewModelScope.launch {
            //将原来播放列表所有音乐playlist该为false
            songRepository.clearAllPlayList()

            val songs = datum.mapIndexed { index, song ->
                song.copy(
                    totalTrackCount = datum.size,
                    trackNumber = index,
                )
            }

            //保存播放列表
            songRepository.insertList(songs.map {
                it.toSongEntity()
            })

            //转为MediaItem
            val mediaItems = datum.map {
                MediaItem.Builder()
                    .apply {
                        setMediaId(it.id)
                        setUri(ResourceUtil.r2(it.uri))
                        setMimeType(MimeTypes.AUDIO_MPEG)
                        setMediaMetadata(
                            MediaMetadata.Builder()
                                .from(it)
                                .apply {
                                    setArtworkUri(Uri.parse(ResourceUtil.r2(it.icon))) // Used by ExoPlayer and Notification
                                    // Keep the original artwork URI for being included in Cast metadata object.
//                                        val extras = Bundle()
//                                        extras.putString(ORIGINAL_ARTWORK_URI_KEY, it.image)
//                                        setExtras(extras)
                                }
                                .build()
                        )
                    }.build()
            }.toList()

            mediaServiceConnection.setMediasAndPlay(mediaItems, index)

            toMusicPlayer.value = navigateToMusicPlayer
        }
    }

    fun clearMusicPlayer(): Unit {
        toMusicPlayer.value = false
    }

    fun onSeek(data: Float) {
        mediaServiceConnection.seekTo(data.toLong())
    }

    fun onPreviousClick() {
        mediaServiceConnection.seekToPrevious()
    }

    fun onNextClick() {
        mediaServiceConnection.seekToNext()
    }

    fun onPlayOrPauseClick() {
        mediaServiceConnection.playOrPause()
    }

    fun onChangeRepeatModeClick() {
        viewModelScope.launch {
//            var playRepeatMode = userDataRepository.userData.first().playRepeatMode.ordinal
//            playRepeatMode++
//            if (playRepeatMode > PlaybackMode.REPEAT_SHUFFLE.ordinal) {
//                playRepeatMode = PlaybackMode.REPEAT_LIST.ordinal
//            }
//            userDataRepository.setRepeatModel(
//                PlaybackModePreferences.forNumber(playRepeatMode)
//            )

//            mediaServiceConnection.setRepeatMode(playRepeatMode)
        }
    }
}