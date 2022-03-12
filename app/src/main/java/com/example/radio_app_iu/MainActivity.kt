package com.example.radio_app_iu

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.radio_app_iu.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding
var player = MediaPlayer()
val playlist = Playlist

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //onClicklistener for the playbuttons
        binding.playbuttoninvisible.setOnClickListener {
            binding.playbutton.setImageResource(R.drawable.playbutton1)
            binding.playbuttoninvisible.visibility = View.INVISIBLE
            binding.playbuttoninvisible2.visibility = View.VISIBLE
            binding.songOutput.setText(playlist.getSong())
            playSong()
        }
        binding.playbuttoninvisible2.setOnClickListener {
            binding.playbutton.setImageResource(R.drawable.playbutton2)
            binding.playbuttoninvisible2.visibility = View.INVISIBLE
            binding.playbuttoninvisible.visibility = View.VISIBLE
            pauseSong()
        }

    }
    //creating options menu headmenu.xml when creating Main Activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.headmenu, menu)
        return true
    }

    //program that will be executed if specific item of OptionsMenu is clicked
    override fun onOptionsItemSelected(item : MenuItem): Boolean {
            if(item.getItemId() == R.id.item1){
               startActivity(Intent(this, PopUpLogin::class.java))

            }
        return true
    }

    private fun playSong() {
        if (!player.isPlaying) {
            player = MediaPlayer.create(this, playlist.getSongFile())
        }
        player.setVolume(1f, 1f)
        player.start()
    }

    private fun pauseSong() {
        player.setVolume(0f,0f)
    }

    override fun onStop(){
        super.onStop()
        player.release()
    }
}