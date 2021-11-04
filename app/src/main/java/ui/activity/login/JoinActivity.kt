package ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.databinding.ActivityAccountBinding
import com.example.chat_app.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding
    private lateinit var binding2: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        binding2 = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignIn.setOnClickListener {
            finish()
        }

        binding.ibRegister.setOnClickListener {

            val birth: String = binding.etRegisterBirth.text.toString()
            val phone: String = binding.etRegisterPhone.text.toString()
            val sex: String = binding.etRegisterSex.text.toString()

            if (birth.trim { it <= ' ' }.isEmpty() || phone.trim { it <= ' ' }.isEmpty() || sex.trim { it <= ' ' }
                    .isEmpty()) {
                Toast.makeText(this@JoinActivity, "올바른 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, AccountActivity::class.java))
            }
        }

        binding.ChooseSex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.etRegisterSex.text = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }





}