package com.example.radio_app_iu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.radio_app_iu.databinding.ActivityMainBinding

//values and variables used in several methods of this activity
private lateinit var binding: ActivityMainBinding

private var stubPlayer = MediaPlayer()
private val playlist = StubPlaylist
private var nextLine = "\n"

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPauseButton()

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

        //starting EvaluationActivity
        binding.buttonEvaluate.setOnClickListener{
            startActivity(Intent(this, Evaluation::class.java))
        }

        //starting WishSongActivity
        binding.wishSong.setOnClickListener{
            startActivity(Intent(this, WishSongActivity::class.java))
        }

        //OnTouchListener for infobox
        binding.infobox.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {

                if(stubPlayer.isPlaying){

                    binding.infoText.setText(nextLine + playlist.getSongTitle() + nextLine + nextLine + playlist.getSongInterpret() + nextLine +
                            nextLine + playlist.getAlbumName() + nextLine + nextLine + playlist.getSongYear() + nextLine + nextLine + playlist.getSongLength())
                }
                else {
                    binding.infoText.setText(nextLine + "Keine Informationen verfügbar!")
                }
                binding.infobox.setBackgroundResource(R.drawable.blueinfobox)
                true
            }
            else {
                    binding.infoText.setText("")
                    binding.infobox.setBackgroundColor(Color.TRANSPARENT)
                true
            }
        }
        binding.infobutton.setOnClickListener{
            startActivity(Intent(this, TestActivity::class.java))
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
               startActivity(Intent(this, Login::class.java))
            }
        return true
    }

    //method to play song of playlist/creates mediaplayer if not already playing
    private fun playSong() {
        if (!stubPlayer.isPlaying) {
            stubPlayer = MediaPlayer.create(this, playlist.getSongFile())
        }

        //unmutes and starts player
        stubPlayer.setVolume(1f, 1f)
        stubPlayer.start()
    }

    //method for muting the player
    private fun muteSong() {
       stubPlayer.setVolume(0f,0f)
    }

    //if there's a change to another activity and the player is playing, the information will be set
    private fun setPauseButton(){
        if(stubPlayer.isPlaying){
            binding.playbutton.setImageResource(R.drawable.playbutton1)
            binding.playbuttoninvisible.visibility = View.INVISIBLE
            binding.playbuttoninvisible2.visibility = View.VISIBLE
            binding.songOutput.setText(playlist.getSong())
            binding.album.setImageResource(playlist.getAlbumCover())
        }
    }
}