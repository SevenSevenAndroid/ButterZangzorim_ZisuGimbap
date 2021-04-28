package com.example.secondassignment.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignment.UserInfoActivity
import com.example.secondassignment.data.RepoInfo
import com.example.secondassignment.adapter.RepoListAdapter
import com.example.secondassignment.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchFollowers()

        val repoListAdapter = RepoListAdapter()
        binding.recyclerviewRepository.adapter = repoListAdapter

        repoListAdapter.repoList.addAll(
            listOf<RepoInfo>(
                RepoInfo(
                    repoName = "JisuRepo",
                    repoDesc = "learn kotlin",
                    repoLanguage = "kotlin"
                ),
                RepoInfo(
                    repoName = "ZisuRepository",
                    repoDesc = "learn android",
                    repoLanguage = "kotlin"
                ),
                RepoInfo(
                    repoName = "RepoJisu",
                    repoDesc = "make recyclerview",
                    repoLanguage = "java"
                ),
                RepoInfo(
                    repoName = "SOPT",
                    repoDesc = "Android",
                    repoLanguage = "kotlin 1.4.31"
                )
            )
        )

        repoListAdapter.notifyDataSetChanged()

    }
    fun searchFollowers(){
        binding.buttonHomeMore.setOnClickListener {
            val intent = Intent(this@HomeActivity, UserInfoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}