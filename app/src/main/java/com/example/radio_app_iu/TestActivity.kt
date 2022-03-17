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

        /**if(StubEvaluationDB.playlistEvaluationList.isNotEmpty()) {**/
        //binding.textView.setText(stubEvaluationDB.getPlaylistEvaluationsElement()
         binding.textView.setText(StubEvaluationDB.getPlaylistEvaluationsElement())
        }
        //else binding.textView.setText("leer")
    //}
}