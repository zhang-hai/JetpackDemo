package com.zhh.app.navigation.demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.adapter.BaseAdapter
import com.zhh.app.navigation.demo.adapter.FindListAdapter
import com.zhh.app.navigation.demo.adapter.TestAdapter
import com.zhh.app.navigation.demo.databinding.FragmentMeBinding
import com.zhh.app.navigation.demo.databinding.ItemFindBinding
import com.zhh.app.navigation.demo.databinding.ItemMeHeaderBinding
import com.zhh.app.navigation.demo.model.FindItemBean

/**
 * A simple [Fragment] subclass.
 * Use the [MeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MeFragment : Fragment() {

    var binding:FragmentMeBinding ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMeBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var headerBind = ItemMeHeaderBinding.inflate(LayoutInflater.from(requireActivity()),null,false)
        headerBind!!.tvWxName.text = "Hello"
        headerBind!!.tvWxNo.text = "微信号：hello_123456"

        //初始化列表
        var findList = ArrayList<FindItemBean>()
        findList.add(FindItemBean(R.drawable.ic_me_pay,"支付",true,true))

        findList.add(FindItemBean(R.drawable.ic_me_collection,"收藏",false,false))
        findList.add(FindItemBean(R.drawable.ic_me_image,"相册",false,false))
        findList.add(FindItemBean(R.drawable.ic_me_cards,"卡包",false,false))
        findList.add(FindItemBean(R.drawable.ic_me_emoj,"表情",false,true))

        findList.add(FindItemBean(R.drawable.ic_me_setting,"设置",true,true))

        binding!!.rvMeList.layoutManager = LinearLayoutManager(requireActivity())
//        binding!!.rvMeList.adapter = FindListAdapter(requireActivity(),findList)
        var adapter = TestAdapter(requireActivity(),findList)
        adapter.addHeaderView(headerBind!!.root)
        binding!!.rvMeList.adapter = adapter
    }
}
