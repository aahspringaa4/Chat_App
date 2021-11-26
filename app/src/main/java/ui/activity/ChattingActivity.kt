package ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.databinding.ActivityChattingBinding
import model.dto.RequestEnterChattingRoomDTO
import model.dto.RequestSendMessageDTO
import network.SocketApplication
import java.net.Socket

class ChattingActivity : AppCompatActivity() {

    private lateinit var socket: Socket

    val chatBody = MutableLiveData<String>()

    private lateinit var binding: ActivityChattingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        socket = SocketApplication.get()

        socket.connect()

        binding.btSend.setOnClickListener {
            sendChatting()
        }

        joinRoom()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun joinRoom() { // 방 입장 소켓
        val data = RequestEnterChattingRoomDTO()
        socket.emit("joinFriendRoom", data)
    }


    fun sendChatting() { // 보내기 버튼 누르면 실행 소켓
        if(!chatBody.value.isNullOrEmpty()){
            val message = chatBody.value
            val data = message?.let { RequestSendMessageDTO(messages = it) }
            socket.emit("sendMessage", data)
            chatBody.value = ""
        }
    }

}