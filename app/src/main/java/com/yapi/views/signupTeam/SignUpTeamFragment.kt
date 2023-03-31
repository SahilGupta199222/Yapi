package com.yapi.views.signupTeam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.yapi.databinding.SignupTeamLayoutBinding

class SignUpTeamFragment : Fragment() {

    private lateinit var rvUsersAdapter: RVUsersAdapter
    private lateinit var dataBinding: SignupTeamLayoutBinding
    private  val viewModelSignUpViewModel:SignupViewModel by viewModels()

    //private var vm:SignupViewModel by ViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dataBinding = SignupTeamLayoutBinding.inflate(LayoutInflater.from(requireActivity()))
        initUI()
        return dataBinding.root
    }

    private fun initUI() {
        rvUsersAdapter = RVUsersAdapter(requireActivity())
        dataBinding.rvUsers.layoutManager = LinearLayoutManager(requireActivity())
        dataBinding.rvUsers.adapter = rvUsersAdapter
        dataBinding.model=viewModelSignUpViewModel
    }
}