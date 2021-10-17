package com.example.chat_app.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityHomeBinding
import com.example.chat_app.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.talk.setOnClickListener {
            var intent = Intent(this, ChattingActivity::class.java)
            startActivity(intent)
        }

        binding.tvChat.setOnClickListener {
            var intent = Intent(this, ChattingActivity::class.java)
            startActivity(intent)
        }
    }
}