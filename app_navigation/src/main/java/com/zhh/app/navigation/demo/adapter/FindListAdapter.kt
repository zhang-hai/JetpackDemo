package com.zhh.app.navigation.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.databinding.ItemFindBinding
import com.zhh.app.navigation.demo.model.ContactHeaderBean
import com.zhh.app.navigation.demo.model.FindItemBean

/**
 * Created by zhanghai on 2020/5/20.
 * function：
 */
class FindListAdapter(context: Context, headers:List<FindItemBean>) : RecyclerView.Adapter<FindListAdapter.FindViewHolder>(){
    var headers:List<FindItemBean>
    var inflater: LayoutInflater
    var mContext: Context

    var viewBind:ItemFindBinding ?= null

    init {
        this.mContext = context
        this.inflater = LayoutInflater.from(context)
        this.headers = headers
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindViewHolder {
        viewBind = ItemFindBinding.inflate(inflater,parent,false)
        val holder = FindViewHolder(viewBind!!.root)
        viewBind!!.vClick.setOnClickListener {
            var p = holder.absoluteAdapterPosition
            var bean = headers[p]
            Toast.makeText(mContext,"点击了-> " + bean.name,Toast.LENGTH_SHORT).show()
        }
        return holder
    }

    override fun getItemCount(): Int {
        if(headers == null) return 0
        return headers.size
    }

    override fun onBindViewHolder(holder: FindViewHolder, position: Int) {
        var bean = headers[position]
        viewBind!!.ivHeaderIcon.setImageResource(bean.iconId)
        viewBind!!.tvItemHeaderName.text = bean.name
        if(bean.isAlone){
            viewBind!!.dvLine.visibility = View.INVISIBLE
            viewBind!!.vSpace.visibility = View.VISIBLE
        }else{
            if(bean.isLast){
                viewBind!!.dvLine.visibility = View.INVISIBLE
                viewBind!!.vSpace.visibility = View.VISIBLE
            }else{
                viewBind!!.dvLine.visibility = View.VISIBLE
                viewBind!!.vSpace.visibility = View.GONE
            }
        }
    }


    inner class FindViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
}