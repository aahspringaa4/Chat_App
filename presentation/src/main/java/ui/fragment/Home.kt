package ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import adapter.ChattingListAdapter
import com.google.gson.JsonObject
import com.example.ChattingListData
import com.example.RequestChattingListDTO
import com.example.ResponseFriendListDTO
import com.example.api.ApiService
import com.example.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class home : Fragment() {
    lateinit var rv: RecyclerView
    lateinit var ChattingListAdapter: ChattingListAdapter
    val datas = mutableListOf<com.example.ChattingListData>()
    var arrayList: ArrayList<com.example.ChattingListData>? = null

    private var ApiService: com.example.api.ApiService? = null
    private var retrofitClient: com.example.api.RetrofitClient? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_chatlist, container, false) //frag1과 연결시켜 return해줌.
        rv = view.findViewById(R.id.rv)
        return view
    }

    private fun StartSetPost(serverResponse: com.example.ResponseFriendListDTO) {
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
        var RequestChattingListDTO = com.example.RequestChattingListDTO(AccessToken)
        retrofitClient = com.example.api.RetrofitClient()

        ApiService = com.example.api.RetrofitClient.getRetrofitInterface()

        val call: Call<com.example.ResponseFriendListDTO>? = ApiService?.ChatRoomList(RequestChattingListDTO)
        call?.enqueue(object : Callback<com.example.ResponseFriendListDTO?> {
            override fun onResponse(call: Call<com.example.ResponseFriendListDTO?>?, response: Response<com.example.ResponseFriendListDTO?>) {
                StartSetPost(com.example.ResponseFriendListDTO())
            }

            override fun onFailure(call: Call<com.example.ResponseFriendListDTO?>?, t: Throwable?) {

            }
        })
    }
}