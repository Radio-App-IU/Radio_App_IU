package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.radio_app_iu.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main) unnötig durch Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //      var buttonBewerten : Button = findViewById(R.id.buttonbewerten);
        //      buttonBewerten.setBackgroundColor(0x654688);

        //OnClickListener Playbutton
        binding.playbuttoninvisible.setOnClickListener {
                val v: ImageView = findViewById(R.id.playbutton)
                    v.setImageResource(R.drawable.playbutton1)
                }
        }
}