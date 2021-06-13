package com.example.newsecondassignment.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.newsecondassignment.SoptUserAuthStorage
import com.example.newsecondassignment.SoptUserInfo
import com.example.newsecondassignment.activity.HomeActivity
import com.example.newsecondassignment.activity.SignUpActivity
import com.example.newsecondassignment.api.ServiceCreator
import com.example.newsecondassignment.databinding.ActivitySignInBinding
import com.example.newsecondassignment.request.RequestLoginData
import com.example.newsecondassignment.response.ResponseLoginData
import com.example.newsecondassignment.utils.enqueueUtil
import com.example.newsecondassignment.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("TAG", "onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchUserAuthStorage()
        setButtonEvent()
    }

    private fun setButtonEvent() {
        with(binding) {
            buttonSignIn.setOnClickListener { loginButtonClickEvent() }
            buttonSignUpStart.setOnClickListener { signUpButtonStartClickEvent() }
        }
    }

    private fun searchUserAuthStorage() {
        with(SoptUserAuthStorage.getInstance(this)) {
            if (hasUserData()) {
                requestLogin(getUserData().let { RequestLoginData(it.id, it.password) })
            }
        }
    }

    private fun loginButtonClickEvent() {
        val requestLoginData = RequestLoginData(
            id = binding.editTextSignInIdInput.text.toString(),
            password = binding.editTextSignInPasswordInput.text.toString()
        )
        requestLogin(requestLoginData)
    }

    private fun requestLogin(requestLoginData: RequestLoginData) {
        val call: Call<ResponseLoginData> = ServiceCreator.soptService
            .postLogin(requestLoginData)
        call.enqueueUtil(
            onSuccess = { response ->
                val data = response.data
                showToast(data?.user_nickname.toString())
                with(SoptUserAuthStorage.getInstance(this)) {
                    saveUserData(requestLoginData.let { SoptUserInfo(it.id, it.password) })
                }
                startHomeActivity()
            }
        )
    }

    // registerForActivityResult 함수를 사용해서 Callback을 등록
    // 인자로 들어가는 것은 ActivityResultContracts 클래스의 Static 함수들 -> Result를 받기 위해서 Activity를 실행하는 StartActivityForResult() 함수를 넣어줌
    val signUpActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val userName = it.data?.getStringExtra("SignUpNameExtra")
                val userID = it.data?.getStringExtra("SignUpIDExtra")
                val userPassword = it.data?.getStringExtra("SignUpPasswordExtra")

                binding.editTextSignInIdInput.setText(userID)
                binding.editTextSignInPasswordInput.setText(userPassword)
            } else {
                Toast.makeText(
                    this@SignInActivity,
                    "회원가입에 실패했습니다!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private fun signUpButtonStartClickEvent() {
        binding.buttonSignUpStart.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    private fun startHomeActivity() {
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