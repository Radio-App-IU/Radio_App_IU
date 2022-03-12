package com.example.radio_app_iu

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity
import com.example.radio_app_iu.databinding.ActivityPopUpLoginBinding


private lateinit var binding: ActivityPopUpLoginBinding

class PopUpLogin : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopUpLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sets OnclickListener for TextInput Password
        binding.slotPassword.setOnClickListener {
            binding.slotPassword.setTextColor(Color.BLACK)
            binding.slotPassword.setText(" ")

            //sets Password invisible
            binding.slotPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
        }

        //sets OnclickListener for TextInput Username
        binding.slotUsername.setOnClickListener {
            binding.slotUsername.setTextColor(Color.BLACK)
            binding.slotUsername.setText(" ")
        }
    }

}