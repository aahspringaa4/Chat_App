package ui.fragment

import adapter.FriendListAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.databinding.ActivityFriendListBinding
import com.google.gson.JsonObject
import model.data.FriendListData
import model.dto.RequestFriendListDTO
import model.dto.ResponseFriendListDTO
import network.ApiService
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.activity.FriendAddActivity


class FriendList : Fragment() {

    lateinit var rv: RecyclerView
    lateinit var friendlistAdapter: FriendListAdapter
    val datas = mutableListOf<FriendListData>()

    var recyclerView: RecyclerView? = null

    var FriendListAdapter: FriendListAdapter? = null

    var linearLayout: LinearLayout? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var arrayList: ArrayList<FriendListData>? = null

    private var ApiService: ApiService? = null
    private var retrofitClient: RetrofitClient? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var binding = ActivityFriendListBinding.inflate(inflater, container, false)

        return binding.root

        binding.ivFriend.setOnClickListener {
            val intent = Intent(context, FriendAddActivity::class.java)
            startActivity(intent)
        }

        binding.tvFriend.setOnClickListener {
            val intent = Intent(context, FriendAddActivity::class.java)
            startActivity(intent)
        }
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

        retrofitClient = RetrofitClient()

        ApiService = RetrofitClient.getRetrofitInterface()

        val call: Call<ResponseFriendListDTO>? = ApiService?.FriendList(size = 10, page = 0)
        call?.enqueue(object : Callback<ResponseFriendListDTO?> {
            override fun onResponse(call: Call<ResponseFriendListDTO?>?, response: Response<ResponseFriendListDTO?>) {
                StartSetPost(ResponseFriendListDTO())
            }

            override fun onFailure(call: Call<ResponseFriendListDTO?>?, t: Throwable?) {

            }
        })
    }



}