package ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chat_app.R
import ui.activity.ImageActivity
import com.example.FriendListData

class FriendListAdapter(private val context: Context?) : RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {
    var datas = mutableListOf<com.example.FriendListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.friend_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tv_content: TextView = itemView.findViewById(R.id.content)
        private val tv_id: TextView = itemView.findViewById(R.id.id)
        private val imgProfile: ImageView = itemView.findViewById(R.id.profiles)

        fun bind(item: com.example.FriendListData) {
            tv_content.text = item.content
            tv_id.text = item.name
            Glide.with(itemView).load(item.img).into(imgProfile)
            itemView.setOnClickListener {
                Intent(context, ImageActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run {
                    context?.startActivity(this)
                }
            }

        }
    }


}