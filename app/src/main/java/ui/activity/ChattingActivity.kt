package ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat_app.R

class ChattingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)
    }
}