package com.zhh.app.navigation.demo.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.databinding.FragmentSplashBinding
import com.zhh.app.navigation.demo.model.User
import com.zhh.app.navigation.demo.viewmodel.NavigationViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var viewModel: NavigationViewModel ? = null
    private var binding:FragmentSplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding!!.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SplashFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(NavigationViewModel::class.java)
        val handler = Handler()
        viewModel!!.getUserLiveData().observe(requireActivity(), Observer {
            handler.postDelayed(Runnable {
                if(viewModel!!.getUserLiveData().value == null || viewModel!!.getUserLiveData().value!!.size == 0){
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment)
                }
            },2000)
        })
    }
}
