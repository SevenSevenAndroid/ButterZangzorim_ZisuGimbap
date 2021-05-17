package com.example.newsecondassignment

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.newsecondassignment.api.ServiceCreator
import com.example.newsecondassignment.databinding.ActivitySignUpBinding
import com.example.newsecondassignment.request.RequestSignUpData
import com.example.newsecondassignment.response.ResponseSignUpData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpEvent()
        showDatePicker()
    }

    private fun signUpEvent() {
        var signUpName = binding.editTextSignUpNameInput.text
        var signUpID = binding.editTextSignUpIdInput.text
        var signUpPassword = binding.editTextSignUpPasswordInput.text

        binding.buttonSignUpFinish.setOnClickListener {
            if (signUpName.isNullOrBlank() || signUpID.isNullOrBlank() || signUpPassword.isNullOrBlank()) {
                Toast.makeText(
                    this@SignUpActivity,
                    "빈 칸이 있는지 확인해주세요!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                inputSignUpInformation()
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.textViewSignUpBirthInput.setOnClickListener {
            val listener =
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, y, m, d ->
                    binding.textViewSignUpBirthInput.setText("" + y + "-" + m + "-" + d)
                }, year, month, day)

            listener.show()

        }
    }

    private fun inputSignUpInformation() {
        val requestSignUpData = RequestSignUpData(
            email = binding.editTextSignUpIdInput.toString(),
            password = binding.editTextSignUpPasswordInput.toString(),
            sex = if (binding.radioButtonMan.isChecked()) "0" else "1",
            nickname = binding.editTextSignUpNicknameInput.toString(),
            phone = binding.editTextSignUpPhoneInput.toString(),
            birth = binding.textViewSignUpBirthInput.toString()
        )

        val call: Call<ResponseSignUpData> = ServiceCreator.soptService.postSignUp(requestSignUpData)

        call.enqueue(object : Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ) {
                if (response.isSuccessful) {

                    Toast.makeText(
                        this@SignUpActivity,
                        "회원가입 완료",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent()
                    intent.putExtra("SignUpNameExtra", binding.editTextSignUpNameInput.text.toString())
                    intent.putExtra("SignUpIDExtra", binding.editTextSignUpIdInput.text.toString())
                    intent.putExtra("SignUpPasswordExtra", binding.editTextSignUpPasswordInput.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()

                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "네트워크 통신 실패",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }
        })
    }
}