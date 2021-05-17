package com.example.newsecondassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
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