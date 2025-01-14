package com.example.wangyiyun.feature.sheetdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.wangyiyun.core.data.repository.SheetRepository
import com.example.wangyiyun.core.data.repository.SongRepository
import com.example.wangyiyun.core.data.repository.UserDataRepository
import com.example.wangyiyun.core.exception.localException
import com.example.wangyiyun.core.media.MediaServiceConnection
import com.example.wangyiyun.core.model.Sheet
import com.example.wangyiyun.core.result.asResult
import com.example.wangyiyun.feature.mediaplayer.BaseMediaPlayerViewModel
import com.quick.app.feature.sheetdetail.SHEET_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 歌单详情vm
 */
@HiltViewModel
class SheetDetailViewModel @Inject constructor(
    saveStateHandle: SavedStateHandle,
    private var sheetRepository:SheetRepository,
    mediaServiceConnection: MediaServiceConnection,
    songRepository: SongRepository,
    userDataRepository: UserDataRepository,
) : BaseMediaPlayerViewModel(
    mediaServiceConnection,
    songRepository,
    userDataRepository,
) {
    val sheetId: String = checkNotNull(saveStateHandle[SHEET_ID])

    private val _uiState =
        MutableStateFlow<SheetDetailUiState>(SheetDetailUiState.Loading) // 这个是DiscoveryViewModel内部的，只有内部逻辑可以修改

    val uiState: StateFlow<SheetDetailUiState> =
        _uiState //这个是公开的，StateFlow会时刻保存对_datum的监听，暴露最新值，外界监听datum获取最新值

    private lateinit var data: Sheet

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            sheetRepository.sheetDetail(sheetId)
                .asResult()
                .collectLatest { res ->
                    if (res.isSuccess){
                        data = res.getOrThrow().data!!
                        _uiState.value = SheetDetailUiState.Success(res.getOrThrow().data!!)
                    }else{
                        _uiState.value = SheetDetailUiState.Error(res.exceptionOrNull()!!.localException())
                    }
                }
        }


        //加了sheetRepository后
//        viewModelScope.launch {
//            try {
//                sheetRepository.sheetDetail(sheetId)
//                    .collectLatest { res ->
//                        if (res.status == 0){
//                            _data.value = SheetDetailUiState.Success(res.data?: SHEET_EMPTY())
//                        }else{
//                            _data.value = SheetDetailUiState.Error(res.message ?: "请求失败")
//                        }
//                    }
//            } catch (e: Exception) {
//                TODO("Not yet implemented")
//            }
//        }


        //加了sheetRepository前
//        //测试网络请求
//        viewModelScope.launch {
//            try {
//                val res = MyRetrofitDatasource.sheetDetail(sheetId)
//                if (res.status == 0){
//                    _data.value = SheetDetailUiState.Success(res.data?: SHEET_EMPTY())
//                }else{
//                    _data.value = SheetDetailUiState.Error(res.message ?: "请求失败")
//                }
//            } catch (e: Exception) {
//                _data.value = SheetDetailUiState.Error(e.localizedMessage)
//            }
//
//            Log.d(TAG, "loadData: ")
//        }
    }

    fun onRetryClick() {
        loadData()
    }

    fun onSongClick(index: Int) {
        setMediasAndPlay(data.songs!!, index, true)
    }

    companion object {
        const val TAG = "SheetDetailViewModel"
    }
}