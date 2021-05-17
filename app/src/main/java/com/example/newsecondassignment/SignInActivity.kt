package com.example.newsecondassignment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.newsecondassignment.api.ServiceCreator
import com.example.newsecondassignment.databinding.ActivitySignInBinding
import com.example.newsecondassignment.request.RequestLoginData
import com.example.newsecondassignment.response.ResponseLoginData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                inputLoginInformation()
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

    private fun inputLoginInformation(){

        // 서버로 보낼 id(email), password를 dataClass로 묶어준다
        val requestLoginData = RequestLoginData(
            id = binding.editTextSignInIdInput.text.toString(),
            password = binding.editTextSignInPasswordInput.text.toString()
        )

        // 현재 사용자의 정보를 받아올 것을 암시
        // 서버 통신은 I/O 작업 -> 비동적으로 받아올 Callback 내부 코드는 나중에
        // 데이터를 받아오고 실행된다
        val call: Call<ResponseLoginData> = ServiceCreator.soptService.postLogin(requestLoginData)

        // enqueue 함수 -> Call이 비동기 작업 이후, 동작할 Callback을 등록할 수 있다
        // 해당 함수 호출은 Callback을 등록만하고
        // 실제 서버 통신을 요청한 이후, 통신 결과가 나왔을 때 실행된다
        // object 키워드로 Callback을 구현할 익명 클래스 생성
        call.enqueue(object : Callback<ResponseLoginData> {

            // 네트워크 통신 Response가 있는 경우, 해당 함수를 retrofit이 호출
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ) {
                // 네트워크 통신에 성공한 경우, status 코드가 200~300일 때, 실행
                if (response.isSuccessful) {
                    // response body 자체가 nullable 데이터
                    // 서버에서 오는 data도 nullable
                    val data = response.body()?.data
                    // 통신 성공시 유저 닉네임을 보여준다
                    Toast.makeText(this@SignInActivity, data?.user_nickname, Toast.LENGTH_SHORT).show()
                    // 홈 화면으로 넘어감
                    startHomeActivity()
                } else {
                    // 네트워크 통신에 실패한 경우, status 코드가 200~300이 아닌 경우
                    Toast.makeText(
                        this@SignInActivity,
                        "네트워크 통신 실패",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // 네트워크 통신 자체가 실패한 경우, 해당 함수를 retrofit이 실행
            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })
    }

    // 홈 화면으로 넘어감
    private fun startHomeActivity(){
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