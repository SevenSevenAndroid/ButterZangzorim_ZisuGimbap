package com.example.secondassignment.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.secondassignment.signup.SignUpActivity
import com.example.secondassignment.databinding.ActivitySignInBinding
import com.example.secondassignment.home.HomeActivity

class SignInActivity : AppCompatActivity() {

//    // registerForActivityResult 함수를 사용해서 Callback을 등록
//    // 인자로 들어가는 것은 ActivityResultContracts 클래스의 Static 함수들 -> Result를 받기 위해서 Activity를 실행하는 StartActivityForResult() 함수를 넣어줌
//    val resultContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? -> // 람다식에는 result로 받아온 값을 어떻게 사용하는지 정의
//        // 인자로 받아온 result 객체를 이용하면 resultCode와 data에 접근 가능 -> resultCode를 이용해서 RESULT_OK인지 확인 가능
//        if (result?.resultCode == Activity.RESULT_OK) {
//            Toast.makeText(
//                this@SignInActivity,
//                "아이디/비밀번호를 확인해주세요!",
//                Toast.LENGTH_SHORT
//            ).show()
//        } else {
//            val result = data?.getIntExtra("result", 0)
//
//            binding.editTextSignInIdInput.setText(data.getStringExtra("id"))
//        }
//    }

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("TAG","onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInButtonClickEvent()
        signUpButtonStartClickEvent()
    }

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

    private fun signUpButtonStartClickEvent() {
        binding.buttonSignUpStart.setOnClickListener{
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivityForResult(intent,200)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200) {
            binding.editTextSignInIdInput.setText(data!!.getStringExtra("id"))
            binding.editTextSignInPasswordInput.setText(data!!.getStringExtra("password"))
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