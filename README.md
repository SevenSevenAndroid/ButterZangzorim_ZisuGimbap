# 28th BE SOPT

## Android Seminar week2 Assignment

### Level1:안린이 탈출하기
#### HomeActivity 추가하기!
+ 2차 세미나 때 배운 내용을 바탕으로 유저 정보 아래 깃허브 레포지토리 띄워 보기
  + 우선 깃허브 레포지토리를 띄우기 위해, RecyclerView를 이용했다.
  + RecyclerView 작업 순서
    + 1. item 하나의 UI 확인하기
    
         : 3개의 TextView로 이루어진 것 확인
    + 2. 확인한 하나의 UI Layout 파일 만들기
          : 레포지터리 이름이 너무 긴경우 뒤에 ...이 나오도록 
          
          ```kotlin
          android:ellipsize="end"
          ```
          를 추가하는 것이 필요하다.                 
        
    + 3. item data class 만들기        
           
          ```kotlin
          data class RepoInfo (
            val repoName:String,
            val repoDesc:String,
            val repoLanguage:String
            )
          ```  

    + 4. UI의 요소가 들고있는 ViewHolder 만들기
    
          뷰 홀더: 뷰의 요소를 붙잡고 관리하는 역할
    + 5. RecyclerView Adapter 만들기
    
          어댑터: 데이터를 뷰 홀더 뷰와 연결해주는 역할
    + 6. RecyclerView 배치하기
    + 7. RecyclerView item 배치 확인하기 (LayoutManager)
    + 8. RecyclerView에 Adapter 연결하기
    + 9. adapter 데이터 갱신하기
    
    위의 작업 순서로 깃허브 레포지토리 RecyclerView를 만들었다. 
    
    이어서 activity_home.xml에 RecyclerView를 추가했다.
    
      ```kotlin             
              <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/recyclerview_repository"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_height="0dp"
                app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                tools:listitem="@layout/item_repo">              
      ``` 
      
      ![KakaoTalk_20210428_193355885](https://user-images.githubusercontent.com/70841402/116640869-7b1b8b00-a9a6-11eb-87ab-76620453c70b.gif)

      
    
+ 1차 세미나에서 만든 유저 정보 부분에 more이란 버튼 하나를 추가하기
  + more 버튼이 해야 할 일: 2차 세미나 시간에 만든 fragment를 가진 Activity를 띄워주기
    + 1. activity_home.xml에 버튼 추가하기
    
          ```kotlin
            <Button
            android:id="@+id/button_home_more"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="MORE"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent" />
          ```  
          
    + 2. HomeActivity에 button_home_more 버튼이 클릭됐을 때, 2차 세미나 시간에 만든 fragment를 가진 Activity를 띄우도록 기능 추가하기
    
          ```kotlin
            fun searchFollowers(){
                binding.buttonHomeMore.setOnClickListener {
                    val intent = Intent(this@HomeActivity, UserInfoActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
          ```       
          
          
          ![KakaoTalk_20210428_193401631](https://user-images.githubusercontent.com/70841402/116640878-81116c00-a9a6-11eb-98f0-b9a32fb2797c.gif)

          
          

