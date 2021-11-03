package ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            val id: String = binding.etRegisterId.getText().toString()
            val name: String = binding.etRegisterName.getText().toString()
            val password: String = binding.etRegisterPw.getText().toString()

            if (id.trim { it <= ' ' }.length == 0 || name.trim { it <= ' ' }.length == 0 || password.trim { it <= ' ' }.length == 0 || id == null || name == null || password == null) {
                Toast.makeText(this@AccountActivity, "회원가입 정보를 옳바르게 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, CheckActivity::class.java))
            }
        }

        binding.tvSignIn.setOnClickListener {
            finish()
        }
    }
}