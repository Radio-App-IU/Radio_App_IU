package com.example.radio_app_iu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.radio_app_iu.databinding.ActivityMainBinding
import com.example.radio_app_iu.databinding.ActivityWishSongBinding

private lateinit var binding: ActivityWishSongBinding

class WishSongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWishSongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //return button
        binding.backbutton3.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        //submit button for wish song
        binding.okbutton3.setOnClickListener {

            if (binding.wishSongBox.text.toString().isNotEmpty() && binding.wishSongNickname.text.toString().isNotEmpty()
            ) {

                //variables receiving the input of the EditTexts
                val wishSongText = binding.wishSongBox.text.toString()
                val wishSongNickname = binding.wishSongNickname.text.toString()
                val idWishSong = StubEvaluationDB.wishSongList.size + 1

                //creates a WishSong object and puts it in the WishSongList of the StubEvaluationDB Class
                val wishSongObject = WishSong(idWishSong, wishSongText, wishSongNickname)
                StubEvaluationDB.wishSongList.add(wishSongObject)

                binding.wishSongBox.setText("")
                binding.wishSongNickname.setText("")

                Toast.makeText(this, "Wunschsong abgesendet", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Bitte Wunschsong UND Nickname eingeben!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}