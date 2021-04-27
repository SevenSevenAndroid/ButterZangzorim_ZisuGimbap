package com.example.secondassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignment.databinding.ActivityUserInfoBinding

class UserInfoActivity:AppCompatActivity() {

    private lateinit var binding:ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val followingListFragment = FollowingListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.user_info_fragment, followingListFragment)
        transaction.commit()
    }
}