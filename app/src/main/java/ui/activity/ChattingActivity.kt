package ui.activity

import adapter.ChattingAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.databinding.ActivityChattingBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import model.dto.RequestEnterChattingRoomDTO
import model.dto.RequestSendMessageDTO
import network.SocketApplication
import io.socket.client.Socket
import model.data.ChattingData
import model.dto.RequestLeaveChatDTO
import network.RetrofitClient

class ChattingActivity() : AppCompatActivity() {

    private lateinit var socket: Socket
    private lateinit var binding: ActivityChattingBinding
    private lateinit var chatting: Array<String>
    private val adapter = RetrofitClient.getRetrofitInterface()
    private val chatBody = MutableLiveData<String>()
    private var saveChat = mutableListOf<ChattingData>()
    private val chattingList = MutableLiveData<List<ChattingData>>()
    private var index = 0
    private val chattingListAdapter = ChattingAdapter(chattingList, index)
    private var readChattingList = mutableListOf<ChattingData>()

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
        getChatting()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            val data = RequestLeaveChatDTO()
            socket.emit("leaveRoom", data)
        }finally {

        }
    }

    @SuppressLint("CheckResult")
    private fun getChatting() {
        val chattingRoomId = "adfbeefc-3307-4ccd-8dbf-3aa2401e4781"
        val count = 10
        adapter.getChatting(chattingRoomId , count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { response ->
                if (response.isSuccessful) {
                    response.body()?.let { readChattingList.addAll(it) }
                    saveChat = readChattingList.asReversed()
                    chattingList.value = saveChat
                    chattingListAdapter.notifyDataSetChanged()
                }
            }, {
            } )
    }

    private fun joinRoom() {
        val data = RequestEnterChattingRoomDTO()
        socket.emit("joinRoom", data)
    }

    private fun sendChatting() {
        if(!chatBody.value.isNullOrEmpty()){
            val message = chatBody.value
            val data = message?.let { RequestSendMessageDTO(messages = it) }
            socket.emit("sendMessage", data)
            chatBody.value = ""
        }
    }
}