package com.example.newsecondassignment.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsecondassignment.RepoInfo
import com.example.newsecondassignment.SoptUserAuthStorage
import com.example.newsecondassignment.SoptUserInfo
import com.example.newsecondassignment.adapter.RepoListAdapter
import com.example.newsecondassignment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moreButtonClickEvent()
        logoutButtonClickEvent()

        val repoListAdapter = RepoListAdapter()

        binding.recyclerviewRepo.adapter = repoListAdapter

        repoListAdapter.repoList.addAll(
            listOf<RepoInfo>(
                RepoInfo(
                    userRepoName = "안드로이드 과제 1",
                    userRepoDescription = "킹갓제너럴엠페러마제스티골져스프레셔스뷰리풀하이클래스엘레강스럭셔리클래식지니어스원더풀러블리월드탑클래스코드",
                    userRepoLanguage = "kotlin"
                ),
                RepoInfo(
                    userRepoName = "안드로이드 과제 2",
                    userRepoDescription = "킹갓제너럴엠페러마제스티골져스프레셔스뷰리풀하이클래스엘레강스럭셔리클래식지니어스원더풀러블리월드탑클래스코드",
                    userRepoLanguage = "kotlin"
                ),
                RepoInfo(
                    userRepoName = "안드로이드 과제 3",
                    userRepoDescription = "킹갓제너럴엠페러마제스티골져스프레셔스뷰리풀하이클래스엘레강스럭셔리클래식지니어스원더풀러블리월드탑클래스코드",
                    userRepoLanguage = "kotlin"
                ),
                RepoInfo(
                    userRepoName = "안드로이드 과제 4",
                    userRepoDescription = "킹갓제너럴엠페러마제스티골져스프레셔스뷰리풀하이클래스엘레강스럭셔리클래식지니어스원더풀러블리월드탑클래스코드",
                    userRepoLanguage = "kotlin"
                ),
                RepoInfo(
                    userRepoName = "안드로이드 과제 5",
                    userRepoDescription = "킹갓제너럴엠페러마제스티골져스프레셔스뷰리풀하이클래스엘레강스럭셔리클래식지니어스원더풀러블리월드탑클래스코드",
                    userRepoLanguage = "kotlin"
                ),
                RepoInfo(
                    userRepoName = "안드로이드 과제 6",
                    userRepoDescription = "킹갓제너럴엠페러마제스티골져스프레셔스뷰리풀하이클래스엘레강스럭셔리클래식지니어스원더풀러블리월드탑클래스코드",
                    userRepoLanguage = "kotlin"
                ),
                RepoInfo(
                    userRepoName = "안드로이드 과제 7",
                    userRepoDescription = "킹갓제너럴엠페러마제스티골져스프레셔스뷰리풀하이클래스엘레강스럭셔리클래식지니어스원더풀러블리월드탑클래스코드",
                    userRepoLanguage = "kotlin"
                )
            )
        )
        repoListAdapter.notifyDataSetChanged()
    }

    private fun logoutButtonClickEvent() {
        binding.buttonSignOut.setOnClickListener {
            with(SoptUserAuthStorage.getInstance(this)) {
                clearAuthStorage()
            }
            val intent = Intent(this@HomeActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun moreButtonClickEvent() {
        binding.buttonHomeMore.setOnClickListener{
            val intent = Intent(this@HomeActivity, UserInfoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}