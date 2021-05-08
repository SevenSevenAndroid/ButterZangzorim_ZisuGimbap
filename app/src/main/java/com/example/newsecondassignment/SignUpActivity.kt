package com.example.newsecondassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newsecondassignment.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpEvent()
    }

    private fun signUpEvent() {
        var signUpName = binding.editTextSignUpNameInput.text
        var signUpID = binding.edieTextSignUpIdInput.text
        var signUpPassword = binding.edieTextSignUpPasswordInput.text

        binding.buttonSignUpFinish.setOnClickListener {
            if (signUpName.isNullOrBlank() || signUpID.isNullOrBlank()||signUpPassword.isNullOrBlank()) {
                Toast.makeText(
                        this@SignUpActivity,
                        "빈 칸이 있는지 확인해주세요!",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent()
                intent.putExtra("SignUpNameExtra", signUpName.toString())
                intent.putExtra("SignUpIDExtra", signUpID.toString())
                intent.putExtra("SignUpPasswordExtra", signUpPassword.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}