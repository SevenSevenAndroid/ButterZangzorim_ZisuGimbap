package com.example.secondassignment

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignment.databinding.ActivityRepoInfoBinding

class RepoInfoActivity:AppCompatActivity() {

    private lateinit var binding: ActivityRepoInfoBinding

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