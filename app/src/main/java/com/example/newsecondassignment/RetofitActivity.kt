package com.example.newsecondassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsecondassignment.databinding.ActivityHomeBinding
import com.example.newsecondassignment.databinding.ActivityRetofitBinding

class RetofitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}