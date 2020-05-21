package com.zhh.app.navigation.demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.adapter.ChatListAdapter
import com.zhh.app.navigation.demo.databinding.FragmentChatBinding
import com.zhh.app.navigation.demo.databinding.ItemChatBinding
import com.zhh.app.navigation.demo.model.ChatItemBean

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {

    lateinit var binding: FragmentChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var list = ArrayList<ChatItemBean>()
        for ( i in 0 until 30){
            var item:ChatItemBean
            when(i%5){
                0 -> item = ChatItemBean("MMM","M:你好，在吗？")
                1 -> item = ChatItemBean("ABIE-L103周天","Allen妈妈:[语音]")
                2 -> item = ChatItemBean("壮壮牛班英语","Candy老师:[视频]")
                3 -> item = ChatItemBean("舞东风超时","520爱你们哦，10000支免费的可爱的大派送咯")
                else -> item = ChatItemBean("舞东风超时","520爱你们哦，10000支免费的可爱的大派送咯")
            }
            list.add(item)
        }

        binding.rvChatList.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvChatList.adapter = ChatListAdapter(requireActivity(),list)
    }
}
