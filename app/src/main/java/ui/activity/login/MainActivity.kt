package ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.databinding.ActivityMainBinding
import com.example.RequestLoginDTO
import com.example.ResponseLoginDTO
import com.example.api.ApiService
import com.example.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var id: String? = null
    var password: String? = null
    var data: String? = null
    var count: Int? = null
    private var ApiService: com.example.api.ApiService? = null
    private var retrofitClient: com.example.api.RetrofitClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibNext.setOnClickListener {
//            Login()
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }

        binding.tvJoin.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        binding.ivJoin.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun Login() {
        id = binding.etPutID.getText().toString()
        password = binding.etPutPW.getText().toString()

        hideKeyboard()

        if (id.isNullOrBlank() || password.isNullOrBlank()) {
            Toast.makeText(this@MainActivity, "올바른 로그인 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            LoginResponse()
        }
    }

    private fun hideKeyboard() // 키보드 숨기기
    {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etPutID.getWindowToken(), 0)
        imm.hideSoftInputFromWindow(binding.etPutPW.getWindowToken(), 0)
    }

    fun LoginResponse() {

        val id: String = binding.etPutID.getText().toString()
        val password: String = binding.etPutPW.getText().toString()

        // 정보 저장
        val requestLogin = com.example.RequestLoginDTO(id, password)

        retrofitClient = com.example.api.RetrofitClient()

        ApiService = com.example.api.RetrofitClient.getRetrofitInterface()

        ApiService?.Login(requestLogin)?.enqueue(object : Callback<com.example.ResponseLoginDTO?> {
            override fun onResponse(
                call: Call<com.example.ResponseLoginDTO?>,
                response: Response<com.example.ResponseLoginDTO?>
            ) {
                Log.d("error", "error1")
                if (response.isSuccessful && response.body() != null) {

                    if (response.code() == 200) {
                        Toast.makeText(this@MainActivity, id + "님 환영합니다.", Toast.LENGTH_SHORT)
                            .show()

                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    }
                    else if (response.code() == 401) {
                        Toast.makeText(
                            this@MainActivity,
                            "로그인에 실패하였습니다.\n(아이디 또는 비밀번호를 다시 확인해주세요)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<com.example.ResponseLoginDTO?>?, t: Throwable?) {
                Log.d("error", "error10")
            }
        })
    }
}