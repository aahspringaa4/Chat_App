package adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_app.databinding.ChatmeBinding
import com.example.chat_app.databinding.ChatotherBinding
import model.data.ChattingData
import viewmodel.ChattingViewModel

@Suppress("UNREACHABLE_CODE")
class ChattingAdapter(val chatting  : MutableLiveData<List<ChattingData>>, val viewModel: ChattingViewModel, val index : Int, val name : String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val MY_CHAT=0
    private val YOUR_CHAT=1

    inner class MyChatViewHolder(private val binding : ChatmeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int, viewModel: ChattingViewModel) {
            binding.vm = viewModel
            binding.position = position
            binding.executePendingBindings()
        }
    }

    inner class YourChatViewHolder(private val binding : ChatotherBinding): RecyclerView.ViewHolder(binding.root){ // 이것만 이미지 포함
        fun bind(position: Int, viewModel: ChattingViewModel) {
            binding.vm = viewModel
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
                (holder as MyChatViewHolder).bind(position,viewModel)
            }
            YOUR_CHAT ->{
                (holder as YourChatViewHolder).bind(position,viewModel)
            }
            else -> { // else 안뜨게 처리 함

            }
        }
    }


    override fun getItemCount(): Int {
        return when(chatting.value){
            null -> 0
            else -> chatting.value!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(index){
            0 ->{
                return when(chatting.value?.get(position)?.user){
                    "1" -> MY_CHAT
                    "2" -> YOUR_CHAT
                    else -> 1
                }
            }
            else -> {
                return when(chatting.value?.get(position)?.user){
                    "1" -> YOUR_CHAT
                    "2" -> MY_CHAT

                    else -> 1
                }
            }
        }
    }
}