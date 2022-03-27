package com.example.radio_app_iu

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.radio_app_iu.databinding.ActivityEvaluationBinding
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: ActivityEvaluationBinding

class Evaluation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvaluationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var playlistEvaluationRatingCount = 1
        var radioHostEvaluationRatingCount = 1

        val dateTimeFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

        //return button
        binding.backbutton2.setOnClickListener {
            finish()
        }

        //submit button for playlist evaluation
        binding.okbutton.setOnClickListener{

            if(binding.playlistevalutionbox.text.toString().isNotEmpty() && binding.nickname.text.toString().isNotEmpty()){

                //variables receiving the input of the EditTexts and rating
                val playlistEvaluation = binding.playlistevalutionbox.text.toString()
                val playlistEvaluationNickname = binding.nickname.text.toString()
                val idPlaylistEvaluation = StubEvaluationDB.playlistEvaluationList.size + 1
                val playlistRating = playlistEvaluationRatingCount
                val playlistTimestamp = dateTimeFormat.format(Calendar.getInstance().time)

                //creates a playlistEvaluation object and puts it in the playlistEvaluationList of the StubEvaluationDB Class
                val playlistEvaluationObject = PlaylistEvaluation(idPlaylistEvaluation, playlistEvaluation, playlistEvaluationNickname, playlistRating, playlistTimestamp)
                StubEvaluationDB.playlistEvaluationList.add(playlistEvaluationObject)

                binding.playlistevalutionbox.setText("")
                binding.nickname.setText("")
                binding.onestar.setImageResource(R.drawable.star1)
                binding.twostars.setImageResource(R.drawable.star2)
                binding.threestars.setImageResource(R.drawable.star2)
                binding.fourstars.setImageResource(R.drawable.star2)
                binding.fivestars.setImageResource(R.drawable.star2)


                playlistEvaluationRatingCount = 1
                Toast.makeText(this, "Playlist bewertet", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Bitte Bewertung UND Nickname eingeben!", Toast.LENGTH_SHORT).show()
            }
        }

        //submit button for anchor evaluation
        binding.okbutton2.setOnClickListener{

            //variables receiving the input of the EditTexts and rating
            if(binding.anchorevalutionbox.text.toString().isNotEmpty() && binding.nickname2.text.toString().isNotEmpty()){
                val radioHostEvaluation = binding.anchorevalutionbox.text.toString()
                val radioHostEvaluationNickname = binding.nickname2.text.toString()
                val idRadioHostEvaluation = StubEvaluationDB.radioHostEvaluationList.size + 1
                val radioHostRating = radioHostEvaluationRatingCount
                //format gets applied on the passed object(the current time)
                val radioHostTimestamp = dateTimeFormat.format(Calendar.getInstance().time)
                //StubEvaluationDB.moderator = StubGetCurrentRadioHost

                //creates a radioHostEvaluation object and puts it in the radioHostEvaluationList of the StubEvaluationDB Class
                val radioHostEvaluationObject = RadioHostEvaluation(idRadioHostEvaluation, radioHostEvaluation, radioHostEvaluationNickname, radioHostRating, stubGetCurrentRadioHost() , radioHostTimestamp)
                StubEvaluationDB.radioHostEvaluationList.add(radioHostEvaluationObject)

                binding.anchorevalutionbox.setText("")
                binding.nickname2.setText("")
                binding.onestaranchor.setImageResource(R.drawable.star1)
                binding.twostaranchor.setImageResource(R.drawable.star2)
                binding.threestaranchor.setImageResource(R.drawable.star2)
                binding.fourstaranchor.setImageResource(R.drawable.star2)
                binding.fivestaranchor.setImageResource(R.drawable.star2)

                radioHostEvaluationRatingCount = 1
                Toast.makeText(this, "Moderator bewertet", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Bitte Bewertung UND Nickname eingeben!", Toast.LENGTH_SHORT).show()
            }
        }

        //onClickListeners for rating star 1 - 5 of playlist and radioHost evaluation
        binding.onestar.setOnClickListener{
            binding.onestar.setImageResource(R.drawable.star1)
            binding.twostars.setImageResource(R.drawable.star2)
            binding.threestars.setImageResource(R.drawable.star2)
            binding.fourstars.setImageResource(R.drawable.star2)
            binding.fivestars.setImageResource(R.drawable.star2)
            playlistEvaluationRatingCount = 1
        }

        binding.twostars.setOnClickListener{
            binding.onestar.setImageResource(R.drawable.star1)
            binding.twostars.setImageResource(R.drawable.star1)
            binding.threestars.setImageResource(R.drawable.star2)
            binding.fourstars.setImageResource(R.drawable.star2)
            binding.fivestars.setImageResource(R.drawable.star2)
            playlistEvaluationRatingCount = 2
        }

        binding.threestars.setOnClickListener{
            binding.onestar.setImageResource(R.drawable.star1)
            binding.twostars.setImageResource(R.drawable.star1)
            binding.threestars.setImageResource(R.drawable.star1)
            binding.fourstars.setImageResource(R.drawable.star2)
            binding.fivestars.setImageResource(R.drawable.star2)
            playlistEvaluationRatingCount = 3
        }

        binding.fourstars.setOnClickListener{
            binding.onestar.setImageResource(R.drawable.star1)
            binding.twostars.setImageResource(R.drawable.star1)
            binding.threestars.setImageResource(R.drawable.star1)
            binding.fourstars.setImageResource(R.drawable.star1)
            binding.fivestars.setImageResource(R.drawable.star2)
            playlistEvaluationRatingCount = 4
        }

        binding.fivestars.setOnClickListener{
            binding.onestar.setImageResource(R.drawable.star1)
            binding.twostars.setImageResource(R.drawable.star1)
            binding.threestars.setImageResource(R.drawable.star1)
            binding.fourstars.setImageResource(R.drawable.star1)
            binding.fivestars.setImageResource(R.drawable.star1)
            playlistEvaluationRatingCount = 5
        }

        binding.onestaranchor.setOnClickListener{
            binding.onestaranchor.setImageResource(R.drawable.star1)
            binding.twostaranchor.setImageResource(R.drawable.star2)
            binding.threestaranchor.setImageResource(R.drawable.star2)
            binding.fourstaranchor.setImageResource(R.drawable.star2)
            binding.fivestaranchor.setImageResource(R.drawable.star2)
            radioHostEvaluationRatingCount = 1
        }

        binding.twostaranchor.setOnClickListener{
            binding.onestaranchor.setImageResource(R.drawable.star1)
            binding.twostaranchor.setImageResource(R.drawable.star1)
            binding.threestaranchor.setImageResource(R.drawable.star2)
            binding.fourstaranchor.setImageResource(R.drawable.star2)
            binding.fivestaranchor.setImageResource(R.drawable.star2)
            radioHostEvaluationRatingCount = 2
        }

        binding.threestaranchor.setOnClickListener{
            binding.onestaranchor.setImageResource(R.drawable.star1)
            binding.twostaranchor.setImageResource(R.drawable.star1)
            binding.threestaranchor.setImageResource(R.drawable.star1)
            binding.fourstaranchor.setImageResource(R.drawable.star2)
            binding.fivestaranchor.setImageResource(R.drawable.star2)
            radioHostEvaluationRatingCount = 3
        }

        binding.fourstaranchor.setOnClickListener{
            binding.onestaranchor.setImageResource(R.drawable.star1)
            binding.twostaranchor.setImageResource(R.drawable.star1)
            binding.threestaranchor.setImageResource(R.drawable.star1)
            binding.fourstaranchor.setImageResource(R.drawable.star1)
            binding.fivestaranchor.setImageResource(R.drawable.star2)
            radioHostEvaluationRatingCount = 4
        }

        binding.fivestaranchor.setOnClickListener{
            binding.onestaranchor.setImageResource(R.drawable.star1)
            binding.twostaranchor.setImageResource(R.drawable.star1)
            binding.threestaranchor.setImageResource(R.drawable.star1)
            binding.fourstaranchor.setImageResource(R.drawable.star1)
            binding.fivestaranchor.setImageResource(R.drawable.star1)
            radioHostEvaluationRatingCount = 5
        }
    }

    fun stubGetCurrentRadioHost() : String {
        return "Moderator1"
    }
}