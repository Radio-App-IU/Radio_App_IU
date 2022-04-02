package com.example.radio_app_iu

//Just a test activity for testing the outputs of set data before integration with radioHost activity
//Will be deleted at final product!


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
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
        var radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size

        val currentRadioHost = RadioStation().stubGetCurrentRadioHost()
        val username = intent.getStringExtra("username")
        binding.textView4.setText(username)

        //Handler with Looper
        val handling = Handler(Looper.getMainLooper())

        //creating Runnable object
        val event = object : Runnable {
            override fun run() {

                    //if logged in radio host is current radio host:
                    if(username == currentRadioHost && StubEvaluationDB.radioHostEvaluationList.size > 0/*'radioHostEvaluationCounter*/){
                        Toast.makeText(applicationContext, "$username, du hast eine neue Bewertung erhalten!", Toast.LENGTH_SHORT).show()
                        radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
                    }
                handling.postDelayed(this, 5000L)
            }
        }

        //calling the Handler with Looper at onCreate
        handling.postDelayed(event, 0L)


        binding.button.setOnClickListener {
            binding.textView5.setText(username)
        }
    }


    fun savePlaylistEvaluationInList(){
        if (StubEvaluationDB.playlistEvaluationList.isNotEmpty()){
            val string = "ID: " + StubEvaluationDB.playlistEvaluationList[0].returnId().toString() + "\n" + StubEvaluationDB.playlistEvaluationList[0].returnPlaylistEvaluation() +
                    "\n" + "Nickname: " + StubEvaluationDB.playlistEvaluationList[0].returnNickname() + "\n" + "Rating: " + StubEvaluationDB.playlistEvaluationList[0].playlistRating.toString() +
                    "\n" + "Zeit: " + StubEvaluationDB.playlistEvaluationList[0].returnTimestamp()
            binding.textView.setText(string)
            }
        else  binding.textView.setText("Playlist ohne Bewertungen")
    }

    fun saveRadioHostEvaluationInList(){
        if(StubEvaluationDB.radioHostEvaluationList.isNotEmpty()){
            val string = "ID: " + StubEvaluationDB.radioHostEvaluationList[0].returnId().toString() + "\n" + StubEvaluationDB.radioHostEvaluationList[0].returnRadioHostEvaluation() +
                    "\n" + "Nickname: " + StubEvaluationDB.radioHostEvaluationList[0].returnNickname() + "\n" + "Rating: " + StubEvaluationDB.radioHostEvaluationList[0].radioHostRating.toString() +
                    "\n" + "Zeit: " + StubEvaluationDB.radioHostEvaluationList[0].returnTimestamp()
            binding.textView2.setText(string)
        }
        else binding.textView2.setText("Moderator ohne Bewertung")
    }

    fun saveWishSongInList() {
        if (StubEvaluationDB.wishSongList.isNotEmpty()){
        val string = "ID: " + StubEvaluationDB.wishSongList[0].returnWishSongId().toString() + "\n" +StubEvaluationDB.wishSongList[0].returnWishedSong() + "\n" +
                "Nickname: " + StubEvaluationDB.wishSongList[0].returnNickname() + "\n" + "Zeit: " + StubEvaluationDB.wishSongList[0].returnTimestamp()
        binding.textView3.setText(string)
        }
    else  binding.textView3.setText("Keine Wunschsongs")
        }
}