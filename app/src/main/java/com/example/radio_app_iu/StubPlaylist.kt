package com.example.radio_app_iu

import kotlin.random.Random

//singleton
object StubPlaylist {

    //creation of song instances
    private var song1 : Song = Song("Let it be", "The Beatles", "Let it be", R.raw.thebeatlesletitbe, R.drawable.coverletitbe)
    private var song2 : Song = Song("Perfect", "Ed Sheeran", "Divide", R.raw.edsheeranperfect, R.drawable.coverdivide)

    //creation of list of songs
    private var playlist : List<Song> = listOf(song1, song2)

    //drawing a random song of the playlist
    private var randomSong : Song = playlist[Random.nextInt(0, playlist.size)]

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