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
import com.example.radio_app_iu.databinding.ActivityPlaylistBewertungenBinding

private lateinit var binding: ActivityPlaylistBewertungenBinding

class PlaylistBewertungenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistBewertungenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var summe = 0.0
        StubEvaluationDB.playlistEvaluationList.forEach { it ->
            summe += it.returnRating()
        }
        val durchschnitt = summe / (StubEvaluationDB.playlistEvaluationList.size)

        fun lesen() {
            if (StubEvaluationDB.playlistEvaluationList.isNotEmpty()) {
                binding.lyBewertungen.removeAllViews()
                StubEvaluationDB.playlistEvaluationList.forEach { it ->
                    val bewertung = it.returnPlaylistEvaluation()
                    val nickname = it.returnNickname()
                    val rating = it.returnRating()
                    val daten = "%s: %d/5\n%s".format(nickname, rating, bewertung)
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

        binding.Eintrag.setOnClickListener {
            lesen()
            if (durchschnitt.isNaN())
                else binding.tvBewertungenDurchschnitt.text = "Durchschnitts Rating: %.1f".format(durchschnitt)
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