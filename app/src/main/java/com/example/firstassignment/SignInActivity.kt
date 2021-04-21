package com.example.firstassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firstassignment.databinding.ActivitySignInBinding
import kotlinx.android.synthetic.main.activity_sign_in.*
// import androidx.databinding.library.baseAdapters.BR

class SignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignInBinding

    val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result: ActivityResult? ->
        if (result?.resultCode == Activity.RESULT_OK) {
            Toast.makeText(
                    this@SignInActivity,
                    "데이터 전송 성공",
                    Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                    this@SignInActivity,
                    "데이터 전송 실패",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TAG","onCreate")

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginButtonClickEvent()
//        signinButtonClickEvent()

        if (intent.hasExtra("userNameExtra")) {
            val userName = intent.getStringExtra("userNameExtra")
        }
        if (intent.hasExtra("userGithubExtra")) {
            var userID = intent.getStringExtra("userGithubExtra")
        }
        if (intent.hasExtra("userSignUpPassword")) {
            var userPassword = intent.getStringExtra("userSignUpPassword")
        }

    }

    private fun loginButtonClickEvent() {
        binding.btnLogin.setOnClickListener {
            var userID = binding.etLoginGithubID.text
            var userPassword = binding.etLoginPassword.text
            if (userID.isNullOrBlank() || userPassword.isNullOrBlank()) {
                Toast.makeText(
                    this@SignInActivity,
                    "아이디/비밀번호를 확인해주세요!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(
                    this@SignInActivity,
                    "로그인 성공",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

//    private fun signinButtonClickEvent() {
//        binding.btnSignin.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
//        }
//    }

    private fun newSigninButtonClickEvent() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultContract.launch(intent)


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