package com.example.radio_app_iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.radio_app_iu.databinding.ActivityMainBinding
import android.content.Intent

private lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buLogin.setOnClickListener {
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
        }
    }
}