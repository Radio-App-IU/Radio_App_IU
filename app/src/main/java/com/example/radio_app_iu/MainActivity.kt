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

//values and variables used in several methods of this activity
private lateinit var binding: ActivityMainBinding
var player = MediaPlayer()
val playlist = Playlist


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //onClicklistener for the playbutton
        binding.playbuttoninvisible.setOnClickListener {
            binding.playbutton.setImageResource(R.drawable.playbutton1)

            //sets this button invisible/the pausebutton visible
            binding.playbuttoninvisible.visibility = View.INVISIBLE
            binding.playbuttoninvisible2.visibility = View.VISIBLE

            //sets the title and interpret of the current song
            binding.songOutput.setText(playlist.getSong())

            //sets album cover image
            binding.album.setImageResource(playlist.getAlbumCover())

            //calls the method to play the song
            playSong()
        }

        //onClickListener for the pausebutton
        binding.playbuttoninvisible2.setOnClickListener {
            binding.playbutton.setImageResource(R.drawable.playbutton2)

            //sets this button invisible/the playbutton visible
            binding.playbuttoninvisible2.visibility = View.INVISIBLE
            binding.playbuttoninvisible.visibility = View.VISIBLE

            //calls the method to mute the current song
            muteSong()
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

    //method to play song of playlist/creates mediaplayer if not already playing
    private fun playSong() {
        if (!player.isPlaying) {
            player = MediaPlayer.create(this, playlist.getSongFile())
        }

        //unmutes and starts player
        player.setVolume(1f, 1f)
        player.start()
    }

    private fun muteSong() {
        player.setVolume(0f,0f)
    }

    //releases player's resources when app stops
    override fun onStop(){
        super.onStop()
        player.release()
    }
}