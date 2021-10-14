package com.example.chat_app.ui.Activity.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibRegister.setOnClickListener {
            finish()
        }
    }
}