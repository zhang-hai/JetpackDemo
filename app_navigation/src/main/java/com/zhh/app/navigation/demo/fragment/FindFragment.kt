package com.zhh.app.navigation.demo.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter

import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.adapter.ChatListAdapter
import com.zhh.app.navigation.demo.adapter.ContactHeaderAdapter
import com.zhh.app.navigation.demo.adapter.ContactsAdapter
import com.zhh.app.navigation.demo.adapter.FindListAdapter
import com.zhh.app.navigation.demo.databinding.FragmentContactsBinding
import com.zhh.app.navigation.demo.databinding.FragmentFindBinding
import com.zhh.app.navigation.demo.model.ChatItemBean
import com.zhh.app.navigation.demo.model.ContactHeaderBean
import com.zhh.app.navigation.demo.model.FindItemBean

/**
 * A simple [Fragment] subclass.
 */
class FindFragment : Fragment() {

    private lateinit var binding:FragmentFindBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFindBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var findList = ArrayList<FindItemBean>()
        findList.add(FindItemBean(R.drawable.ic_friend_circle,"朋友圈",true,true))
        findList.add(FindItemBean(R.drawable.ic_scan,"扫一扫",false,false))
        findList.add(FindItemBean(R.drawable.ic_shake,"摇一摇",false,true))
        findList.add(FindItemBean(R.drawable.ic_have_a_look,"看一看",false,false))
        findList.add(FindItemBean(R.drawable.ic_search_s,"搜一搜",false,true))

        findList.add(FindItemBean(R.drawable.ic_around,"附近的人",true,true))
        findList.add(FindItemBean(R.drawable.ic_shoping,"购物",false,false))
        findList.add(FindItemBean(R.drawable.ic_game,"游戏",false,true))

        findList.add(FindItemBean(R.drawable.ic_applet,"小程序",true,true))

        var findAdapter = FindListAdapter(requireActivity(),findList)


        binding.rvFind.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFind.adapter = findAdapter
    }
}
