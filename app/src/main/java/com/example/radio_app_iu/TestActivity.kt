package com.example.radio_app_iu

//Just a test activity for testing the outputs of set data before integration with radioHost activity
//Will be deleted at final product!


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.radio_app_iu.databinding.ActivityTestBinding

private lateinit var binding: ActivityTestBinding

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savePlaylistEvaluationInList()
        saveRadioHostEvaluationInList()
        saveWishSongInList()
        }

    fun savePlaylistEvaluationInList(){
        if (StubEvaluationDB.playlistEvaluationList.isNotEmpty()){
            val string = "ID: " + StubEvaluationDB.playlistEvaluationList[0].returnId().toString() + "\n" + StubEvaluationDB.playlistEvaluationList[0].returnPlaylistEvaluation() +
                    "\n" + "Nickname: " + StubEvaluationDB.playlistEvaluationList[0].returnNickname() + "\n" + "Rating: " + StubEvaluationDB.playlistEvaluationList[0].playlistRating.toString()
            binding.textView4.setText(string)
            }
        else  binding.textView4.setText("Playlist ohne Bewertungen")
    }

    fun saveRadioHostEvaluationInList(){
        if(StubEvaluationDB.radioHostEvaluationList.isNotEmpty()){
            val string = "ID: " + StubEvaluationDB.radioHostEvaluationList[0].returnId().toString() + "\n" + StubEvaluationDB.radioHostEvaluationList[0].returnRadioHostEvaluation() +
                    "\n" + "Nickname: " + StubEvaluationDB.radioHostEvaluationList[0].returnNickname() + "\n" + "Rating: " + StubEvaluationDB.radioHostEvaluationList[0].radioHostRating.toString()
            binding.textView2.setText(string)
        }
        else binding.textView2.setText("Moderator ohne Bewertung")
    }

    fun saveWishSongInList() {
        if (StubEvaluationDB.wishSongList.isNotEmpty()){
        val string = "ID: " + StubEvaluationDB.wishSongList[0].returnWishSongId().toString() + "\n" +StubEvaluationDB.wishSongList[0].returnWishedSong() + "\n" + "Nickname: " + StubEvaluationDB.wishSongList[0].returnNickname()
        binding.textView3.setText(string)
        }
    else  binding.textView3.setText("Keine Wunschsongs")
        }
}