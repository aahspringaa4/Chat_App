package ui.activity.login

import android.R.attr.password
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.databinding.ActivityAccountBinding
import com.example.chat_app.databinding.ActivityJoinBinding
import model.RequestRegisterDTO
import model.ResponseLoginDTO
import network.ApiService
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
        setContentView(binding2.root)

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

    public fun Register() {

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

    fun RegisterResponse() {
        val birth: String = binding.etRegisterBirth.getText().toString().trim()
        val phone: String = binding.etRegisterPhone.getText().toString().trim()
        val gender: String = binding.etRegisterSex.getText().toString().trim()

        val id: String = binding2.etRegisterId.getText().toString().trim()
        val name: String = binding2.etRegisterName.getText().toString().trim()
        val password: String = binding2.etRegisterPw.getText().toString().trim()

        // 정보 저장
        val requestRegister = RequestRegisterDTO(birth, phone, gender, id, name, password)
        val retrofitClient = RetrofitClient.getInstance()
        val ApiService = RetrofitClient.getRetrofitInterface()

        ApiService.Register(requestRegister).enqueue(object : Callback<ResponseLoginDTO?>() {
            override fun onResponse(
                call: Call<ResponseLoginDTO?>?,
                response: Response<ResponseLoginDTO?>
            ) {
                println("")
                if (response.isSuccessful() && response.body() != null) {

                    if (response.code() === 200) {
                        Toast.makeText(
                            this@JoinActivity,
                            "회원가입이 성공적으로 완료되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@JoinActivity,
                            "예기치 못한 오류가 발생했습니다.\n 고객센터에 문의해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLoginDTO?>?, t: Throwable?) {
                Toast.makeText(
                    this@JoinActivity,
                    "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun hideKeyboard() // 키보드 숨기기
    {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etRegisterBirth.getWindowToken(), 0)
        imm.hideSoftInputFromWindow(binding.etRegisterPhone.getWindowToken(), 0)
        imm.hideSoftInputFromWindow(binding.etRegisterSex.getWindowToken(), 0)
    }

}