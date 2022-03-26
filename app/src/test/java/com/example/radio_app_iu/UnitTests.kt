package com.example.radio_app_iu


import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UnitTests {

    private var stubServerLoginCheck = StubServerLoginCheck()
    private var radioHostEvaluation = RadioHostEvaluation (11, "eineBewertung", "einNick", 1, "einModerator", "")
    private var playlistEvaluation = PlaylistEvaluation (1, "eineBewertung", "einNick", 5, "")
    private var wishSong = WishSong (2, "einSong", "einNick", "")
    private var song = Song("Titel", "Interpret", "Album", 66, 77, "2000", "12min")
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("d.M.y H:m:ss")

    // unit tests

    @Test
    fun checkCorrectLogin() {
        assertTrue("valid login", stubServerLoginCheck.checkLogin("Moderator2", "xyz"))
    }

    @Test
    fun checkIncorrectLogin() {
        assertFalse("invalid login", stubServerLoginCheck.checkLogin("Moderator1", "xyz"))
    }

    @Test
    fun getSongTitle() {
        val title = "Titel: "
        assertTrue("got Song back", StubPlaylist.getSongTitle() == (title + "Let it be") || StubPlaylist.getSongTitle() == (title + "Perfect"))
    }

    @Test
    fun getInterpret() {
        assertTrue("Interpret received", StubPlaylist.getSongInterpret() == "Interpret: The Beatles" || StubPlaylist.getSongInterpret() == "Interpret: Ed Sheeran")
    }

    @Test
    fun getAlbumName() {
        assertTrue("Albumname received", StubPlaylist.getAlbumName() == "Album: Let it be" || StubPlaylist.getAlbumName() == "Album: Divide")
    }

    @Test
    fun getSongLength() {
        assertTrue("Length received", StubPlaylist.getSongLength() == ("Länge: 8:32 min") || StubPlaylist.getSongLength() == ("Länge: 4:29 min"))
    }

    @Test
    fun getSongYear() {
        assertTrue("Year received", StubPlaylist.getSongYear() == ("Jahr: 1970") || StubPlaylist.getSongYear() == ("Jahr: 2017"))
    }

    @Test
    fun outputRadioHostEvaluation() {
    assertTrue("Data Output", radioHostEvaluation.returnId() == 11 && radioHostEvaluation.returnRadioHostEvaluation() == "eineBewertung" &&
            radioHostEvaluation.returnNickname() == "einNick" && radioHostEvaluation.returnRating() == 1 && radioHostEvaluation.moderator == "einModerator")
    }

    @Test
    fun outputPlaylistEvaluation(){
        assertTrue("Data Output", playlistEvaluation.returnId() == 1 && playlistEvaluation.returnPlaylistEvaluation() == "eineBewertung" &&
            playlistEvaluation.returnNickname() == "einNick" && playlistEvaluation.playlistRating == 5)
    }

    @Test
    fun outputWishSong() {
        assertTrue("Data Output", wishSong.returnWishSongId() == 2 && wishSong.returnWishedSong() == "einSong" && wishSong.returnNickname() == "einNick")
    }

    @Test
    fun toStringTest() {
        assertTrue("Title, Interpret, Album received", song.toString() == "Titel by Interpret from album: Album")
    }

    @Test
    fun getFileAndInterpret() {
        assertTrue("File and Interpret received", song.getTitleAndInterpret() == "Titel    -    Interpret")
    }

    @Test
    fun getCurrentAudioFile() {
        assertTrue("Audio File received", song.getCurrentAudioFile() == 66)
    }

    @Test
    fun getCurrentAlbumImage() {
        assertTrue("Album Image received", song.getCurrentAlbumImage() == 77)
    }

    @Test
    fun returnRadioHostTimestamp() {
        val time = LocalDateTime.now()
        val radioHostTimestamp = dateTimeFormatter.format(time).toString()
        val radHoEv = RadioHostEvaluation(0, "", "", 1, "", radioHostTimestamp)
        assertTrue("Local DateTime received", radHoEv.returnTimestamp() == dateTimeFormatter.format(LocalDateTime.now()).toString())
    }

    @Test
    fun returnPlaylistTimestamp() {
        val time = LocalDateTime.now()
        val playlistTimestamp = dateTimeFormatter.format(time).toString()
        val plLEv = PlaylistEvaluation(0, "", "", 1, playlistTimestamp)
        assertTrue("Local DateTime received", plLEv.returnTimestamp() == dateTimeFormatter.format(LocalDateTime.now()).toString())
    }

    @Test
    fun returnWishsongTimestamp() {
        val time = LocalDateTime.now()
        val wishSongTimestamp = dateTimeFormatter.format(time).toString()
        val wiS = WishSong(0, "", "", wishSongTimestamp)
        assertTrue("Local DateTime received", wiS.returnTimestamp() == dateTimeFormatter.format(LocalDateTime.now()).toString())
    }

    //Integration tests

    @Test
    fun getSongFile() {
        assertTrue("SongFile received", StubPlaylist.getSongFile() == R.raw.thebeatlesletitbe || StubPlaylist.getSongFile() == R.raw.edsheeranperfect)
    }

    @Test
    fun getAlbumCover() {
        assertTrue("Cover received", StubPlaylist.getAlbumCover() == R.drawable.coverletitbe || StubPlaylist.getAlbumCover() == R.drawable.coverdivide)
    }

    @Test
    fun getSongTitleInterpret() {
        assertTrue("title and interpret received", StubPlaylist.getSong() == "Let it be" + "    -    " + "The Beatles" ||
                StubPlaylist.getSong() == "Perfect" + "    -    " + "Ed Sheeran")
    }
}