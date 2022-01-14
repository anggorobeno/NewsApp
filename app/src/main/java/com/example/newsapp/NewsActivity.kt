package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.newsapp.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    private var _binding: ActivityNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        navController = findNavController(R.id.nav_host_fragment)



    }
}