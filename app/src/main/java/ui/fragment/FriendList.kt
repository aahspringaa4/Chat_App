package ui.fragment

import adapter.FriendListAdapter
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.R
import com.example.chat_app.databinding.ActivityFriendListBinding
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
        var binding = ActivityFriendListBinding.inflate(inflater, container, false)

        return binding.root

        binding.ivFriend.setOnClickListener {
            val customDialog = CustomDialog(this@FriendList)

        }

        binding.tvFriend.setOnClickListener {
            val customDialog = CustomDialog(this@FriendList)
        }
    }

    private fun CustomDialog(Context: FriendList) {
        val context: Context = TODO()

        // 호출할 다이얼로그 함수를 정의한다.
        fun callFunction(main_label: TextView) {

            // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
            val dlg = Dialog(context)

            // 커스텀 다이얼로그의 레이아웃을 설정한다.
            dlg.setContentView(R.layout.friend)

            // 커스텀 다이얼로그를 노출한다.
            dlg.show()

            // 커스텀 다이얼로그의 각 위젯들을 정의한다.
            val message = dlg.findViewById(R.id.mesgase) as EditText
            val okButton: Button = dlg.findViewById(R.id.okButton) as Button
            val cancelButton: Button = dlg.findViewById(R.id.cancelButton) as Button
            okButton.setOnClickListener(View.OnClickListener { // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.
                    main_label.text = message.text.toString()
                Toast.makeText(
                    context,
                    "\"" + message.text.toString() + "\" 님께 친구 요청을 보냈습니다.",
                    Toast.LENGTH_SHORT
                ).show()

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss()

                pushPost(message)
            })
            cancelButton.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show()

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss()
            })
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

    private fun pushPost(message: EditText) {
        val memberId: EditText = message
        val ApiService : ApiService = BaseApi.getInstance().create(ApiService::class.java)
        val call: Unit = ApiService.FriendApply(memberId)
        call.enqueue(object : Callback<ServerRequest?> {
            override fun onResponse(
                call: Call<ServerRequest?>,
                response: Response<ServerRequest?>
            ) {
                Toast.makeText(this@FriendList, "게시글 등록이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ServerRequest?>, t: Throwable) {
                Toast.makeText(
                    this@PostActivity,
                    "예기치 못한 오류로 인해 게시글 등록에 실패하였습니다.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        })
    }

}