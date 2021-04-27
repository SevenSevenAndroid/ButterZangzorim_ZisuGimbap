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

    private lateinit var binding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repoListFragment = RepoListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.example.secondassignment.R.id.repo_info_fragment, repoListFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        moreButtonClickEvent()
    }

    private fun moreButtonClickEvent() {
        binding.buttonHomeMore.setOnClickListener{
            val intent = Intent(this@HomeActivity, UserInfoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }




}