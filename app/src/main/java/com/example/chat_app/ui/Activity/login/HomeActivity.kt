package com.example.chat_app.ui.Activity.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityHomeBinding
import com.example.chat_app.ui.fragment.FriendList
import com.example.chat_app.ui.fragment.Mypage
import com.example.chat_app.ui.fragment.home

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFrag(0)

        binding.chat.setOnClickListener {
            setFrag(0)
        }

        binding.frinend.setOnClickListener {
            setFrag(1)
        }
        binding.my.setOnClickListener {
            setFrag(2)
        }
    }

    private fun setFrag(fragnum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragnum){
            0 -> {
                ft.replace(R.id.main_frame, home()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, FriendList()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, Mypage()).commit()
            }
        }
    }
}