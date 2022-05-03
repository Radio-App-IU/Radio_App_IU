package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.radio_app_iu.com.example.radio_app_iu.WishSong
import com.example.radio_app_iu.databinding.ActivityWishSongBinding
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: ActivityWishSongBinding

class WishSongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishSongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dateTimeFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

        //return button
        binding.ibBackbutton.setOnClickListener {
            finish()
        }

        //submit button for wish song
        binding.ibOkbutton.setOnClickListener {

            if (binding.etWishSongBox.text.toString().isNotEmpty() && binding.etWishSongNickname.text.toString().isNotEmpty()
            ) {

                //variables receiving the input of the EditTexts
                val wishSongText = binding.etWishSongBox.text.toString()
                val edWishSongNickname = binding.etWishSongNickname.text.toString()
                val idWishSong = StubEvaluationDB.wishSongList.size + 1
                val wishSongTimestamp = dateTimeFormat.format(Calendar.getInstance().time)


                //creates a WishSong object and puts it in the WishSongList of the StubEvaluationDB Class
                val wishSongObject = WishSong(idWishSong, wishSongText, edWishSongNickname, wishSongTimestamp)
                StubEvaluationDB.wishSongList.add(wishSongObject)

                binding.etWishSongBox.setText("")
                binding.etWishSongNickname.setText("")

                Toast.makeText(this, "Wunschsong abgesendet", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Bitte Wunschsong UND Nickname eingeben!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}