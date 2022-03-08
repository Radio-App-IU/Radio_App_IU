package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.example.radio_app_iu.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //onClicklistener for the play buttons
        binding.playbuttoninvisible.setOnClickListener {
            binding.playbutton.setImageResource(R.drawable.playbutton1)
            binding.playbuttoninvisible.visibility = View.INVISIBLE
            binding.playbuttoninvisible2.visibility = View.VISIBLE
                }
        binding.playbuttoninvisible2.setOnClickListener {
            binding.playbutton.setImageResource(R.drawable.playbutton2)
            binding.playbuttoninvisible2.visibility = View.INVISIBLE
            binding.playbuttoninvisible.visibility = View.VISIBLE
        }
    }
    //creating optiions menu headmenu.xml when creating Main Activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.headmenu, menu)
        return true
    }
}