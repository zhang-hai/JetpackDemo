package com.zhh.app.navigation.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.databinding.ItemChatBinding
import com.zhh.app.navigation.demo.model.ChatItemBean

/**
 * Created by zhanghai on 2020/5/19.
 * function：
 */
class ChatListAdapter(context: Context,chats:List<ChatItemBean>) : RecyclerView.Adapter<ChatListAdapter.ChatViewHolder>(){
    lateinit var binding:ItemChatBinding
    lateinit var chats:List<ChatItemBean>
    lateinit var inflater: LayoutInflater

    init {
        this.inflater = LayoutInflater.from(context)
        this.chats = chats

        this.binding = ItemChatBinding.inflate(this.inflater)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        var view = inflater.inflate(R.layout.item_chat,parent,false)
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.chats.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val bean = this.chats[position]
        holder.tvName.text = bean.name
        holder.tvChatContent.text = bean.content
    }




    inner class ChatViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        lateinit var tvName:TextView
        lateinit var tvChatContent:TextView
        init {
            tvName = itemView.findViewById(R.id.tv_name)
            tvChatContent = itemView.findViewById(R.id.tv_chat_content)
        }
    }
}