package com.example.radio_app_iu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.radio_app_iu.databinding.ActivityPopUpLoginBinding


private lateinit var binding: ActivityPopUpLoginBinding
private val logincheck = StubServerLoginCheck()

class PopUpLogin : AppCompatActivity() {


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityPopUpLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //sets OnclickListener for TextInput Password
    binding.slotPassword.setOnClickListener {
        binding.slotPassword.setTextColor(Color.BLACK)

        //sets Password invisible
        binding.slotPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
    }

    //sets OnclickListener for TextInput Username
    binding.slotUsername.setOnClickListener {
        binding.slotUsername.setTextColor(Color.BLACK)
    }

    //onClickListener für das Login
    binding.submit.setOnClickListener {
        if (logincheck.checkLogin(binding.slotUsername.getText().toString(), binding.slotPassword.getText().toString())) {

            Toast.makeText(this, "Login erfolgreich!", Toast.LENGTH_SHORT).show()
            //startActivity(Intent(this, LoginActivity::class.java))

        } else Toast.makeText(this, "Login nicht erfolgreich!", Toast.LENGTH_SHORT).show()
    }

    binding.backbutton.setOnClickListener {
        startActivity(Intent(this, MainActivity::class.java))
    }
}}