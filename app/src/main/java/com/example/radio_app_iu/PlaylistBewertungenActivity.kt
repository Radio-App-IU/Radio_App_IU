package com.example.radio_app_iu


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.airbnb.paris.extensions.style
import com.example.radio_app_iu.databinding.ActivityPlaylistBewertungenBinding

private lateinit var binding: ActivityPlaylistBewertungenBinding
private var username = ""

class PlaylistBewertungenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistBewertungenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("username").toString()

        //calculate the average rating
        var summe = 0.0
        StubEvaluationDB.playlistEvaluationList.forEach { it ->
            summe += it.returnRating()
        }
        val durchschnitt = summe / (StubEvaluationDB.playlistEvaluationList.size)

        //function to add new evaluations to the view
        fun lesen() {
            if (StubEvaluationDB.playlistEvaluationList.isNotEmpty()) {
                binding.lyBewertungen.removeAllViews()
                StubEvaluationDB.playlistEvaluationList.forEach { it ->
                    val bewertung = it.playlistEvaluation
                    val nickname = it.playlistEvaluationNickname
                    val rating = it.playlistRating
                    val time = it.playlistTimestamp
                    val daten = "%s\n\n%s: %d/5\n%s".format(bewertung, nickname, rating, time)
                    val tvNeu = TextView(this)
                    binding.lyBewertungen.addView(tvNeu)
                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ) //Parameter der neuen Textview
                    tvNeu.style(R.style.Bewertungen)
                    tvNeu.layoutParams = lp
                    tvNeu.gravity = Gravity.CENTER_HORIZONTAL
                    tvNeu.text = daten
                }
            }
            else {
                binding.tvEintraegeAktualisieren.text = "Keine Bewertungen vorhanden\nBitte Aktualisieren"
            }
        }

        //update the evaluations and the average rating
        binding.Eintrag.setOnClickListener {
            lesen()
            if (durchschnitt.isNaN())
                else binding.tvBewertungenDurchschnitt.text = "Durchschnitts Rating: %.1f".format(durchschnitt)
        }

        binding.backbutton.setOnClickListener{
            finish()
        }

        handlingToast.postDelayed(eventToast, 0L)
    }


    //Handler with Looper
    val handlingToast = Handler(Looper.getMainLooper())

    //handler variables
    var radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
    var playlistEvaluationCounter = StubEvaluationDB.playlistEvaluationList.size
    var wishSongCounter = StubEvaluationDB.wishSongList.size
    val currentRadioHost = RadioStation().stubGetCurrentRadioHost()

    //creating Runnable Toast object
    val eventToast = object : Runnable {
        override fun run() {

            //if logged in radio host is current radio host:
            if (username == currentRadioHost) {

                //if a new radioHostEvaluation is available, Notify the User
                if (StubEvaluationDB.radioHostEvaluationList.size > radioHostEvaluationCounter) {
                    Toast.makeText(applicationContext, "$username, du hast eine neue Moderator-Bewertung erhalten!", Toast.LENGTH_SHORT).show()
                    radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
                }
                //if a new playlistEvaluation is available, Notify the User
                if (StubEvaluationDB.playlistEvaluationList.size > playlistEvaluationCounter) {
                    Toast.makeText(applicationContext,"$username, du hast eine neue Playlist-Bewertung erhalten!", Toast.LENGTH_SHORT).show()
                    playlistEvaluationCounter = StubEvaluationDB.playlistEvaluationList.size
                }
                //if a new Songwish is available, Notify the User
                if (StubEvaluationDB.wishSongList.size > wishSongCounter) {
                    Toast.makeText(applicationContext,"$username, du hast einen neuen Songwunsch erhalten!", Toast.LENGTH_SHORT).show()
                    wishSongCounter = StubEvaluationDB.wishSongList.size
                }
            }
            handlingToast.postDelayed(this, 8000L)
        }
    }


    //menu gets inflated to be used
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.headmenu, menu)
        return true
    }

    //program that will be executed if specific item of OptionsMenu is clicked
    override fun onOptionsItemSelected(item : MenuItem): Boolean {
        finish()
        return true
    }

    override fun onPause() {
        handlingToast.removeCallbacks(eventToast)
        super.onPause()
    }
}