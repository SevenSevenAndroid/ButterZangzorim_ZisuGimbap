package com.example.newsecondassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import com.example.newsecondassignment.databinding.ActivityUserInfoBinding

class UserInfoActivity:AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. UserInfoActivity에 FollowingListFragment 보여주기
        // 2-1. UserInfoActivity에서 보여줄 FollowingListFragment 생성
        val followingListFragment = FollowingListFragment()

        // 2-2. UserInfoActivity에서 FragmentManager가 Fragment를 관리할 transaction(작업 단위) 만들어주기
        val transaction = supportFragmentManager.beginTransaction()
        // 2-3. 해당 작업 단위에서 어떤 View(id 참조)에 어떤 Fragment를 보여줄 것인가
        // 2-4. 그것이 해당 뷰에 추가하는 일이라고 선언
        transaction.add(R.id.user_info_fragment, followingListFragment)
        transaction.commit()
    }
}