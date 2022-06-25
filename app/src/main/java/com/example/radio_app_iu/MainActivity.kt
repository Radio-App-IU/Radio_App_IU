package com.example.radio_app_iu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.radio_app_iu.databinding.ActivityMainBinding

//values and variables used in several methods of this activity
private lateinit var binding: ActivityMainBinding

private var stubPlayer = MediaPlayer()
private val playlist = StubPlaylist
private var nextLine = "\n"
private var infoClickCounter = 0
private var playButtonCounter = 0

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPauseButton()

        binding.tvInfoText.visibility = View.INVISIBLE

        //onClicklistener for the playbutton

        binding.ibButtonPlay.setOnClickListener{

            if(playButtonCounter % 2 == 0){
                playSong()
                binding.ibButtonPlay.setImageResource(R.drawable.playbutton_2)
                //sets the title and interpret of the current song
                binding.tvSongOutput.setText(playlist.getSong())
                //sets album cover image
                binding.ivAlbum.setImageResource(playlist.getAlbumCover())
                } else {
                binding.ibButtonPlay.setImageResource(R.drawable.playbutton_1)
                muteSong()
            }
            playButtonCounter+=1
        }

        //starting EvaluationActivity
        binding.buEvaluate.setOnClickListener{
            startActivity(Intent(this, Evaluation::class.java))
        }

        //starting WishSongActivity
        binding.buWishSong.setOnClickListener{
            startActivity(Intent(this, WishSongActivity::class.java))
        }

      // displays information by clicking on infobutton
      binding.ibInfobutton.setOnClickListener{
          if (infoClickCounter % 2 == 0){
              binding.tvInfoText.visibility = View.VISIBLE
              binding.ivAlbum.visibility = View.INVISIBLE
              if (stubPlayer.isPlaying) {

                  binding.tvInfoText.setText(
                      nextLine + playlist.getSongTitle() + nextLine + nextLine + playlist.getSongInterpret() + nextLine +
                              nextLine + playlist.getAlbumName() + nextLine + nextLine + playlist.getSongYear() + nextLine + nextLine + playlist.getSongLength()
                  )
              } else {
                  binding.tvInfoText.setText(nextLine + "Keine" + nextLine + "Informationen" + nextLine + "verfügbar!")
              }
          }
          else {
              binding.tvInfoText.visibility = View.INVISIBLE
              binding.ivAlbum.visibility = View.VISIBLE
          }
          infoClickCounter += 1
      }

    }
    //creating options menu headmenu.xml when creating Main Activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.headmenu_login, menu)
        return true
    }

    //program that will be executed if specific item of OptionsMenu is clicked
    override fun onOptionsItemSelected(item : MenuItem): Boolean {
            if(item.getItemId() == R.id.item2){
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
            playButtonCounter = 1
            binding.ibButtonPlay.setImageResource(R.drawable.playbutton_2)
            binding.tvSongOutput.setText(playlist.getSong())
            binding.ivAlbum.setImageResource(playlist.getAlbumCover())
        }
    }
}