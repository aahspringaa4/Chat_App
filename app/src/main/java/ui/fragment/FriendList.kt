package ui.fragment

import adapter.FriendListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import com.google.gson.JsonObject
import model.data.FriendListData
import model.dto.ResponseFriendListDTO
import network.ApiService
import network.BaseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FriendList : Fragment() {

    lateinit var rv: RecyclerView
    lateinit var friendlistAdapter: FriendListAdapter
    val datas = mutableListOf<FriendListData>()

    var recyclerView: RecyclerView? = null

    var FriendListAdapter: FriendListAdapter? = null

    var linearLayout: LinearLayout? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var arrayList: ArrayList<FriendListData>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_friend_list, container, false) //frag1과 연결시켜 return해줌.
        rv = view.findViewById(R.id.rv)
        return view
    }

    private fun StartSetPost(serverResponse: ResponseFriendListDTO) {
        val totalElements: Int = serverResponse.getBoardInfos()!!.size
        for (i in 0 until totalElements) {
            val jsonObject: JsonObject? = serverResponse.getBoardInfos()?.get(i)
            val name = jsonObject?.get("name").toString()
            var content = jsonObject?.get("content").toString()
            var profile = jsonObject?.get("profile").toString()
            val FriendList = FriendListData(name, content, profile)
            arrayList!!.add(FriendList)
            FriendListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        arrayList?.clear()
        FriendListAdapter?.notifyDataSetChanged()
        val ApiService: ApiService = BaseApi.getInstance().create(ApiService::class.java)
        val call: Call<ResponseFriendListDTO> = ApiService.FriendList(size = 10, page = 0)
        call.enqueue(object : Callback<ResponseFriendListDTO?> {
            override fun onResponse(call: Call<ResponseFriendListDTO?>?, response: Response<ResponseFriendListDTO?>) {
                StartSetPost(ResponseFriendListDTO())
            }

            override fun onFailure(call: Call<ResponseFriendListDTO?>?, t: Throwable?) {

            }
        })
    }



}