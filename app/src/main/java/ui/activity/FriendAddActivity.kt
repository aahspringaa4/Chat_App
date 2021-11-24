package ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chat_app.databinding.ActivityFriendAddBinding
import model.dto.RequestFriendListDTO
import network.ApiService
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.activity.login.HomeActivity

class FriendAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendAddBinding

    private var ApiService: ApiService? = null
    private var retrofitClient: RetrofitClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.check.setOnClickListener{
            finish()
            pushPost()
        }
    }
    private fun pushPost() {

        var memberId = binding.edFriendId.text.toString()

        retrofitClient = RetrofitClient()

        ApiService = RetrofitClient.getRetrofitInterface()

        val call: Call<RequestFriendListDTO>? = ApiService?.FriendApply(memberId)
        call?.enqueue(object : Callback<RequestFriendListDTO?> {
            override fun onResponse(
                call: Call<RequestFriendListDTO?>,
                response: Response<RequestFriendListDTO?>
            ) {
                Toast.makeText(HomeActivity(), "myText", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<RequestFriendListDTO?>, t: Throwable) {
                Toast.makeText(HomeActivity(), "myText", Toast.LENGTH_SHORT).show();
            }
        })
    }
}