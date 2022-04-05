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
import com.example.radio_app_iu.com.example.radio_app_iu.DatenbankKlasse
import com.example.radio_app_iu.databinding.ActivityModeratorBewertungenBinding

private lateinit var binding: ActivityModeratorBewertungenBinding

class ModeratorBewertungenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModeratorBewertungenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
        val currentRadioHost = RadioStation().stubGetCurrentRadioHost()
        val username = intent.getStringExtra("username").toString()

        var summe = 0.0
        StubEvaluationDB.radioHostEvaluationList.forEach { it ->
            summe += it.returnRating()
        }
        val durchschnitt = summe / (StubEvaluationDB.radioHostEvaluationList.size)

        fun lesen() {
            if (StubEvaluationDB.radioHostEvaluationList.isNotEmpty()) {
                binding.lyBewertungen.removeAllViews()
                StubEvaluationDB.radioHostEvaluationList.forEach { it ->
                    val bewertung = it.radioHostEvaluation
                    val nickname = it.radioHostEvaluationNickname
                    val rating = it.radioHostRating
                    val time = it.radioHostTimestamp
                    val daten = "%s\n\n%s:   %d/5   %s".format(bewertung, nickname, rating, time)
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

        binding.buEintraegeAktualisieren.setOnClickListener {
            lesen()
            if (durchschnitt.isNaN())
            else binding.tvBewertungenDurchschnitt.text = "Durchschnitts Rating: %.1f".format(durchschnitt)
        }

        //Handler with Looper
        val handling = Handler(Looper.getMainLooper())

        //creating Runnable object
        val event = object : Runnable {
            override fun run() {

                //if logged in radio host is current radio host:
                if(username == currentRadioHost && StubEvaluationDB.radioHostEvaluationList.size > radioHostEvaluationCounter){
                    Toast.makeText(applicationContext, "$username, du hast eine neue Bewertung erhalten!", Toast.LENGTH_SHORT).show()
                    radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
                }
                handling.postDelayed(this, 8000L)
            }
        }

        //calling the Handler with Looper at onCreate
        handling.postDelayed(event, 0L)

        //logout stops the Handler with Runnable
        binding.backbutton.setOnClickListener{
            handling.removeCallbacks(event)
            finish()
        }
    }



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
}