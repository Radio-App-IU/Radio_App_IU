package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.radio_app_iu.databinding.ActivityPopUpLoginBinding

private lateinit var binding: ActivityPopUpLoginBinding

class PopUpLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopUpLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}