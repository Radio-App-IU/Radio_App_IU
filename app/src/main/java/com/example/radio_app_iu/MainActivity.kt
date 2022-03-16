package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.radio_app_iu.databinding.ActivityMainBinding
import android.content.Intent
import android.content.ContentValues

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

        binding.buSQLLoeschen.setOnClickListener {
            val loescher = datenbank.writableDatabase
            loescher.delete("Bewertungen", "", null)
        }

        binding.buSQL.setOnClickListener {
            val bewertungstext = binding.etBewertungstext.text.toString()
            val nickname = binding.etNickname.text.toString()
            val rating = binding.etRating.text.toString()

            val schreiber = datenbank.writableDatabase
            val datensatz = ContentValues()
            var ausgabe = ""

            datensatz.put("Bewertungstext", bewertungstext)
            datensatz.put("Nickname", nickname)
            datensatz.put("Rating", rating)
            if (schreiber.insert("Bewertungen", null, datensatz) == -1L)
                ausgabe += "Fehler beim Hinzufügen"
            else
                ausgabe += "Datensatz hinzugefügt"

            binding.textView.text = ausgabe
            schreiber.close()
        }
    }

    override fun onStop() {
        super.onStop()
        datenbank.close()
    }
}