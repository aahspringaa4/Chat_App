package ui.activity

import adapter.ChattingAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.databinding.ActivityChattingBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.socket.client.IO
import io.socket.client.Manager
import model.dto.RequestEnterChattingRoomDTO
import model.dto.RequestSendMessageDTO
import network.SocketApplication
import io.socket.client.Socket
import io.socket.emitter.Emitter
import io.socket.engineio.client.Transport
import model.data.ChattingData
import model.dto.RequestLeaveChatDTO
import network.RetrofitClient
import org.json.JSONObject
import java.net.URISyntaxException

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

    fun startSocket(accessToken: String) {
        try {
            val opts = IO.Options()
            opts.transports =
                arrayOf(io.socket.engineio.client.transports.WebSocket.NAME) // xhr에러 방지
            socket = IO.socket("https://api.private", opts)
            socket.io().on(Manager.EVENT_TRANSPORT, Emitter.Listener { args ->
                val trans = args[0] as Transport
                trans.on(Transport.EVENT_REQUEST_HEADERS) { // request 해더 넣는 부분
                        args ->
                    val mHeaders = args[0] as MutableMap<String, List<String>>
                    println("여기가 실행${accessToken}")
                    mHeaders["Authorization"] = listOf("Bearer $accessToken")
                }
            })
            socket.on(Socket.EVENT_CONNECT) {
                println("성공")
            }.on(Socket.EVENT_CONNECT_ERROR) {
                println("실패;;")
                println(it.contentToString()) // 이게 에러 받는거입니다
            }
            socket.on("response", connect)
            socket.connect()
            socket.on("recv_chat", chat).apply {
            }
            socket.on("error", connect)
        } catch (e: URISyntaxException) {
            println(e.reason)
        }
    }

    val connect: Emitter.Listener = Emitter.Listener {
        println("헬퍼 성공?")
        val size = it.size - 1
        val data = it
        for (i in 0..size) {
            println("${data[i]} 이게 연결 결과값")
        }
    }

    @SuppressLint("SimpleDateFormat")
    val chat: Emitter.Listener = Emitter.Listener {

        val json = JSONObject(it[0].toString())
        var result = true
        val title = json.getString("title")
        val msg = json.getString("msg")
        val user_type = json.getString("user_type")
        val date = json.getString("date")
        if(json.isNull("result")){
        }else{
            result = json.getBoolean("result")
            try {
                userResult2.value = result
            }catch (e: Throwable){}
        }

        try {
            chatInfo = ChattingData(title,msg,result,user_type,date)
            possingChat.add(chatInfo)
            chattingList.postValue(possingChat)
            chattingListAdapter.notifyDataSetChanged()
            navigater.binding.chatPageRv.scrollToPosition(possingChat.size)
        }catch (e:Throwable){}
        navigater.binding.chatPageRv.smoothScrollToPosition(possingChat.size)
    }
}