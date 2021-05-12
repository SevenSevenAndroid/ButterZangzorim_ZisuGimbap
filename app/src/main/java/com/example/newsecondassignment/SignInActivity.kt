package com.example.newsecondassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.newsecondassignment.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("TAG","onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInButtonClickEvent()
        signUpButtonStartClickEvent()
    }

    // 요기에 쓰는 거였네
    private fun signInButtonClickEvent() {
        binding.buttonSignIn.setOnClickListener {
            var signInID = binding.editTextSignInIdInput.text
            var signInPassword = binding.editTextSignInPasswordInput.text

            if (signInID.isNullOrBlank() || signInPassword.isNullOrBlank()) {
                Toast.makeText(
                        this@SignInActivity,
                        "아이디/비밀번호를 확인해주세요!",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                        this@SignInActivity,
                        "로그인 성공",
                        Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    // registerForActivityResult 함수를 사용해서 Callback을 등록
    // 인자로 들어가는 것은 ActivityResultContracts 클래스의 Static 함수들 -> Result를 받기 위해서 Activity를 실행하는 StartActivityForResult() 함수를 넣어줌
    val signUpActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == Activity.RESULT_OK){
            val userName = it.data?.getStringExtra("SignUpNameExtra")
            val userID = it.data?.getStringExtra("SignUpIDExtra")
            val userPassword = it.data?.getStringExtra("SignUpPasswordExtra")

            binding.editTextSignInIdInput.setText(userID)
            binding.editTextSignInPasswordInput.setText(userPassword)
        } else{
            Toast.makeText(
                    this@SignInActivity,
                    "회원가입에 실패했습니다!",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun signUpButtonStartClickEvent() {
        binding.buttonSignUpStart.setOnClickListener{
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }



}