package com.example.chat_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat_app.databinding.ActivityFriendListBinding
import com.example.chat_app.databinding.ActivityJoinBinding

class FriendListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chat.setOnClickListener {
            finish()
        }
    }
}