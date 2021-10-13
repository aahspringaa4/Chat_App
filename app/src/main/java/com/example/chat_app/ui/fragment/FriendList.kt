package com.example.chat_app.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import com.example.chat_app.ui.recyclerView.FriendListAdapter
import com.example.chat_app.ui.recyclerView.FriendListData

class FriendList : Fragment() {

    lateinit var rv: RecyclerView
    lateinit var friendlistAdapter: FriendListAdapter
    val datas = mutableListOf<FriendListData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_friend_list, container, false) //frag1과 연결시켜 return해줌.
        rv = view.findViewById(R.id.rv)
        initRecycler()
        return view
    }

    private fun initRecycler() {
        friendlistAdapter = FriendListAdapter(context)
        rv.adapter = friendlistAdapter


        datas.apply {
            add(FriendListData(img = R.drawable.dream, id = "명철이 형", content = "1일 1커밋"))
            Log.d("결과", "성공")
//            add(FriendListData(img = R.drawable.profile3, name = "jenny", age = 26))
//            add(FriendListData(img = R.drawable.profile2, name = "jhon", age = 27))
//            add(FriendListData(img = R.drawable.profile5, name = "ruby", age = 21))
//            add(FriendListData(img = R.drawable.profile4, name = "yuna", age = 23))

            friendlistAdapter.datas = datas
            friendlistAdapter.notifyDataSetChanged()

        }
    }
}