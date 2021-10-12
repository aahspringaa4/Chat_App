package com.example.chat_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.chat_app.databinding.ActivityHomeBinding
import com.example.chat_app.databinding.ActivityMainBinding

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