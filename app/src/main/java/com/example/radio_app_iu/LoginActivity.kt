package com.example.radio_app_iu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import com.example.radio_app_iu.databinding.ActivityLoginBinding
import android.widget.LinearLayout
import com.airbnb.paris.extensions.style
import com.example.radio_app_iu.com.example.radio_app_iu.Evaluation

private lateinit var binding: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val modBegruessung = stubGetCurrentRadioHost()
        binding.tvModeratorBegruessung.text = "Hallo %s!".format(modBegruessung)

        fun lesen() {
            if (StubEvaluationDB.wishSongList.isNotEmpty()) {
                binding.lySongWunsch.removeAllViews()
                StubEvaluationDB.wishSongList.forEach { it ->
                    val song = it.returnWishedSong()
                    val nickname = it.returnNickname()
                    val daten = "%s:\n%s".format(nickname, song)
                    val tvNeu = TextView(this)
                    binding.lySongWunsch.addView(tvNeu)
                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ) //Parameter der neuen Textview
                    tvNeu.style(R.style.Bewertungen)
                    tvNeu.layoutParams = lp
                    tvNeu.gravity = Gravity.CENTER_HORIZONTAL
                    tvNeu.text = daten
                }
            } else {
                binding.tvEintraegeAktualisieren.text = "Keine Wünsche vorhanden\nBitte Aktualisieren"
            }
        }

        binding.buModeratorBewertungen.setOnClickListener {
            startActivity(Intent(this, ModeratorBewertungenActivity::class.java))
        }

        binding.buPlaylistBewertungen.setOnClickListener {
            startActivity(Intent(this, PlaylistBewertungenActivity::class.java))
        }

        binding.buEintraegeAktualisieren.setOnClickListener {
            lesen()
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