package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //      var buttonBewerten : Button = findViewById(R.id.buttonbewerten);
        //      buttonBewerten.setBackgroundColor(0x654688);

        //Instanziierien des Playbuttons
        val playbutton : Button = findViewById(R.id.playbuttoninvisible)

        //Playbutton OmClickListener
        playbutton.setOnClickListener {
                val v: ImageView = findViewById(R.id.playbutton)
                    v.setImageResource(R.drawable.playbutton1)
                }
        }
}