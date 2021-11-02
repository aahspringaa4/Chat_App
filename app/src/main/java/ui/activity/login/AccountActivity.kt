package ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityAccountBinding
import com.example.chat_app.databinding.ActivityJoinBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibRegister.setOnClickListener {
            startActivity(Intent(this, CheckActivity::class.java))
        }

        binding.tvSignIn.setOnClickListener {
            finish()
        }
    }
}