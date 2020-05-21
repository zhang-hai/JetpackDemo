package com.zhh.app.navigation.demo.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhh.app.navigation.demo.fragment.ChatFragment
import com.zhh.app.navigation.demo.fragment.ContactsFragment
import com.zhh.app.navigation.demo.fragment.FindFragment
import com.zhh.app.navigation.demo.fragment.MeFragment

/**
 * Created by zhanghai on 2020/5/18.
 * function：
 */
class ViewPager2Adapter(fragment: Fragment) : FragmentStateAdapter(fragment)  {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        var fragment:Fragment
        when(position){
            0 -> fragment = ChatFragment()
            1 -> fragment = ContactsFragment()
            2 -> fragment = FindFragment()
            else -> fragment = MeFragment()
        }
        return fragment
    }
}