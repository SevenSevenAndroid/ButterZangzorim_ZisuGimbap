package com.example.newsecondassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsecondassignment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moreButtonClickEvent()

        val repoListAdapter = RepoListAdapter()

        binding.recyclerviewRepo.adapter = repoListAdapter

        repoListAdapter.repoList.addAll(
            listOf<RepoInfo>(
                RepoInfo(
                    userRepoName = "이름이 너무 길다이름이 너무 길다이름이 너무 길다이름이 너무 길다이름이 너무 길다이름이 너무 길다이름이 너무 길다",
                    userRepoDescription = "awesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesome",
                    userRepoLanguage = "kt"
                ),
                RepoInfo(
                    userRepoName = "qqqqq",
                    userRepoDescription = "awesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesome",
                    userRepoLanguage = "kt"
                ),
                RepoInfo(
                    userRepoName = "qqqqq",
                    userRepoDescription = "awesome",
                    userRepoLanguage = "kt"
                ),
                RepoInfo(
                    userRepoName = "qqqqq",
                    userRepoDescription = "awesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesomeawesome",
                    userRepoLanguage = "kt"
                ),
                RepoInfo(
                    userRepoName = "qqqqq",
                    userRepoDescription = "awesome",
                    userRepoLanguage = "kt"
                ),
                RepoInfo(
                    userRepoName = "qqqqq",
                    userRepoDescription = "awesome",
                    userRepoLanguage = "kt"
                ),
                RepoInfo(
                    userRepoName = "qqqqq",
                    userRepoDescription = "awesome",
                    userRepoLanguage = "kt"
                ),
                RepoInfo(
                    userRepoName = "qqqqq",
                    userRepoDescription = "awesome",
                    userRepoLanguage = "kt"
                )
            )
        )
        repoListAdapter.notifyDataSetChanged()
    }

    private fun moreButtonClickEvent() {
        binding.buttonHomeMore.setOnClickListener{
            val intent = Intent(this@HomeActivity, UserInfoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}