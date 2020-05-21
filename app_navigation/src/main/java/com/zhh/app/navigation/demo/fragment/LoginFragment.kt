package com.zhh.app.navigation.demo.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.zhh.app.navigation.demo.R
import com.zhh.app.navigation.demo.databinding.FragmentLoginBinding
import com.zhh.app.navigation.demo.model.User
import com.zhh.app.navigation.demo.viewmodel.NavigationViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: NavigationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        viewModel = ViewModelProvider(requireActivity()).get(NavigationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                LoginFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val watcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            //输入后的监听}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //输入文字产生变化的监听
                val strAccount = binding.editText.text.trim()
                val strPwd = binding.editText2.text.trim()
                binding.button.isEnabled = !(TextUtils.isEmpty(strAccount) || TextUtils.isEmpty(strPwd))
                binding.button2.isEnabled = !(TextUtils.isEmpty(strAccount) || TextUtils.isEmpty(strPwd))
            }
        }
        binding.editText.addTextChangedListener(watcher)
        binding.editText2.addTextChangedListener(watcher)

        binding.button.isEnabled = false
        binding.button2.isEnabled = false


        //登录
        binding.button.setOnClickListener {
            val user = User(binding.editText.text.toString().trim(),binding.editText2.text.toString().trim())
            user.status = 0
            viewModel.insertUser(user)
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_homeFragment2)
        }
        //注册并登录
        binding.button2.setOnClickListener {
            val user = User(binding.editText.text.toString().trim(),binding.editText2.text.toString().trim())
            user.status = 0
            viewModel.insertUser(user)
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_homeFragment2)
        }
    }
}
