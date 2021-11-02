package ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.databinding.ActivityJoinBinding


class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignIn.setOnClickListener{
            finish()
        }

        binding.ibRegister.setOnClickListener {
            startActivity(Intent (this, AccountActivity::class.java))
        }

        binding.ChooseSex.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.etRegisterSex.setText(parent?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {]
            }

        }
    }

    private fun Register() {

        val Birth: String = binding.etRegisterBirth.getText().toString()
        val phone: String = binding.etRegisterPhone.getText().toString()
        val sex: String = binding.etRegisterSex.getText().toString()

        hideKeyboard()
        
        if (Birth.trim { it <= ' ' }.length == 0 || phone.trim { it <= ' ' }.length == 0 || sex.trim { it <= ' ' }.length == 0 || Birth == null || phone == null || sex == null) {
            Toast.makeText(this@JoinActivity, "올바른 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            RegisterResponse()
        }
    }
}