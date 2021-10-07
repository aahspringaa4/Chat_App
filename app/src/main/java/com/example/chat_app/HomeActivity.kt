package com.example.chat_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat_app.databinding.ActivityHomeBinding
import com.example.chat_app.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.frinend.setOnClickListener {
            supportFragmentManager.beginTransaction() .replace(R.id.view, FriendList()) .commit()
        }

        binding.my.setOnClickListener {
            supportFragmentManager.beginTransaction() .replace(R.id.view, Mypage()) .commit()
        }

        binding.chat.setOnClickListener {
            supportFragmentManager.beginTransaction() .replace(R.id.view, home()) .commit()
        }
    }
}