package ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.chat_app.R

class ChatAdapter {
    var data = arrayListOf<ChatData>()

    private val MY_CHAT = 0
    private val YOUR_CHAT = 1

    override fun getItemViewType(position: Int): Int {
        val chatMessage = data[position]

        return if (chatMessage.id=="me") {
            MY_CHAT
        } else {
            YOUR_CHAT
        }
    }

    class ChatMyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val message: TextView = view.findViewById(R.id.tv_chatmyitem_contents)

        fun onBind(chatData: ChatData) {
            message.text = chatData.message
        }
    }

    class ChatYourViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val profileImage: ImageView = view.findViewById(R.id.iv_yourchatitem_profile)
        val message: TextView = view.findViewById(R.id.tv_yourchatitem_message)

        fun onBind(chatData: ChatData) {
            message.text = chatData.message

            Glide.with(itemView.context).load(chatData.profile)
                .transform(RoundedCorners(180))
                .transition(DrawableTransitionOptions.withCrossFade()) to profileImage

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        lateinit var viewHolder: RecyclerView.ViewHolder

        when (viewType) {
            MY_CHAT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chatme, parent, false)

                return ChatMyViewHolder(view)

            }
            YOUR_CHAT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chatother, parent, false)

                return ChatYourViewHolder(view)
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder){
            is ChatMyViewHolder -> {
                holder.onBind(data[position])
            }
            is ChatYourViewHolder -> {
                holder.onBind(data[position])
            }
        }
    }

    override fun addItem(item: ChatData) {
        data.add(item)
    }

}