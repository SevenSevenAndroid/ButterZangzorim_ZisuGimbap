package com.example.secondassignment

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignment.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

}