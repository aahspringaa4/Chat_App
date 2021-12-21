package ui.activity.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityHomeBinding
import io.socket.client.IO
import io.socket.client.Manager
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.Transport
import ui.activity.MypageActivity
import ui.fragment.FriendList
import ui.fragment.home
import java.net.URISyntaxException
import java.util.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFrag(0)

        binding.chat.setOnClickListener {
            setFrag(0)
        }

        binding.frinend.setOnClickListener {
            setFrag(1)
        }

        binding.my.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }
    }

    fun startSocket(accessToken: String) {
        try {
            val opts = IO.Options()
            opts.transports =
                arrayOf(io.socket.engineio.client.transports.WebSocket.NAME) // xhr에러 방지
            var socket = IO.socket("https://api.private", opts)
            socket.io().on(Manager.EVENT_TRANSPORT, Emitter.Listener { args ->
                val trans = args[0] as Transport
                trans.on(Transport.EVENT_REQUEST_HEADERS) { // request 해더 넣는 부분
                        args ->
                    val mHeaders = args[0] as MutableMap<String, List<String>>
                    println("여기가 실행${accessToken}")
                    mHeaders["Authorization"] = Arrays.asList("Bearer ${accessToken}")
                }
            })

            socket.on(Socket.EVENT_CONNECT) {
                println("성공")
            }.on(Socket.EVENT_CONNECT_ERROR) {
                println("실패;;")
                println(it.contentToString())
            }
            socket.on("response", event)
            socket.on("alarm", alarm)
            socket.connect()
        } catch (e: URISyntaxException) {
            println(e.reason)
        }
    }


    private fun setFrag(fragnum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragnum){
            0 -> {
                ft.replace(R.id.main_frame, home()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, FriendList()).commit()
            }
        }
    }
}