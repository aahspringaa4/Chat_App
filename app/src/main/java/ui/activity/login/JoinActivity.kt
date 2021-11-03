package ui.activity.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.databinding.ActivityAccountBinding
import com.example.chat_app.databinding.ActivityJoinBinding
import model.RequestRegisterDTO
import model.ResponseLoginDTO
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

            val Birth: String = binding.etRegisterBirth.getText().toString()
            val phone: String = binding.etRegisterPhone.getText().toString()
            val sex: String = binding.etRegisterSex.getText().toString()

            if (Birth.trim { it <= ' ' }.length == 0 || phone.trim { it <= ' ' }.length == 0 || sex.trim { it <= ' ' }.length == 0 || Birth == null || phone == null || sex == null) {
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
                binding.etRegisterSex.setText(parent?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }





}