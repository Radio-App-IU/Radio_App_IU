package com.example.radio_app_iu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.radio_app_iu.databinding.ActivityLoginBinding


private lateinit var binding: ActivityLoginBinding

private val logincheck = StubServerLoginCheck()

class Login : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sets OnclickListener for TextInput Password
        binding.etSlotPassword.setOnClickListener {
            binding.etSlotPassword.setTextColor(Color.BLACK)

            //sets Password invisible
            binding.etSlotPassword.setTransformationMethod(PasswordTransformationMethod.getInstance())
        }

        //sets OnclickListener for TextInput Username
        binding.etSlotUsername.setOnClickListener {
            binding.etSlotUsername.setTextColor(Color.BLACK)
        }

        //onClickListener for the login
        binding.buSubmit.setOnClickListener {
            if (logincheck.checkLogin(binding.etSlotUsername.getText().toString(), binding.etSlotPassword.getText().toString())) {

                Toast.makeText(this, "Login erfolgreich!", Toast.LENGTH_SHORT).show()

                //passing the username to the radioHost activity
                var name = binding.etSlotUsername.getText().toString()
                logincheck.userList.forEach {
                    if(it.first == name) {
                        name = it.third
                    }
                }
                    val intent = Intent(this, postLoginActivity::class.java).putExtra("username", name)
                    startActivity(intent)
            }
            else Toast.makeText(this, "Login nicht erfolgreich!", Toast.LENGTH_SHORT).show()
        }

        binding.ivBackbutton.setOnClickListener {
            finish()
        }
    }
}