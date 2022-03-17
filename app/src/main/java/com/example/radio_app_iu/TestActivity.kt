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

        binding.textView.setText(StubEvaluationDB.getPlaylistEvaluationsElement())
        binding.textView2.setText(StubEvaluationDB.getRadioHostEvaluationsElement())
        binding.textView3.setText(StubEvaluationDB.getPlaylistElementRating().toString())
        }
}