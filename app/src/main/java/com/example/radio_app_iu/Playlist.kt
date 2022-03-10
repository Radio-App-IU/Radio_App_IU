package com.example.radio_app_iu

import kotlin.random.Random

//singleton
object Playlist {

    var song1 : Song = Song("Let it be", "The Beatles", "Let it be")
    var song2 : Song = Song("Perfect", "Ed Sheeran", "Divide")

    var playlist : List<Song> = listOf(song1, song2)

    fun stubPlayPlaylist() : String {
        return this.playlist[Random.nextInt(0, playlist.size)].getTitleAndInterpret()
    }
}