package ui.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat_app.databinding.ActivityAccountBinding
import com.example.chat_app.databinding.ActivityCheckBinding

class CheckActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}