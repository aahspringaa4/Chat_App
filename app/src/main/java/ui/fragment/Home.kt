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
import model.data.ChattingListData

class home : Fragment() {
    lateinit var rv: RecyclerView
    lateinit var ChattingAdapter: ChattingListAdapter
    val datas = mutableListOf<ChattingListData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_chatlist, container, false) //frag1과 연결시켜 return해줌.
        rv = view.findViewById(R.id.rv)
        initRecycler()
        return view
    }

    private fun initRecycler() {
        ChattingAdapter = ChattingListAdapter(context)
        rv.adapter = ChattingAdapter


        datas.apply {
            add(ChattingListData(img = R.drawable.dream, id = "명철이 형", content = "1일 1커밋", people = "2명"))
            Log.d("결과", "성공")
//            add(FriendListData(img = R.drawable.profile3, name = "jenny", age = 26))
//            add(FriendListData(img = R.drawable.profile2, name = "jhon", age = 27))
//            add(FriendListData(img = R.drawable.profile5, name = "ruby", age = 21))
//            add(FriendListData(img = R.drawable.profile4, name = "yuna", age = 23))

            ChattingAdapter.datas = datas
            ChattingAdapter.notifyDataSetChanged()

        }
    }
}