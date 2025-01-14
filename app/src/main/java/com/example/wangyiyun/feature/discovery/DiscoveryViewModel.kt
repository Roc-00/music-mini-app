package com.example.wangyiyun.feature.discovery

import androidx.lifecycle.viewModelScope
import com.example.wangyiyun.core.data.repository.CommonRepository
import com.example.wangyiyun.core.data.repository.SongRepository
import com.example.wangyiyun.core.data.repository.UserDataRepository
import com.example.wangyiyun.core.media.MediaServiceConnection
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.model.ViewData
import com.example.wangyiyun.feature.mediaplayer.BaseMediaPlayerViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 发现界面VM
 */
@HiltViewModel
class DiscoveryViewModel @Inject constructor(
    private val commonRepository: CommonRepository,
    songRepository: SongRepository,
    mediaServiceConnection: MediaServiceConnection,
    userDataRepository: UserDataRepository,
): BaseMediaPlayerViewModel(
    mediaServiceConnection,
    songRepository,
    userDataRepository,
) {
    private val _topdatum = MutableStateFlow<List<ViewData>>(emptyList()) // 这个是DiscoveryViewModel内部的，只有内部逻辑可以修改

    val topdatum: StateFlow<List<ViewData>> = _topdatum //这个是公开的，StateFlow会时刻保存对_datum的监听，暴露最新值，外界监听datum获取最新值

    init {
        loadData()
    }

    private fun loadData(){
//        _topdatum.value = FakeDatas.SONGS

        //测试序列化
//        val json = Json.encodeToString(FakeDatas.SONG)
//        Log.d(TAG, "loadData: $json")
//
//        var obj = Json.decodeFromString<Song>(json)
//        Log.d(TAG, "loadData: $obj")

        //测试网络请求
        viewModelScope.launch {
//            val songs = MyRetrofitDatasource.songs()
//            _datum.value = songs.data?.list ?: emptyList()
            val indexes = commonRepository.indexes(app = 30)
            _topdatum.value = indexes.data?.list?: emptyList()
        }
    }

    fun onSongClick(datum: List<Song>, index: Int) {
        setMediasAndPlay(
            datum,
            index,
            true
        )
    }

    companion object{
        const val TAG = "DiscoveryViewModel"
    }
}