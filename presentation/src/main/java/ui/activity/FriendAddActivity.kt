package ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chat_app.databinding.ActivityFriendAddBinding
import com.example.RequestFriendListDTO
import com.example.api.ApiService
import com.example.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.activity.login.HomeActivity

class FriendAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendAddBinding

    private var ApiService: com.example.api.ApiService? = null
    private var retrofitClient: com.example.api.RetrofitClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.check.setOnClickListener{
//            finish()
//            pushPost()
//        }

        binding.check.setOnClickListener {
            finish()
        }
    }
    private fun pushPost() {

        var memberId = binding.edFriendId.text.toString()

        retrofitClient = com.example.api.RetrofitClient()

        ApiService = com.example.api.RetrofitClient.getRetrofitInterface()

        val call: Call<com.example.RequestFriendListDTO>? = ApiService?.FriendApply(memberId)
        call?.enqueue(object : Callback<com.example.RequestFriendListDTO?> {
            override fun onResponse(
                call: Call<com.example.RequestFriendListDTO?>,
                response: Response<com.example.RequestFriendListDTO?>
            ) {
                Toast.makeText(HomeActivity(), "myText", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<com.example.RequestFriendListDTO?>, t: Throwable) {
                Toast.makeText(HomeActivity(), "myText", Toast.LENGTH_SHORT).show();
            }
        })
    }
}