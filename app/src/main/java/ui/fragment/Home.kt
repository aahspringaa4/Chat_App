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
    lateinit var ChattingListAdapter: ChattingListAdapter
    val datas = mutableListOf<ChattingListData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_chatlist, container, false) //frag1과 연결시켜 return해줌.
        rv = view.findViewById(R.id.rv)
        initRecycler()
        return view
    }

    private fun initRecycler() {
        ChattingListAdapter = ChattingListAdapter(context)
        rv.adapter = ChattingListAdapter


        datas.apply {
            add(ChattingListData(img = R.drawable.dream, id = "안진우", content = "1일 1커밋", people = "2명"))

            add(ChattingListData(img = R.drawable.appicon, id = "제발되라", content = "qwer", people = "2명"))

//            add(ChattingListData(img = R.drawable.flower, id = "안드로이드", content = "소켓 어렵다 ㅠ", people = "2명"))
            Log.d("결과","성공")

            ChattingListAdapter.datas = datas
            ChattingListAdapter.notifyDataSetChanged()

        }
    }
}