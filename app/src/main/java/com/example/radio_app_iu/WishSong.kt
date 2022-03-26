package com.example.radio_app_iu

class WishSong (var idWishSong : Int = 0, var song : String = "", var nickname : String = "", var wishsongTimestamp : String) {

    fun returnWishSongId() : Int {
        return this.idWishSong
    }

    fun returnWishedSong() : String {
        return this.song
    }

    fun returnNickname() : String {
        return this.nickname
    }

    fun returnTimestamp() : String {
        return this.wishsongTimestamp
    }
}