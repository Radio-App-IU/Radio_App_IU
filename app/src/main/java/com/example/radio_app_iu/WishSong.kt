package com.example.radio_app_iu.com.example.radio_app_iu

class WishSong (var idWishSong : Int = 0, var song : String = "", var nickname : String = "") {

    fun returnWishSongId() : Int {
        return this.idWishSong
    }

    fun returnWishedSong() : String {
        return this.song
    }

    fun returnNickname() : String {
        return this.nickname
    }
}