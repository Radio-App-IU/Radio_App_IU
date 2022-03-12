package com.example.radio_app_iu

import kotlin.random.Random

//singleton
object Playlist {

    //creation of song instances
    var song1 : Song = Song("Let it be", "The Beatles", "Let it be", R.raw.thebeatlesletitbe, R.drawable.coverletitbe)
    var song2 : Song = Song("Perfect", "Ed Sheeran", "Divide", R.raw.edsheeranperfect, R.drawable.coverdivide)

    //creation of list of songs
    var playlist : List<Song> = listOf(song1, song2)

    //drawing a random song of the playlist
    var randomSong : Song = playlist[Random.nextInt(0, playlist.size)]

    //getting String of a song's title and interpret
    fun getSong() : String {
        return this.randomSong.getTitleAndInterpret()
    }

    //getting the current song's audio resource
    fun getSongFile() : Int {
        return this.randomSong.getCurrentAudioFile()
    }

    //getting the current song's album image resource
    fun getAlbumCover() : Int {
        return this.randomSong.getCurrentAlbumImage()
    }
}