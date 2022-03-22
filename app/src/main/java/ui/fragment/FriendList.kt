package ui.fragment

import ui.adapter.FriendListAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityFriendListBinding
import com.google.gson.JsonObject
import com.example.FriendListData
import com.example.ResponseFriendListDTO
import com.example.api.ApiService
import com.example.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ui.activity.FriendAddActivity


class FriendList : Fragment() {

    lateinit var rv: RecyclerView
    val datas = mutableListOf<com.example.FriendListData>()


    lateinit var FriendListAdapter: FriendListAdapter
    var arrayList: ArrayList<com.example.FriendListData>? = null

    private var ApiService: com.example.api.ApiService? = null
    private var retrofitClient: com.example.api.RetrofitClient? = null

    private lateinit var binding: ActivityFriendListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityFriendListBinding.inflate(inflater)

        rv = binding.rv
        
        binding.ivFriend.setOnClickListener {
            val intent = Intent(getActivity(), FriendAddActivity::class.java)
            startActivity(intent)
        }

        binding.tvFriend.setOnClickListener {
            val intent = Intent(getActivity(), FriendAddActivity::class.java)
            startActivity(intent)
        }

        initRecycler()

        return binding.root
    }

    private fun initRecycler() {
        FriendListAdapter = FriendListAdapter(context)
        rv.adapter = FriendListAdapter


        datas.apply {
            add(
                com.example.FriendListData(
                    name = "안진우",
                    content = "1일 1커밋",
                    img = R.drawable.dream
                )
            )

            add(
                com.example.FriendListData(
                    name = "제발되라",
                    content = "qwer",
                    img = R.drawable.appicon
                )
            )

            add(
                com.example.FriendListData(
                    name = "안드로이드",
                    content = "소켓 어렵다 ㅠ",
                    img = R.drawable.flower
                )
            )
            Log.d("결과","성공")

            FriendListAdapter!!.datas = datas
            FriendListAdapter!!.notifyDataSetChanged()

        }
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
            FriendListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        arrayList?.clear()
        FriendListAdapter?.notifyDataSetChanged()

        retrofitClient = com.example.api.RetrofitClient()

        ApiService = com.example.api.RetrofitClient.getRetrofitInterface()

        val call: Call<com.example.ResponseFriendListDTO>? = ApiService?.FriendList(size = 10, page = 0)
        call?.enqueue(object : Callback<com.example.ResponseFriendListDTO?> {
            override fun onResponse(call: Call<com.example.ResponseFriendListDTO?>?, response: Response<com.example.ResponseFriendListDTO?>) {
                StartSetPost(com.example.ResponseFriendListDTO())
            }

            override fun onFailure(call: Call<com.example.ResponseFriendListDTO?>?, t: Throwable?) {

            }
        })
    }



}