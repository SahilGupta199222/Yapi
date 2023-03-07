package com.yapi.views.signupTeam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yapi.databinding.SignupTeamLayoutBinding

class SignUpTeamFragment():Fragment() {

    private lateinit var dataBinding: SignupTeamLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding=SignupTeamLayoutBinding.inflate(LayoutInflater.from(requireActivity()))
        return dataBinding.root
    }
}