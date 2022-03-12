package com.example.radio_app_iu

import kotlin.random.Random

//singleton
object Playlist {

    var song1 : Song = Song("Let it be", "The Beatles", "Let it be", R.raw.thebeatlesletitbe)
    var song2 : Song = Song("Perfect", "Ed Sheeran", "Divide", R.raw.edsheeranperfect)

    var playlist : List<Song> = listOf(song1, song2)
    var randomSong : Song = playlist[Random.nextInt(0, playlist.size)]

    fun getSong() : String {
        return this.randomSong.getTitleAndInterpret()
    }

    fun getSongFile() : Int {
        return this.randomSong.getCurrentAudioFile()
    }
}