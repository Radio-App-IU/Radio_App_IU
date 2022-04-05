package com.example.radio_app_iu

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.radio_app_iu.databinding.ActivityLoginBinding
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.airbnb.paris.extensions.style

private lateinit var binding: ActivityLoginBinding
private val logincheck = StubServerLoginCheck()
private const val NOTIFICATION_ID = 42
private const val CHANNEL_ID = "channel01"
private const val RESULT_KEY = "resultKey"

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //greeting the logged-in moderator
        val username = intent.getStringExtra("username").toString()
        binding.tvModeratorBegruessung.text = "Hallo %s!".format(username)

        //function to add new evaluations to the view
        fun lesen() {
            if (StubEvaluationDB.wishSongList.isNotEmpty()) {
                binding.lySongWunsch.removeAllViews()
                StubEvaluationDB.wishSongList.forEach { it ->
                    val song = it.returnWishedSong()
                    val nickname = it.returnNickname()
                    val time = it.returnTimestamp()
                    val daten = "%s:\n%s %s".format(nickname, song, time)
                    val tvNeu = TextView(this)
                    binding.lySongWunsch.addView(tvNeu)
                    val lp = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ) //Parameter der neuen Textview
                    tvNeu.style(R.style.Bewertungen)
                    tvNeu.layoutParams = lp
                    tvNeu.gravity = Gravity.CENTER_HORIZONTAL
                    tvNeu.text = daten
                }
            } else {
                binding.tvEintraegeAktualisieren.text = "Keine Wünsche vorhanden\nBitte Aktualisieren"
            }
        }

        binding.buModeratorBewertungen.setOnClickListener {
            startActivity(Intent(this, ModeratorBewertungenActivity::class.java))
        }

        binding.buPlaylistBewertungen.setOnClickListener {
            startActivity(Intent(this, PlaylistBewertungenActivity::class.java))
        }

        binding.buEintraegeAktualisieren.setOnClickListener {
            lesen()
        }

        //Handler with Looper
        val handling = Handler(Looper.getMainLooper())

        //handler variables
        var radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
        var playlistEvaluationCounter = StubEvaluationDB.playlistEvaluationList.size
        var wishSongCounter = StubEvaluationDB.wishSongList.size
        val currentRadioHost = RadioStation().stubGetCurrentRadioHost()

        //creating Runnable object
        val event = object : Runnable {
            override fun run() {

                //if logged in radio host is current radio host:
                if (username == currentRadioHost) {

                    if (StubEvaluationDB.radioHostEvaluationList.size > radioHostEvaluationCounter) {
                        createAndSendNotification("$username, du hast eine neue Moderator-Bewertung erhalten!")
                        radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
                    }
                    if (StubEvaluationDB.playlistEvaluationList.size > playlistEvaluationCounter) {
                        createAndSendNotification("$username, du hast eine neue Playlist-Bewertung erhalten!")
                        playlistEvaluationCounter = StubEvaluationDB.playlistEvaluationList.size
                    }
                    if (StubEvaluationDB.wishSongList.size > wishSongCounter) {
                        createAndSendNotification("$username, du hast einen neuen Songwunsch erhalten!")
                        wishSongCounter = StubEvaluationDB.wishSongList.size
                    }
                    handling.postDelayed(this, 8000L)
                }
            }
        }

        //calling the Handler with Looper at onCreate
        handling.postDelayed(event, 0L)
    }

    //function for Notification
    private fun createAndSendNotification(Nachricht: String) {
        val pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, LoginActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(Nachricht)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(false)
        val manager = NotificationManagerCompat.from(this)
        val channel = NotificationChannel(CHANNEL_ID, getString(R.string.channel_name),
            NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)
        manager.notify(NOTIFICATION_ID, builder.build())
    }

    //menu gets inflated to be used
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.headmenu, menu)
        return true
    }

    //program that will be executed if specific item of OptionsMenu is clicked
    override fun onOptionsItemSelected(item : MenuItem): Boolean {
        finish()
        return true
    }
}