package com.example.chat_app.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chat_app.R
import org.w3c.dom.Text

class ChattingAdapter(private val context: Context?) : RecyclerView.Adapter<ChattingAdapter.ViewHolder>() {
    var datas = mutableListOf<ChattingData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chatting_item_view,parent,false)
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
        private val tv_people: TextView = itemView.findViewById(R.id.people)

        fun bind(item: ChattingData) {
            tv_content.text = item.content
            tv_id.text = item.id
            tv_people.text = item.people
            Glide.with(itemView).load(item.img).into(imgProfile)

        }
    }


}