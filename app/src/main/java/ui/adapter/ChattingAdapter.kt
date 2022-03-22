package ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.databinding.ChatmeBinding
import com.example.chat_app.databinding.ChatotherBinding
import com.example.ChattingData

@Suppress("UNREACHABLE_CODE")
class ChattingAdapter(private val chattingList: MutableLiveData<List<com.example.ChattingData>>, private val index: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val MY_CHAT=0
    private val YOUR_CHAT=1

    inner class MyChatViewHolder(private val binding : ChatmeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.position = position
            binding.executePendingBindings()
        }
    }

    inner class YourChatViewHolder(private val binding : ChatotherBinding): RecyclerView.ViewHolder(binding.root){ // 이것만 이미지 포함
        fun bind(position: Int) {
            binding.position = position
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            MY_CHAT ->{
                val binding = ChatmeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return MyChatViewHolder(binding)
            }
            YOUR_CHAT ->{
                val binding = ChatotherBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return YourChatViewHolder(binding)
            }
        }

        run { // else 안뜨게 처리 함
            val binding = ChatmeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MyChatViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            MY_CHAT ->{
                (holder as MyChatViewHolder).bind(position)
            }
            YOUR_CHAT ->{
                (holder as YourChatViewHolder).bind(position)
            }
            else -> {

            }
        }
    }


    override fun getItemCount(): Int {
        return when(chattingList.value){
            null -> 0
            else -> chattingList.value!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(index){
            0 ->{
                return when(chattingList.value?.get(position)?.mine){
                    true -> MY_CHAT
                    false -> YOUR_CHAT
                    else -> 1
                }
            }
            else -> {
                return when(chattingList.value?.get(position)?.mine){
                    true -> YOUR_CHAT
                    false -> MY_CHAT

                    else -> 1
                }
            }
        }
    }
}