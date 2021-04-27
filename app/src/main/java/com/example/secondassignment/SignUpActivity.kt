package com.example.secondassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.secondassignment.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signUpButtonFinishClickEvent()

    }

    private fun signUpButtonFinishClickEvent() {
        binding.buttonSignUpFinish.setOnClickListener {
            var signUpName = binding.editTextSignUpNameInput.text
            var signUpID = binding.edieTextSignUpIdInput.text
            var signUpPassword = binding.edieTextSignUpPasswordInput.text

            if (signUpName.isNullOrBlank() || signUpID.isNullOrBlank()||signUpPassword.isNullOrBlank()) {
                Toast.makeText(
                    this@SignUpActivity,
                    "빈 칸이 있는지 확인해주세요!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intentSignUpToSignIn = Intent()
                intentSignUpToSignIn.putExtra("SignUpNameExtra", signUpName.toString())
                intentSignUpToSignIn.putExtra("SignUpIDExtra", signUpID.toString())
                intentSignUpToSignIn.putExtra("SignUpPasswordExtra", signUpPassword.toString())

                setResult(Activity.RESULT_OK, intentSignUpToSignIn)
                finish()
            }
        }

    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }


}