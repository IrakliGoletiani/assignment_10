package com.example.sammary3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sammary3.databinding.ActivityUserBinding

class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}