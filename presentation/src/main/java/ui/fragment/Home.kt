package ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import adapter.ChattingListAdapter
import com.google.gson.JsonObject
import model.data.ChattingListData
import model.data.FriendListData
import model.dto.RequestChattingListDTO
import model.dto.ResponseFriendListDTO
import network.ApiService
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class home : Fragment() {
    lateinit var rv: RecyclerView
    lateinit var ChattingListAdapter: ChattingListAdapter
    val datas = mutableListOf<ChattingListData>()
    var arrayList: ArrayList<ChattingListData>? = null

    private var ApiService: ApiService? = null
    private var retrofitClient: RetrofitClient? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_chatlist, container, false) //frag1과 연결시켜 return해줌.
        rv = view.findViewById(R.id.rv)
        return view
    }

    private fun StartSetPost(serverResponse: ResponseFriendListDTO) {
        val totalElements: Int = serverResponse.getBoardInfos()!!.size
        for (i in 0 until totalElements) {
            val jsonObject: JsonObject? = serverResponse.getBoardInfos()?.get(i)
            val name = jsonObject?.get("name").toString()
            var content = jsonObject?.get("content").toString()
            var img = jsonObject?.get("img").toString()
            // val FriendList = FriendListData( name, content, img)
            //arrayList!!.add(FriendList)
            ChattingListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        arrayList?.clear()
        ChattingListAdapter?.notifyDataSetChanged()

        var AccessToken : String = "Authorization=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjM0IiwiYXVkIjoiTE9DQUwiLCJpc3MiOiJBZG1pbiIsImV4cCI6MTYzNzk4OTcxNn0.m8JEP4VyWqtqHG6QVda78Me-l453g77uZk1YJymrGIfF0eomzNtw59LdFb69D1wx9dlrn50YP6htmOOC6-Tshw"
        var RequestChattingListDTO = RequestChattingListDTO(AccessToken)
        retrofitClient = RetrofitClient()

        ApiService = RetrofitClient.getRetrofitInterface()

        val call: Call<ResponseFriendListDTO>? = ApiService?.ChatRoomList(RequestChattingListDTO)
        call?.enqueue(object : Callback<ResponseFriendListDTO?> {
            override fun onResponse(call: Call<ResponseFriendListDTO?>?, response: Response<ResponseFriendListDTO?>) {
                StartSetPost(ResponseFriendListDTO())
            }

            override fun onFailure(call: Call<ResponseFriendListDTO?>?, t: Throwable?) {

            }
        })
    }
}