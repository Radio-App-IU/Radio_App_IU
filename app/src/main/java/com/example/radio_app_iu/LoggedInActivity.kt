package com.example.radio_app_iu


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import com.example.radio_app_iu.databinding.ActivityLoggedinBinding
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.airbnb.paris.extensions.style

private lateinit var binding: ActivityLoggedinBinding
private var username = "Daniel"

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoggedinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //greeting the logged-in moderator
        username = intent.getStringExtra("username").toString()
        binding.tvModeratorBegruessung.text = "Hallo %s!".format(username)

        //function to add new evaluations to the view
        fun lesen() {
            if (StubEvaluationDB.wishSongList.isNotEmpty()) {
                binding.lySongWunsch.removeAllViews()
                StubEvaluationDB.wishSongList.forEach { it ->
                    val song = it.returnWishedSong()
                    val nickname = it.returnNickname()
                    val time = it.returnTimestamp()
                    val daten = "%s:\n%s\n%s".format(nickname, song, time)
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
            startActivity(Intent(this, ModeratorBewertungenActivity::class.java).putExtra("username",username))
            activityWechsel()
        }

        binding.buPlaylistBewertungen.setOnClickListener {
            startActivity(Intent(this, PlaylistBewertungenActivity::class.java))
            activityWechsel()
        }

        binding.buEintraegeAktualisieren.setOnClickListener {
            lesen()
        }

        //calling the Toast Handler with Looper at onCreate
        handlingToast.postDelayed(eventToast, 0L)

        //create the NotificationChannel at onCreate
        createNotificationChannel()

        //calling the Notification Handler with Looper at onCreate
        handlingNotification.postDelayed(eventNotification, 0L)
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

    //start Notification and stop Toast when mod leaves the app
    override fun onPause() {
        handlingToast.removeCallbacks(eventToast)
        super.onPause()
    }

    //start Toast and stop Notifications when mod is back
    override fun onResume() {
        handlingToast.postDelayed(eventToast, 0L)
        super.onResume()
    }


    //Handler with Looper
    val handlingToast = Handler(Looper.getMainLooper())
    val handlingNotification = Handler(Looper.getMainLooper())

    //handler variables
    var radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
    var playlistEvaluationCounter = StubEvaluationDB.playlistEvaluationList.size
    var wishSongCounter = StubEvaluationDB.wishSongList.size
    val currentRadioHost = RadioStation().stubGetCurrentRadioHost()

    //creating Runnable Toast object
    val eventToast = object : Runnable {
        override fun run() {

            //if logged in radio host is current radio host:
            if (username == currentRadioHost) {

                //if a new radioHostEvaluation is available, Notify the User
                if (StubEvaluationDB.radioHostEvaluationList.size > radioHostEvaluationCounter) {
                    Toast.makeText(applicationContext, "$username, du hast eine neue Moderator-Bewertung erhalten!", Toast.LENGTH_SHORT).show()
                    radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
                }
                //if a new playlistEvaluation is available, Notify the User
                if (StubEvaluationDB.playlistEvaluationList.size > playlistEvaluationCounter) {
                    Toast.makeText(applicationContext,"$username, du hast eine neue Playlist-Bewertung erhalten!", Toast.LENGTH_SHORT).show()
                    playlistEvaluationCounter = StubEvaluationDB.playlistEvaluationList.size
                }
                //if a new Songwish is available, Notify the User
                if (StubEvaluationDB.wishSongList.size > wishSongCounter) {
                    Toast.makeText(applicationContext,"$username, du hast einen neuen Songwunsch erhalten!", Toast.LENGTH_SHORT).show()
                    wishSongCounter = StubEvaluationDB.wishSongList.size
                }
            }
            handlingToast.postDelayed(this, 8000L)
        }
    }

    //Notification Values
    private val NOTIFICATION_ID = 42
    private val CHANNEL_ID = "channel01"

    //function to create the NotificationChannel
    private fun createNotificationChannel() {
        val name = "Moderator Notification"
        val descriptionText = "Benachrichtigung über neue Einträge der User"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }
    //function to send the Notification
    private fun sendNotification(Nachricht:String) {
        val intent = Intent(this, LoginActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Neue Hörerbeiträge")
            .setContentText(Nachricht)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setSilent(false)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(Nachricht))

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    //creating Runnable Notification object
    val eventNotification = object : Runnable {
        override fun run() {

            //send a notification if at least one new entry is available
            if (username == currentRadioHost &&
                StubEvaluationDB.radioHostEvaluationList.size > radioHostEvaluationCounter ||
                StubEvaluationDB.playlistEvaluationList.size > playlistEvaluationCounter ||
                StubEvaluationDB.wishSongList.size > wishSongCounter) {
                var notificationText = "$username, du hast neue Einträge erhalten:"

                //if a new radioHostEvaluation is available, Notify the User
                if (StubEvaluationDB.radioHostEvaluationList.size > radioHostEvaluationCounter) {
                    notificationText += "\nNeue Moderator-Bewertung"
                    radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
                }
                //if a new playlistEvaluation is available, Notify the User
                if (StubEvaluationDB.playlistEvaluationList.size > playlistEvaluationCounter) {
                    notificationText += "\nNeue Playlist-Bewertung"
                    playlistEvaluationCounter = StubEvaluationDB.playlistEvaluationList.size
                }
                //if a new Wish for a Song is available, Notify the User
                if (StubEvaluationDB.wishSongList.size > wishSongCounter) {
                    notificationText += "\nNeuer Song Wunsch"
                    wishSongCounter = StubEvaluationDB.wishSongList.size
                }
                sendNotification(notificationText)
            }
            handlingNotification.postDelayed(this, 8000L)
        }
    }

    // if the user changes activity and the handler did not activate, this function will show the toast
    private fun activityWechsel() {
        if(username == currentRadioHost &&
            StubEvaluationDB.radioHostEvaluationList.size > radioHostEvaluationCounter ||
            StubEvaluationDB.playlistEvaluationList.size > playlistEvaluationCounter ||
            StubEvaluationDB.wishSongList.size > wishSongCounter) {

            //if a new radioHostEvaluation is available, Notify the User
            if (StubEvaluationDB.radioHostEvaluationList.size > radioHostEvaluationCounter) {
                Toast.makeText(applicationContext, "$username, du hast eine neue Moderator-Bewertung erhalten!", Toast.LENGTH_SHORT).show()
                radioHostEvaluationCounter = StubEvaluationDB.radioHostEvaluationList.size
            }
            //if a new playlistEvaluation is available, Notify the User
            if (StubEvaluationDB.playlistEvaluationList.size > playlistEvaluationCounter) {
                Toast.makeText(applicationContext,"$username, du hast eine neue Playlist-Bewertung erhalten!", Toast.LENGTH_SHORT).show()
                playlistEvaluationCounter = StubEvaluationDB.playlistEvaluationList.size
            }
            //if a new Songwish is available, Notify the User
            if (StubEvaluationDB.wishSongList.size > wishSongCounter) {
                Toast.makeText(applicationContext,"$username, du hast einen neuen Songwunsch erhalten!", Toast.LENGTH_SHORT).show()
                wishSongCounter = StubEvaluationDB.wishSongList.size
            }
        }
    }
}