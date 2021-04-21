package com.example.firstassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firstassignment.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TAG","onCreate")

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpButtonClickEvent()
    }

    private fun signUpButtonClickEvent() {
        binding.signUpButton.setOnClickListener {

            setResult(Activity.RESULT_OK)

            val signUpUserName = binding.userNameInput.text
            val userGithubID = binding.githubIdInput.text
            val userSignUpPassword = binding.signUpPasswordInput.text

            if (signUpUserName.isNullOrBlank() && userGithubID.isNullOrBlank() && userSignUpPassword.isNullOrBlank()) {

                Toast.makeText(
                    this@SignUpActivity,
                    "빈 칸이 있는지 확인해주세요.",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (signUpUserName.isNotBlank() && userGithubID.isNotBlank() && userSignUpPassword.isNotBlank()) {

                Toast.makeText(
                    this@SignUpActivity,
                    "회원가입 성공",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, SignInActivity::class.java)

                intent.putExtra("userNameExtra", signUpUserName.toString())
                intent.putExtra("userGithubExtra", userGithubID.toString())
                intent.putExtra("userSignUpPassword", userSignUpPassword.toString())
                startActivity(intent)

            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onPause")
    }
}