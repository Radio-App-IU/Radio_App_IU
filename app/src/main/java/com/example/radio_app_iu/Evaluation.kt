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
        binding.ibBackbutton.setOnClickListener {
            finish()
        }

        //submit button for playlist evaluation
        binding.ibOkbutton.setOnClickListener{

            if(binding.etPlaylistevaluationbox.text.toString().isNotEmpty() && binding.etNickname2.text.toString().isNotEmpty()){

                //variables receiving the input of the EditTexts and rating
                val playlistEvaluation = binding.etPlaylistevaluationbox.text.toString()
                val playlistEvaluationNickname = binding.etNickname2.text.toString()
                val idPlaylistEvaluation = StubEvaluationDB.playlistEvaluationList.size + 1
                val playlistRating = playlistEvaluationRatingCount
                val playlistTimestamp = dateTimeFormat.format(Calendar.getInstance().time)

                //creates a playlistEvaluation object and puts it in the playlistEvaluationList of the StubEvaluationDB Class
                val playlistEvaluationObject = PlaylistEvaluation(idPlaylistEvaluation, playlistEvaluation, playlistEvaluationNickname, playlistRating, playlistTimestamp)
                StubEvaluationDB.playlistEvaluationList.add(playlistEvaluationObject)

                binding.etPlaylistevaluationbox.setText("")
                binding.etNickname2.setText("")
                binding.ivOnestar.setImageResource(R.drawable.star1)
                binding.ivTwostars.setImageResource(R.drawable.star2)
                binding.ivThreestars.setImageResource(R.drawable.star2)
                binding.ivFourstars.setImageResource(R.drawable.star2)
                binding.ivFivestars.setImageResource(R.drawable.star2)


                playlistEvaluationRatingCount = 1
                Toast.makeText(this, "Playlist bewertet", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Bitte Bewertung UND Nickname eingeben!", Toast.LENGTH_SHORT).show()
            }
        }

        //submit button for anchor evaluation
        binding.ibOkbutton2.setOnClickListener{

            //variables receiving the input of the EditTexts and rating
            if(binding.etAnchorevaluationbox.text.toString().isNotEmpty() && binding.etNickname.text.toString().isNotEmpty()){
                val radioHostEvaluation = binding.etAnchorevaluationbox.text.toString()
                val radioHostEvaluationNickname = binding.etNickname.text.toString()
                val idRadioHostEvaluation = StubEvaluationDB.radioHostEvaluationList.size + 1
                val radioHostRating = radioHostEvaluationRatingCount
                //format gets applied on the passed object(the current time)
                val radioHostTimestamp = dateTimeFormat.format(Calendar.getInstance().time)
                //StubEvaluationDB.moderator = StubGetCurrentRadioHost

                //creates a radioHostEvaluation object and puts it in the radioHostEvaluationList of the StubEvaluationDB Class
                val radioHostEvaluationObject = RadioHostEvaluation(idRadioHostEvaluation, radioHostEvaluation, radioHostEvaluationNickname, radioHostRating, stubGetCurrentRadioHost() , radioHostTimestamp)
                StubEvaluationDB.radioHostEvaluationList.add(radioHostEvaluationObject)

                binding.etAnchorevaluationbox.setText("")
                binding.etNickname.setText("")
                binding.ivOnestaranchor.setImageResource(R.drawable.star1)
                binding.ivTwostaranchor.setImageResource(R.drawable.star2)
                binding.ivThreestaranchor.setImageResource(R.drawable.star2)
                binding.ivFourstaranchor.setImageResource(R.drawable.star2)
                binding.ivFivestaranchor.setImageResource(R.drawable.star2)

                radioHostEvaluationRatingCount = 1
                Toast.makeText(this, "Moderator bewertet", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Bitte Bewertung UND Nickname eingeben!", Toast.LENGTH_SHORT).show()
            }
        }

        //onClickListeners for rating star 1 - 5 of playlist and radioHost evaluation
        binding.ivOnestar.setOnClickListener{
            binding.ivOnestar.setImageResource(R.drawable.star1)
            binding.ivTwostars.setImageResource(R.drawable.star2)
            binding.ivThreestars.setImageResource(R.drawable.star2)
            binding.ivFourstars.setImageResource(R.drawable.star2)
            binding.ivFivestars.setImageResource(R.drawable.star2)
            playlistEvaluationRatingCount = 1
        }

        binding.ivTwostars.setOnClickListener{
            binding.ivOnestar.setImageResource(R.drawable.star1)
            binding.ivTwostars.setImageResource(R.drawable.star1)
            binding.ivThreestars.setImageResource(R.drawable.star2)
            binding.ivFourstars.setImageResource(R.drawable.star2)
            binding.ivFivestars.setImageResource(R.drawable.star2)
            playlistEvaluationRatingCount = 2
        }

        binding.ivThreestars.setOnClickListener{
            binding.ivOnestar.setImageResource(R.drawable.star1)
            binding.ivTwostars.setImageResource(R.drawable.star1)
            binding.ivThreestars.setImageResource(R.drawable.star1)
            binding.ivFourstars.setImageResource(R.drawable.star2)
            binding.ivFivestars.setImageResource(R.drawable.star2)
            playlistEvaluationRatingCount = 3
        }

        binding.ivFourstars.setOnClickListener{
            binding.ivOnestar.setImageResource(R.drawable.star1)
            binding.ivTwostars.setImageResource(R.drawable.star1)
            binding.ivThreestars.setImageResource(R.drawable.star1)
            binding.ivFourstars.setImageResource(R.drawable.star1)
            binding.ivFivestars.setImageResource(R.drawable.star2)
            playlistEvaluationRatingCount = 4
        }

        binding.ivFivestars.setOnClickListener{
            binding.ivOnestar.setImageResource(R.drawable.star1)
            binding.ivTwostars.setImageResource(R.drawable.star1)
            binding.ivThreestars.setImageResource(R.drawable.star1)
            binding.ivFourstars.setImageResource(R.drawable.star1)
            binding.ivFivestars.setImageResource(R.drawable.star1)
            playlistEvaluationRatingCount = 5
        }

        binding.ivOnestaranchor.setOnClickListener{
            binding.ivOnestaranchor.setImageResource(R.drawable.star1)
            binding.ivTwostaranchor.setImageResource(R.drawable.star2)
            binding.ivThreestaranchor.setImageResource(R.drawable.star2)
            binding.ivFourstaranchor.setImageResource(R.drawable.star2)
            binding.ivFivestaranchor.setImageResource(R.drawable.star2)
            radioHostEvaluationRatingCount = 1
        }

        binding.ivTwostaranchor.setOnClickListener{
            binding.ivOnestaranchor.setImageResource(R.drawable.star1)
            binding.ivTwostaranchor.setImageResource(R.drawable.star1)
            binding.ivThreestaranchor.setImageResource(R.drawable.star2)
            binding.ivFourstaranchor.setImageResource(R.drawable.star2)
            binding.ivFivestaranchor.setImageResource(R.drawable.star2)
            radioHostEvaluationRatingCount = 2
        }

        binding.ivThreestaranchor.setOnClickListener{
            binding.ivOnestaranchor.setImageResource(R.drawable.star1)
            binding.ivTwostaranchor.setImageResource(R.drawable.star1)
            binding.ivThreestaranchor.setImageResource(R.drawable.star1)
            binding.ivFourstaranchor.setImageResource(R.drawable.star2)
            binding.ivFivestaranchor.setImageResource(R.drawable.star2)
            radioHostEvaluationRatingCount = 3
        }

        binding.ivFourstaranchor.setOnClickListener{
            binding.ivOnestaranchor.setImageResource(R.drawable.star1)
            binding.ivTwostaranchor.setImageResource(R.drawable.star1)
            binding.ivThreestaranchor.setImageResource(R.drawable.star1)
            binding.ivFourstaranchor.setImageResource(R.drawable.star1)
            binding.ivFivestaranchor.setImageResource(R.drawable.star2)
            radioHostEvaluationRatingCount = 4
        }

        binding.ivFivestaranchor.setOnClickListener{
            binding.ivOnestaranchor.setImageResource(R.drawable.star1)
            binding.ivTwostaranchor.setImageResource(R.drawable.star1)
            binding.ivThreestaranchor.setImageResource(R.drawable.star1)
            binding.ivFourstaranchor.setImageResource(R.drawable.star1)
            binding.ivFivestaranchor.setImageResource(R.drawable.star1)
            radioHostEvaluationRatingCount = 5
        }
    }

    fun stubGetCurrentRadioHost() : String {
        return "Moderator1"
    }
}