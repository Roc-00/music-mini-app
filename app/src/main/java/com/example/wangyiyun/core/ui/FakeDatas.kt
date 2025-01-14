package com.example.wangyiyun.core.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.wangyiyun.core.model.Song
import com.example.wangyiyun.core.ui.FakeDatas.SONGS
import com.example.wangyiyun.util.Constant

/**
 * 假数据，只是为了在没网的情况下测试页面
 */
class FakeDatasProvider :PreviewParameterProvider<List<Song>>{
    override val values: Sequence<List<Song>> = sequenceOf(SONGS)
}

object FakeDatas {
    val SONG = Song( // 单个数据
        id = "1",
        title = "爱的代价",
        uri = "assets/s2.mp3",
        icon = "assets/s2.jpg",
        album = "不舍",
        artist = "李宗盛",
        genre = "流行",
        lyricStyle = Constant.VALUE0,
        lyric = "",
        trackNumber = 1,
        totalTrackCount = 2,
    )

    val SONGS = listOf( // 多个数据
        SONG,
        Song(
            id = "2",
            title = "爱拼才会赢",
            uri = "assets/s1.mp3",
            icon = "assets/s1.jpg",
            album = "唱遍市场",
            artist = "叶启田",
            genre = "流行",
            lyricStyle = Constant.VALUE10,
            lyric = "",
            trackNumber = 2,
            totalTrackCount = 2,
        ),
        SONG,
        Song(
            id = "2",
            title = "爱拼才会赢",
            uri = "assets/s1.mp3",
            icon = "assets/s1.jpg",
            album = "唱遍市场",
            artist = "叶启田",
            genre = "流行",
            lyricStyle = Constant.VALUE10,
            lyric = "",
            trackNumber = 2,
            totalTrackCount = 2,
        ),
        SONG,
        Song(
            id = "2",
            title = "爱拼才会赢",
            uri = "assets/s1.mp3",
            icon = "assets/s1.jpg",
            album = "唱遍市场",
            artist = "叶启田",
            genre = "流行",
            lyricStyle = Constant.VALUE10,
            lyric = "",
            trackNumber = 2,
            totalTrackCount = 2,
        ),
        SONG,
        Song(
            id = "2",
            title = "爱拼才会赢",
            uri = "assets/s1.mp3",
            icon = "assets/s1.jpg",
            album = "唱遍市场",
            artist = "叶启田",
            genre = "流行",
            lyricStyle = Constant.VALUE10,
            lyric = "",
            trackNumber = 2,
            totalTrackCount = 2,
        ),
        SONG,
        Song(
            id = "2",
            title = "爱拼才会赢",
            uri = "assets/s1.mp3",
            icon = "assets/s1.jpg",
            album = "唱遍市场",
            artist = "叶启田",
            genre = "流行",
            lyricStyle = Constant.VALUE10,
            lyric = "",
            trackNumber = 2,
            totalTrackCount = 2,
        ),
        SONG,
        Song(
            id = "2",
            title = "爱拼才会赢",
            uri = "assets/s1.mp3",
            icon = "assets/s1.jpg",
            album = "唱遍市场",
            artist = "叶启田",
            genre = "流行",
            lyricStyle = Constant.VALUE10,
            lyric = "",
            trackNumber = 2,
            totalTrackCount = 2,
        ),
    )
}