# 28th BE SOPT

## Android Seminar week7 Assignment

![image](https://user-images.githubusercontent.com/70841402/121808894-8e07c780-cc95-11eb-821e-9b4bd7dc695b.png)

### Level1:안린이 탈출하기 - 1

1️⃣ SignIn Activity로 처음 들어왔을때 SharedPreference에서 ID/PW가 있다면?
-> 로그인 과정을 건너뛴다

https://user-images.githubusercontent.com/70841402/121809702-cd83e300-cc98-11eb-976d-e625f2ef4ba8.mp4

▪ SignInActivity
  ```kotlin
      override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("TAG", "onCreate")

        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchUserAuthStorage()
        setButtonEvent()
    }  
  
    private fun searchUserAuthStorage() {
        with(SoptUserAuthStorage.getInstance(this)) {
            if (hasUserData()) {
                requestLogin(getUserData().let { RequestLoginData(it.id, it.password) })
            }
        }
    }     
    ...
  ``` 
  
▪ SoptUserAuthStorage

  ```kotlin
    ...
    fun hasUserData(): Boolean {
        with(getUserData()) {
            return id.isNotEmpty() && password.isNotEmpty()
        }
    } 

    fun getUserData(): SoptUserInfo = SoptUserInfo(
        id = sharedPreferences.getString(USER_ID_KEY, "") ?: "",
        password = sharedPreferences.getString(USER_PW_KEY, "") ?: ""
    )    
    ...
  ```



2️⃣ 로그인할 때 성공하면 SharedPreference에 집어 넣는다.

▪ SignInActivity
  ```kotlin
  
      private fun setButtonEvent() {
        with(binding) {
            buttonSignIn.setOnClickListener { loginButtonClickEvent() }
            buttonSignUpStart.setOnClickListener { signUpButtonStartClickEvent() }
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
  ```
▪ SoptUserAuthStorage
  ```kotlin
    ...
    fun saveUserData(userData: SoptUserInfo) {
        editor.putString(USER_ID_KEY, userData.id)
            .putString(USER_PW_KEY, userData.password)
            .apply()
    } 
    ...
  ```


3️⃣ 서비스에서 로그아웃하면 SharedPreference를 clear한다.

https://user-images.githubusercontent.com/70841402/121809715-dc6a9580-cc98-11eb-9787-531ec6de4cf6.mp4

▪ HomeActivity
  ```kotlin
    private fun signOutButtonClickEvent() {
        binding.buttonSignOut.setOnClickListener {
            with(SoptUserAuthStorage.getInstance(this)) {
                clearAuthStorage()
            }
            val intent = Intent(this@HomeActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
  ```

▪ SoptUserAuthStorage
  ```kotlin
    ...
    fun clearAuthStorage() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
    ...
  ```

☑ 위와 같은 플로우로 마치 자동 로그인 처럼 할 수 있다.

### Level1:안린이 탈출하기 - 2

☑ 자신만의 방식으로 코드를 정리하고, SharedPreference 구현

  ```kotlin
class SoptUserAuthStorage private constructor(context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        "${context.packageName}.$STORAGE_KEY",
        Context.MODE_PRIVATE
    )

    private val editor = sharedPreferences.edit()

    fun saveUserData(userData: SoptUserInfo) {
        editor.putString(USER_ID_KEY, userData.id)
            .putString(USER_PW_KEY, userData.password)
            .apply()
    }

    fun getUserData(): SoptUserInfo = SoptUserInfo(
        id = sharedPreferences.getString(USER_ID_KEY, "") ?: "",
        password = sharedPreferences.getString(USER_PW_KEY, "") ?: ""
    )

    fun hasUserData(): Boolean {
        with(getUserData()) {
            return id.isNotEmpty() && password.isNotEmpty()
        }
    }

    fun clearAuthStorage() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    companion object {
        private const val STORAGE_KEY = "user_auth"
        private const val USER_ID_KEY = "user_id"
        private const val USER_PW_KEY = "user_pw"

        @Volatile
        private var instance: SoptUserAuthStorage? = null

        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: SoptUserAuthStorage(context).apply {
                instance = this
            }
        }
    }
}
  ```
  
☑ 확장함수

▪ RetrofitEnqueueUtil

  ```kotlin
fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> Unit,
    onError: ((stateCode: Int) -> Unit)? = null
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess.invoke(response.body() ?: return)
            } else {
                onError?.invoke(response.code())
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("NetworkTest", "error:$t")
        }
    })
}
  ```
  
▪ ToastUtil
  
  ```kotlin
fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        .show()
}
  ```
      
### 과제를 통해 배우고 성장한 내용
이번 세미나에서 SharedPreference에 대해 배우고, 과제에서는 직접 자동 로그인과 로그아웃을 구현해보았습니다. 앱을 실행할 때마다 아이디와 비밀번호를 입력하는 과정이 불편했는데, SharedPreference를 통해 이런 불편함을 해소할 수 있어서 매우 신기하고 유익한 시간이었습니다. 하지만 SharedPreference를 다루는 것에 아직 서툴고 어려워서 제 자신만의 코드를 구성한 것이 아니라 안드로이드파트장님의 코드를 많이 참고했다는 것이 아쉬웠습니다. 지금까지 배워온 것을 복습하고, 아직 서툰 것은 스스로 공부해나가고, 앞으로 있을 행사에 참여하면서 더 큰 성장을 할 수 있도록 노력하겠습니다. 감사합니다. 😊
