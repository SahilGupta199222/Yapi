package com.yapi.views.menu_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private var groupListClicked = false
    private var jobListClicked = false
    private var customerListClicked = false
    private var teamListClicked = false
    private var leadListClicked = false
    private var settingListClicked = false
    private var adapterGroupsList: AdapterGroupsList? = null
    private var adapterJobsList: AdapterGroupsList? = null
    private var adapterCustomerList: AdapterCustomerList? = null
    private var adapterTeamList: AdapterCustomerList? = null
    private var adapterLeadsList: AdapterCustomerList? = null
    private var adapterSettingsList: AdapterSettingList? = null

    val viewModel:MenuViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentMenuBinding.inflate(LayoutInflater.from(requireActivity()))
        binding.mViewmodel=viewModel
        binding = FragmentMenuBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
            init()
    }

    private fun init() {
        binding.apply {
            layoutAddNewGroupsMenu.clipToOutline = true
            layoutAddNewCustomersMenu.clipToOutline = true
        }
        clickListener()
    }

    private fun clickListener() {
        binding.apply {
            imgTempDrawableMenu.setOnClickListener {
                findNavController().popBackStack()
            }
            layoutGroupsMenu.setOnClickListener {
                groupListClicked = !groupListClicked
                if (groupListClicked) {
                    imgArrowGroupMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_up_24
                        )
                    )
                    rvGroupListMenu.visibility = View.VISIBLE
                    setGroupListAdapter()
                } else {
                    imgArrowGroupMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_right_24
                        )
                    )
                    rvGroupListMenu.visibility = View.GONE
                }
            }
            layoutJobsMenu.setOnClickListener {
                jobListClicked = !jobListClicked
                if (jobListClicked) {
                    imgArrowJobsMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_up_24
                        )
                    )
                    rvJobsListMenu.visibility = View.VISIBLE
                    setJobsListAdapter()
                } else {
                    imgArrowJobsMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_right_24
                        )
                    )
                    rvJobsListMenu.visibility = View.GONE
                }
            }
            layoutCustomersMenu.setOnClickListener {
                customerListClicked = !customerListClicked
                if (customerListClicked) {
                    imgArrowCustomersMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_up_24
                        )
                    )
                    rvCustomersListMenu.visibility = View.VISIBLE
                    setCustomerListAdapter()
                } else {
                    imgArrowCustomersMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_right_24
                        )
                    )
                    rvCustomersListMenu.visibility = View.GONE
                }
            }
            layoutTeamMenu.setOnClickListener {
                teamListClicked = !teamListClicked
                if (teamListClicked) {
                    imgArrowTeamMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_up_24
                        )
                    )
                    rvTeamsListMenu.visibility = View.VISIBLE
                    setTeamListAdapter()
                } else {
                    imgArrowTeamMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_right_24
                        )
                    )
                    rvTeamsListMenu.visibility = View.GONE
                }
            }
            layoutLeadMenu.setOnClickListener {
                leadListClicked = !leadListClicked
                if (leadListClicked) {
                    imgArrowLeadMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_up_24
                        )
                    )
                    rvLeadsListMenu.visibility = View.VISIBLE
                    rvLeadsListMenu()
                } else {
                    imgArrowLeadMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_right_24
                        )
                    )
                    rvLeadsListMenu.visibility = View.GONE
                }
            }
            layoutSettingsMenu.setOnClickListener {
                settingListClicked = !settingListClicked
                if (settingListClicked) {
                    imgArrowSettingsMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_up_24
                        )
                    )
                    rvSettingsListMenu.visibility = View.VISIBLE
                    setSettingListAdapter()
                } else {
                    imgArrowSettingsMenu.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_baseline_keyboard_arrow_right_24
                        )
                    )
                    rvSettingsListMenu.visibility = View.GONE
                }
            }
            etSearchMenu.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }
            layoutBoatMenu.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }
            layoutAddNewGroupsMenu.setOnClickListener {
//                findNavController().navigate(R.id.action_menuFragment_to_createGroupFragment)
            }
            layoutAddNewCustomersMenu.setOnClickListener {
                Toast.makeText(requireContext(), "Add new member", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSettingListAdapter() {
        val list = ArrayList<PojoSettingList>()
        list.add(PojoSettingList("User Management", false))
        list.add(PojoSettingList("Workspaces Management", false))
        val fixImageHeight =
            requireContext().resources.getDimension(com.intuit.sdp.R.dimen._45sdp).toInt()
        val rvHeight = fixImageHeight * list.size
        binding.rvSettingsListMenu.layoutParams.height = rvHeight
        adapterSettingsList =
            AdapterSettingList(requireContext(), list, object : AdapterSettingList.Click {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSelected(position: Int) {
                    for (i in 0 until adapterSettingsList?.getListt()?.size!!) {
                        adapterSettingsList?.getListt()?.get(i)?.selectedStatus = position == i
                    }
                    adapterSettingsList?.notifyDataSetChanged()
                }
            })
        binding.rvSettingsListMenu.adapter = adapterSettingsList
    }

    private fun setCustomerListAdapter() {
        val list = ArrayList<PojoCustomerList>()
        val tempOnlineList = listOf(true, false)
        for (i in 0 until 5) {
            list.add(PojoCustomerList("ab", "Customer${i + 34}", tempOnlineList.random(), i))
        }
        val fixImageHeight =
            requireContext().resources.getDimension(com.intuit.sdp.R.dimen._45sdp).toInt()
        val rvHeight = fixImageHeight * list.size
        binding.rvCustomersListMenu.layoutParams.height = rvHeight

        adapterCustomerList =
            AdapterCustomerList(requireContext(), list, object : AdapterCustomerList.Click {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSeletect(position: Int) {
                    for (i in 0 until adapterCustomerList?.getListt()?.size!!) {
                        adapterCustomerList?.getListt()?.get(i)?.selectedStatus = position == i
                    }
                    adapterCustomerList?.notifyDataSetChanged()
                }
            })
        binding.rvCustomersListMenu.adapter = adapterCustomerList
    }

    private fun rvLeadsListMenu() {
        val list = ArrayList<PojoCustomerList>()
        val tempOnlineList = listOf(true, false)
        for (i in 0 until 5) {
            list.add(PojoCustomerList("ab", "Customer${i + 34}", tempOnlineList.random(), i))
        }
        val fixImageHeight =
            requireContext().resources.getDimension(com.intuit.sdp.R.dimen._45sdp).toInt()
        val rvHeight = fixImageHeight * list.size
        binding.rvLeadsListMenu.layoutParams.height = rvHeight



        adapterLeadsList =
            AdapterCustomerList(requireContext(), list, object : AdapterCustomerList.Click {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSeletect(position: Int) {
                    for (i in 0 until adapterLeadsList?.getListt()?.size!!) {
                        adapterLeadsList?.getListt()?.get(i)?.selectedStatus = position == i
                    }
                    adapterLeadsList?.notifyDataSetChanged()
                }
            })
        binding.rvLeadsListMenu.adapter = adapterLeadsList
    }

    private fun setTeamListAdapter() {
        val list = ArrayList<PojoCustomerList>()
        val tempOnlineList = listOf(true, false)
        for (i in 0 until 5) {
            list.add(PojoCustomerList("ab", "Customer${i + 34}", tempOnlineList.random(), i))
        }
        val fixImageHeight =
            requireContext().resources.getDimension(com.intuit.sdp.R.dimen._45sdp).toInt()
        val rvHeight = fixImageHeight * list.size
        binding.rvTeamsListMenu.layoutParams.height = rvHeight


        adapterTeamList =
            AdapterCustomerList(requireContext(), list, object : AdapterCustomerList.Click {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSeletect(position: Int) {
                    for (i in 0 until adapterTeamList?.getListt()?.size!!) {
                        adapterTeamList?.getListt()?.get(i)?.selectedStatus = position == i
                    }
                    adapterTeamList?.notifyDataSetChanged()
                }
            })
        binding.rvTeamsListMenu.adapter = adapterTeamList
    }

    private fun setGroupListAdapter() {
        val list = ArrayList<PojoGroupMembersList>()
        for (i in 0 until 5) {
            list.add(PojoGroupMembersList("Group ${i + 2}", i, false))
        }

        val fixImageHeight =
            requireContext().resources.getDimension(com.intuit.sdp.R.dimen._36sdp).toInt()
        val rvHeight = fixImageHeight * list.size
        binding.rvGroupListMenu.layoutParams.height = rvHeight

        adapterGroupsList =
            AdapterGroupsList(requireContext(), true, list, object : AdapterGroupsList.Click {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSeletect(position: Int) {
                    for (i in 0 until adapterGroupsList?.getListt()?.size!!) {
                        adapterGroupsList?.getListt()?.get(i)?.selected = position == i
                    }
                    Log.i("asdfjanskdf", "before notifiy list is\n${adapterGroupsList?.getListt()}")
                    adapterGroupsList?.notifyDataSetChanged()
                }
            })
        binding.rvGroupListMenu.adapter = adapterGroupsList
    }

    private fun setJobsListAdapter() {
        val list = ArrayList<PojoGroupMembersList>()
        for (i in 0 until 5) {
            list.add(PojoGroupMembersList("Jobs ${i + 2}", i, false))
        }
        val fixImageHeight =
            requireContext().resources.getDimension(com.intuit.sdp.R.dimen._36sdp).toInt()
        val rvHeight = fixImageHeight * list.size
        binding.rvJobsListMenu.layoutParams.height = rvHeight

//        val adapter = AdapterJobsList(requireContext(), list)
//        binding.rvJobsListMenu.adapter = adapter

        adapterJobsList =
            AdapterGroupsList(requireContext(), false, list, object : AdapterGroupsList.Click {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSeletect(position: Int) {
                    for (i in 0 until adapterJobsList?.getListt()?.size!!) {
                        adapterJobsList?.getListt()?.get(i)?.selected = position == i
                    }
                    Log.i("asdfjanskdf", "before notifiy list is\n${adapterJobsList?.getListt()}")
                    adapterJobsList?.notifyDataSetChanged()
                }
            })
        binding.rvJobsListMenu.adapter = adapterJobsList

    }

}