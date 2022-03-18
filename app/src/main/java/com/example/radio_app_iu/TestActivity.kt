package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.radio_app_iu.databinding.ActivityTestBinding

private lateinit var binding: ActivityTestBinding

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savePlaylistEvaluationInList()
        saveRadioHostEvaluationInList()
        }

    fun savePlaylistEvaluationInList(){
        val string = StubEvaluationDB.playlistEvaluationList[0].returnPlaylistEvaluation() + "\n" + StubEvaluationDB.playlistEvaluationList[0].returnNickname()
        binding.textView4.setText(string)
    }

    fun saveRadioHostEvaluationInList(){
        val string = StubEvaluationDB.radioHostEvaluationList[0].returnRadioHostEvaluation() + "\n" + StubEvaluationDB.radioHostEvaluationList[0].returnNickname()
        binding.textView2.setText(string)
    }
}