package com.zhh.app.navigation.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.model.ContactHeaderBean

/**
 * Created by zhanghai on 2020/5/20.
 * function：
 */
class ContactHeaderAdapter(context: Context, headers:List<ContactHeaderBean>) : RecyclerView.Adapter<ContactHeaderAdapter.ContactHeaderViewHolder>(){
    lateinit var headers:List<ContactHeaderBean>
    lateinit var inflater: LayoutInflater
    lateinit var mContext: Context

    init {
        this.mContext = context
        this.inflater = LayoutInflater.from(context)
        this.headers = headers
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHeaderViewHolder {
        var view = inflater.inflate(R.layout.item_contacts_header,parent,false)
        return ContactHeaderViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(headers == null) return 0
        return headers.size
    }

    override fun onBindViewHolder(holder: ContactHeaderViewHolder, position: Int) {
        var bean = headers[position]
        holder.ivHeadIcon.setImageResource(bean.iconId)
        holder.ivHeadIcon.setBackgroundColor(bean.bgcolor)
        holder.tvName.text = bean.name
    }


    inner class ContactHeaderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        lateinit var ivHeadIcon: ImageView
        lateinit var tvName: TextView
        init {
            ivHeadIcon = itemView.findViewById(R.id.iv_header_icon)
            tvName = itemView.findViewById(R.id.tv_item_header_name)
        }
    }
}