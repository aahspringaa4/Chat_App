package ui.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.chat_app.databinding.ActivityAccountBinding
import model.RequestRegisterDTO
import model.ResponseRegisterDTO
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivity : AppCompatActivity() {

    private lateinit var binding2: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding2 = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        binding2.ibRegister.setOnClickListener {
            val id: String = binding2.etRegisterId.text.toString()
            val name: String = binding2.etRegisterName.text.toString()
            val password: String = binding2.etRegisterPw.text.toString()

            val birth: String = binding2.etRegisterBirth.text.toString()
            val phone: String = binding2.etRegisterPhone.text.toString()
            val sex: String = binding2.etRegisterSex.text.toString()

            if (id.trim { it <= ' ' }.isEmpty() || name.trim { it <= ' ' }
                    .isEmpty() || password.trim { it <= ' ' }
                    .isEmpty() || birth.trim { it <= ' ' }.isEmpty() || phone.trim { it <= ' ' }
                    .isEmpty() || sex.trim { it <= ' ' }
                    .isEmpty()) {
                Toast.makeText(this@AccountActivity, "회원가입 정보를 옳바르게 입력해주세요.", Toast.LENGTH_SHORT)
                    .show()

                Log.d("Error", "Error3")
            } else {
                hideKeyboard()

                RegisterResponse()

                Log.d("Error", "Error4")
            }
        }

        binding2.tvSignIn.setOnClickListener {
            finish()
        }
    }
    fun RegisterResponse() {
        val birth: String = binding2.etRegisterBirth.text.toString()
        val phone: String = binding2.etRegisterPhone.text.toString()
        val gender: String = binding2.etRegisterSex.text.toString()

        val id: String = binding2.etRegisterId.text.toString()
        val name: String = binding2.etRegisterName.text.toString()
        val password: String = binding2.etRegisterPw.text.toString()

        Log.d("Error", "Error1")
        // 정보 저장
        val requestRegister = RequestRegisterDTO(birth, phone, gender, name, id, password)
        val retrofitClient = RetrofitClient.getInstance()
        val apiService = RetrofitClient.getRetrofitInterface()

        apiService.Register(requestRegister).enqueue(object : Callback<ResponseRegisterDTO> {

            override fun onResponse(
                call: Call<ResponseRegisterDTO?>,
                response: Response<ResponseRegisterDTO?>
            ) {
                Log.d("Error", "Error")
                if (response.code() != 404) {

                    if (response.code() == 200) {
                        Toast.makeText(
                            this@AccountActivity,
                            "회원가입이 성공적으로 완료되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()

                        Log.d("Error", "Error5")
                        finish()
                    } else {
                        Toast.makeText(
                            this@AccountActivity,
                            "예기치 못한 오류가 발생했습니다.\n 고객센터에 문의해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@AccountActivity,
                        "이미 사용중인 아이디입니다 ㅠ", Toast.LENGTH_SHORT
                    ).show()

                    Log.d("Error", "Error8")
                }
            }

            override fun onFailure(call: Call<ResponseRegisterDTO?>, t: Throwable) {
                Toast.makeText(
                    this@AccountActivity,
                    "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("Error", "Error9")
            }
        })
    }

    private fun hideKeyboard() // 키보드 숨기기
    {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding2.etRegisterId.windowToken, 0)
        imm.hideSoftInputFromWindow(binding2.etRegisterPw.windowToken, 0)
        imm.hideSoftInputFromWindow(binding2.etRegisterName.windowToken, 0)
        imm.hideSoftInputFromWindow(binding2.etRegisterBirth.windowToken, 0)
        imm.hideSoftInputFromWindow(binding2.etRegisterPhone.windowToken, 0)
        imm.hideSoftInputFromWindow(binding2.etRegisterSex.windowToken, 0)
    }
}
