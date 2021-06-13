package com.example.newsecondassignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsecondassignment.R
import com.example.newsecondassignment.fragment.RepoListFragment
import com.example.newsecondassignment.databinding.ActivityRepoInfoBinding

class RepoInfoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRepoInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repoListFragment = RepoListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.repo_info_fragment, repoListFragment)
        transaction.commit()
    }
}