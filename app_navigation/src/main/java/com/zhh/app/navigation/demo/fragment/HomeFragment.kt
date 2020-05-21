package com.zhh.app.navigation.demo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.adapter.ViewPager2Adapter
import com.zhh.app.navigation.demo.databinding.FragmentHomeBinding
import com.zhh.app.navigation.demo.viewmodel.NavigationViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var viewMode: NavigationViewModel
    private lateinit var binding: FragmentHomeBinding

    private var lastPositionOffset : Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMode = ViewModelProvider(requireActivity()).get(NavigationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                HomeFragment().apply {
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = 0
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            binding.tvTitle.text = it.title
            var cur = 0
            val m = binding.bottomNavigationView.menu
            for (i in 0 until m.size()){
                if(m.get(i).itemId == it.itemId){
                    cur = i
                    break
                }
            }
            //索引位置 != 当前获取位置
            if (binding.vp2Content.currentItem != cur){
                binding.vp2Content.setCurrentItem(cur,false)
            }
            //顶部工具栏是否要显示
            if(it.itemId == R.id.m_item_me){
                binding.groupTopTool.visibility = View.GONE
                binding.groupMeTop.visibility = View.VISIBLE
            }else{
                binding.groupTopTool.visibility = View.VISIBLE
                binding.groupMeTop.visibility = View.GONE
            }

            true
        })
        binding.bottomNavigationView.selectedItemId = index
        val m:MenuItem = binding.bottomNavigationView.menu.getItem(index)
        binding.tvTitle.text = m.title


        //设置ViewPager
        binding.vp2Content.adapter = ViewPager2Adapter(this)
        binding.vp2Content.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//                Log.d("HomeFragment",String.format(Locale.CHINESE,"position--->%d,positionOffset--->%f,positionOffsetPixels--->%d",position,positionOffset,positionOffsetPixels))

            }
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val item = binding.bottomNavigationView.menu.get(position)
                binding.bottomNavigationView.selectedItemId = item.itemId
            }
        })
    }
}
