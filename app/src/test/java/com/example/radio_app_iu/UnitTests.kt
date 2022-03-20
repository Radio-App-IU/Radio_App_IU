package com.example.radio_app_iu

import org.junit.Test

import org.junit.Assert.*

var stubServerLoginCheck = StubServerLoginCheck()

class UnitTests {
    @Test
    fun checkCorrectLogin() {
        assertTrue("valid login", stubServerLoginCheck.checkLogin("Moderator2", "xyz"))
    }

    @Test
    fun checkIncorrectLogin() {
        assertFalse("invalid login", stubServerLoginCheck.checkLogin("Moderator1", "xyz"))
    }

    @Test
    fun getSongTitle(){
        var title = "Titel: "
        assertTrue("got Song back", StubPlaylist.getSongTitle() == (title + "Let it be") || StubPlaylist.getSongTitle() == (title + "Perfect"))
    }

    @Test
    fun getSongTitleInterpret(){
        assertTrue("title and interpret received", StubPlaylist.getSong() == "Let it be" + "    -    " + "The Beatles" ||
                StubPlaylist.getSong() == "Perfect" + "    -    " + "Ed Sheeran")
    }

    @Test
    fun getSongFile(){
        assertTrue("SongFile received", StubPlaylist.getSongFile() == R.raw.thebeatlesletitbe || StubPlaylist.getSongFile() == R.raw.edsheeranperfect)
    }

    @Test
    fun getAlbumCover(){
        assertTrue("Cover received", StubPlaylist.getAlbumCover() == R.drawable.coverletitbe || StubPlaylist.getAlbumCover() == R.drawable.coverdivide)
    }

    @Test
    fun getInterpret(){
        assertTrue("Interpret received", StubPlaylist.getSongInterpret() == "Interpret: The Beatles" || StubPlaylist.getSongInterpret() == "Interpret: Ed Sheeran")
    }

    @Test
    fun getAlbumName(){
        assertTrue("Albumname received", StubPlaylist.getAlbumName() == "Album: Let it be" || StubPlaylist.getAlbumName() == "Album: Divide")
    }

    @Test
    fun getSongLength(){
        assertTrue("Length received", StubPlaylist.getSongLength() == ("Länge: 8:32 min") || StubPlaylist.getSongLength() == ("Länge: 4:29 min"))
    }

    @Test
    fun getSongYear(){
        assertTrue("Year received", StubPlaylist.getSongYear() == ("Jahr: 1970") || StubPlaylist.getSongYear() == ("Jahr: 2017"))
    }
}