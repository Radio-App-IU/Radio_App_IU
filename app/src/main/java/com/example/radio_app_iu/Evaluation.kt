package com.example.radio_app_iu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.radio_app_iu.databinding.ActivityEvaluationBinding

private lateinit var binding: ActivityEvaluationBinding

class Evaluation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //return button
        binding.backbutton2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        //submit button for playlist evaluation
        binding.okbutton.setOnClickListener{

            StubEvaluationDB.playlistEvaluation = binding.playlistevalutionbox.text.toString()
            StubEvaluationDB.playlistEvaluationNickname = binding.nickname.text.toString()
            StubEvaluationDB.idPlaylistEvaluation = 3
            Toast.makeText(this, "Playlist bewertet", Toast.LENGTH_SHORT).show()
        }

        //submit button for anchor evaluation
        binding.okbutton2.setOnClickListener{
            Toast.makeText(this, "Moderator bewertet", Toast.LENGTH_SHORT).show()
        }
    }
}