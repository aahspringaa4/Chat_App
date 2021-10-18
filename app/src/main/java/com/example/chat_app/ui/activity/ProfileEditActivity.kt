package com.example.chat_app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat_app.databinding.ActivityMypageBinding
import com.example.chat_app.databinding.ActivityProfileEditBinding

class ProfileEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbutton.setOnClickListener {
            finish()
        }

        binding.check.setOnClickListener {
            finish()
            changename()
        }
    }

    private fun changename(){

    }
}

