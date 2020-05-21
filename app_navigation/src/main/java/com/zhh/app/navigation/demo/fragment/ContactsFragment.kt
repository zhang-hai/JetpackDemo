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
import com.zhh.app.navigation.demo.databinding.FragmentContactsBinding
import com.zhh.app.navigation.demo.model.ChatItemBean
import com.zhh.app.navigation.demo.model.ContactHeaderBean

/**
 * A simple [Fragment] subclass.
 */
class ContactsFragment : Fragment() {

    private lateinit var binding:FragmentContactsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentContactsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var headers = ArrayList<ContactHeaderBean>()
        headers.add(ContactHeaderBean(R.drawable.ic_person_add_white,requireActivity().resources.getColor(R.color.color_new_friend),"新朋友"))
        headers.add(ContactHeaderBean(R.drawable.ic_group_white,requireActivity().resources.getColor(R.color.color_group_chat),"群聊"))
        headers.add(ContactHeaderBean(R.drawable.ic_edit_white,requireActivity().resources.getColor(R.color.color_lables),"标签"))
        headers.add(ContactHeaderBean(R.drawable.ic_person_white,requireActivity().resources.getColor(R.color.color_offical_account),"公众号"))

        var headerAdapter = ContactHeaderAdapter(requireActivity(),headers)

        var chatList = ArrayList<ChatItemBean>()
        for ( i in 0 until 20){
            var item:ChatItemBean
            when(i%5){
                0 -> item = ChatItemBean("MMM","M:你好，在吗？")
                1 -> item = ChatItemBean("ABIE-L103周天","Allen妈妈:[语音]")
                2 -> item = ChatItemBean("壮壮牛班英语","Candy老师:[视频]")
                3 -> item = ChatItemBean("舞东风超时","520爱你们哦，10000支免费的可爱的大派送咯")
                else -> item = ChatItemBean("舞东风超时","520爱你们哦，10000支免费的可爱的大派送咯")
            }
            chatList.add(item)
        }
        var chatAdapter = ChatListAdapter(requireActivity(),chatList)

        var mergeAdapter = MergeAdapter(headerAdapter,chatAdapter)

        binding.rvContacts.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvContacts.adapter = mergeAdapter
    }
}
