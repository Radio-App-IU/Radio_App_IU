package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.radio_app_iu.databinding.ActivityMainBinding
import android.content.Intent
import android.content.ContentValues
import android.widget.Toast
import com.example.radio_app_iu.com.example.radio_app_iu.DatenbankKlasse
import com.example.radio_app_iu.com.example.radio_app_iu.WishSong

private lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val datenbank = DatenbankKlasse(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buLogin.setOnClickListener {
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
        }

        binding.buModAktualisieren.setOnClickListener {
            if (binding.etBewertungstext.text.toString().isNotEmpty() && binding.etNickname.text.toString().isNotEmpty() && binding.etRating.text.toString().isNotEmpty()
            ) {
                //variables receiving the input of the EditTexts
                val text = binding.etBewertungstext.text.toString()
                val nickname = binding.etNickname.text.toString()
                val id = StubEvaluationDB.radioHostEvaluationList.size + 1
                val rating: Int = binding.etRating.text.toString().toInt()

                //creates a WishSong object and puts it in the WishSongList of the StubEvaluationDB Class
                val radioHostEvaluationObject = RadioHostEvaluation(id, text, nickname, rating)
                StubEvaluationDB.radioHostEvaluationList.add(radioHostEvaluationObject)

                binding.etBewertungstext.setText("")
                binding.etNickname.setText("")
                binding.etRating.setText("")

                Toast.makeText(this, "Bewertung abgesendet", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Bitte Bewertung, Rating UND Nickname eingeben!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buSongwunschAktualisieren.setOnClickListener {
            if (binding.etBewertungstext1.text.toString().isNotEmpty() && binding.etNickname1.text.toString().isNotEmpty()
            ) {
                //variables receiving the input of the EditTexts
                val wishSongText = binding.etBewertungstext1.text.toString()
                val wishSongNickname = binding.etNickname1.text.toString()
                val idWishSong = StubEvaluationDB.wishSongList.size + 1

                //creates a WishSong object and puts it in the WishSongList of the StubEvaluationDB Class
                val wishSongObject = WishSong(idWishSong, wishSongText, wishSongNickname)
                StubEvaluationDB.wishSongList.add(wishSongObject)

                binding.etBewertungstext1.setText("")
                binding.etNickname1.setText("")

                Toast.makeText(this, "Wunschsong abgesendet", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Bitte Wunschsong UND Nickname eingeben!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        datenbank.close()
    }
}