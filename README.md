# 28th BE SOPT

## Android Seminar week4 Assignment

![캡처](https://user-images.githubusercontent.com/70841402/118386974-45d59500-b656-11eb-9df7-f9f6120e1ca0.PNG)

### Level1:안린이 탈출하기
* 해당 링크를 보면서 로그인, 회원가입 통신 구현하기

  ✔ [Retrofit2 공식문서](http://devflow.github.io/retrofit-kr/)
  
  ✔ [SOPT signIn/signUp](https://www.notion.so/API-f960755d414d4c8181c2e0516c4a82a7)
  
* PostMan 테스트 사진
  - SignIn 
      ![화면 캡처 2021-05-16 163912](https://user-images.githubusercontent.com/70841402/118389763-6c9bc780-b666-11eb-9248-de6d5da7bd03.png)
      
  - SignUp
      ![화면 캡처 2021-05-16 155732](https://user-images.githubusercontent.com/70841402/118389767-76252f80-b666-11eb-9e72-8d80c31b9bc1.png)
  
* 회원가입 완료 + 로그인 완료 구현 gif
  - 회원가입 완료
   
    ![ezgif com-gif-maker](https://user-images.githubusercontent.com/70841402/118389837-d7e59980-b666-11eb-9cb5-3ce33eb054b8.gif)
    
  - 로그인 완료
   
    ![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/70841402/118389903-3ca0f400-b667-11eb-8986-79a937c8c14a.gif)
    
* retrofit interface

  ```kotlin             
  interface SoptService {
      @POST("/login/signin")
      fun postLogin(
          @Body body:RequestLoginData
      ): Call<ResponseLoginData>

      // 서버에 POST라는 행위를 요청
      // /login/signup이란 식별자에 해당하는 데이터를 body에 담아 보낸다
      @POST("/login/signup")
      fun postSignUp(
          @Body body:RequestSignUpData
      ):Call<ResponseSignUpData>
  }          
  ``` 
  
    ```kotlin             
  object ServiceCreator {
      private const val BASE_URL = "http://cherishserver.com"

      private val retrofit:Retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()

      val soptService:SoptService = retrofit.create(SoptService::class.java)
  }       
  ``` 

*  callback 연결 부분

    - SignIn
  
      ```kotlin             
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
          }```


    - SignUp
    
      ```kotlin             
      private fun inputSignUpInformation() {
          val requestSignUpData = RequestSignUpData(
              email = binding.editTextSignUpIdInput.toString(),
              password = binding.editTextSignUpPasswordInput.toString(),
              sex = if (binding.radioButtonMan.isChecked()) "0" else "1",
              nickname = binding.editTextSignUpNicknameInput.toString(),
              phone = binding.editTextSignUpPhoneInput.toString(),
              birth = binding.textViewSignUpBirthInput.toString()
          )

          val call: Call<ResponseSignUpData> =
              ServiceCreator.soptService.postSignUp(requestSignUpData)

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
                      intent.putExtra(
                          "SignUpNameExtra",
                          binding.editTextSignUpNameInput.text.toString()
                      )
                      intent.putExtra("SignUpIDExtra", binding.editTextSignUpIdInput.text.toString())
                      intent.putExtra(
                          "SignUpPasswordExtra",
                          binding.editTextSignUpPasswordInput.text.toString()
                      )
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
      }```
      
### 과제를 통해 배우고 성장한 내용
안드로이드 스튜디오에서 벗어나(?) 서버와 통신해볼 수 있어서 정말 신기하고 재밌었습니다. 하지만 완벽히 이해하고 선택 과제에서 안내해주신 것과 같은 다양한 api를 활용하기 위해서는 많은 노력이 필요할 것 같습니다. 이번에 수행한 필수 과제에 대한 이해가 더 깊어지면, '서버 연결은 여러번해서 익숙해지는 것 말고는 답이 없다.'라고 말씀하신 것처럼 여러번 시도해보고 실패해보고 에러도 마주쳐보겠습니다. 그리고 이 과정에서 에러를 해결하는 방법을 숙지하면, 서버 통신에 대해 조금 더 알아갈 수 있을 것 같습니다.
