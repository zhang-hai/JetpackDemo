package com.zhh.app.navigation.demo

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.zhh.app.navigation.demo.databinding.ActivityNavigationBinding
import com.zhh.app.navigation.demo.model.User
import com.zhh.app.navigation.demo.viewmodel.NavigationViewModel

class NavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationBinding

    private lateinit var viewModel : NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val controller = findNavController(R.id.fragment)
//        NavigationUI.setupActionBarWithNavController(this,controller)

        viewModel = ViewModelProvider(this).get(NavigationViewModel::class.java)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.fragment).navigateUp();
//    }
}
