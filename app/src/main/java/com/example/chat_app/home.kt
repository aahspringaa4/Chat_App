package com.example.chat_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chat_app.databinding.ActivityFriendListBinding
import com.example.chat_app.databinding.ActivityJoinBinding

class home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        return inflater.inflate(
            R.layout.activity_home,
            container,
            false
        ) //frag1과 연결시켜 return해줌.
    }
}