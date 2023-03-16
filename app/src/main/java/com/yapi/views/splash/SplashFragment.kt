package com.yapi.views.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.common.showToastMessage
import com.yapi.databinding.SplashLayoutBinding

class SplashFragment : Fragment() {

    private lateinit var binding: SplashLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = SplashLayoutBinding.inflate(inflater)

        Handler(Looper.myLooper()!!).postDelayed(object : Runnable {
            override fun run() {
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
            }
        }, 2000)
        return binding.root
    }
}