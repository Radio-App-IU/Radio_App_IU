package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.paris.extensions.style
import com.example.radio_app_iu.com.example.radio_app_iu.DatenbankKlasse
import com.example.radio_app_iu.databinding.ActivityModeratorBewertungenBinding

private lateinit var binding: ActivityModeratorBewertungenBinding

class ModeratorBewertungenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModeratorBewertungenBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.backbutton.setOnClickListener {
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