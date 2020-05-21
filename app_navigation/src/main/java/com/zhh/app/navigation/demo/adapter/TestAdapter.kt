package com.zhh.app.navigation.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.zhh.app.navigation.demo.databinding.ItemFindBinding
import com.zhh.app.navigation.demo.model.FindItemBean

/**
 * Created by zhanghai on 2020/5/21.
 * function：
 */
class TestAdapter(context: Context,items:List<FindItemBean>):BaseAdapter<FindItemBean, ItemFindBinding>(context, items) {

    override fun bindView(inflater: LayoutInflater, parent: ViewGroup){
        viewBinding = ItemFindBinding.inflate(inflater,parent,false)
    }

    override fun convert(position: Int) {
        if(viewBinding == null){
            return
        }
        var bean = items!![position]
        (viewBinding as ItemFindBinding)!!.ivHeaderIcon.setImageResource(bean.iconId)
        (viewBinding as ItemFindBinding)!!.tvItemHeaderName.text = bean.name
        if(bean.isAlone){
            (viewBinding as ItemFindBinding)!!.dvLine.visibility = View.INVISIBLE
            (viewBinding as ItemFindBinding)!!.vSpace.visibility = View.VISIBLE
        }else{
            if(bean.isLast){
                (viewBinding as ItemFindBinding)!!.dvLine.visibility = View.INVISIBLE
                (viewBinding as ItemFindBinding)!!.vSpace.visibility = View.VISIBLE
            }else{
                (viewBinding as ItemFindBinding)!!.dvLine.visibility = View.VISIBLE
                (viewBinding as ItemFindBinding)!!.vSpace.visibility = View.GONE
            }
        }
    }

    override fun bindViewClickListener(holder: BaseViewHolder) {
        if(viewBinding == null) return
        (viewBinding as ItemFindBinding)!!.vClick.setOnClickListener {
            var p = getClickPosition(holder)
            var bean = items!![p]
            Toast.makeText(context,"点击了-> " + bean.name, Toast.LENGTH_SHORT).show()
        }
    }

}