package com.yapi.views.menu_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private var groupListClicked = false
    private var jobListClicked = false
    private var customerListClicked = false
    private var conversationListClicked = false
    private var teamListClicked = false
    private var leadListClicked = false
    private var settingListClicked = false
    private var adapterGroupsList: AdapterGroupsList? = null
    private var adapterJobsList: AdapterGroupsList? = null
    private var adapterCustomerList: AdapterCustomerList? = null
    private var adapterConversationList: AdapterCustomerList? = null
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
        val displayMetrics = DisplayMetrics()
       requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        var width = displayMetrics.widthPixels
        viewModel.screenWidth=width
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            init()
    }

    private fun init() {
        setGroupDataMethod()
        setJobDataMethod()
        setCusomerListMethod()
        setConversationMethod()
        setTeamMethod()
        setLeadMethod()
        setSettingMethod()

        binding.apply {
            layoutAddNewGroupsMenu.clipToOutline = true
            layoutAddNewCustomersMenu.clipToOutline = true
        }
        clickListener()
    }

    private fun clickListener() {

        binding.apply {
            imgTempDrawableMenu.setOnClickListener {
                lifecycleScope.launchWhenResumed {
                    if (findNavController().currentDestination?.id == R.id.menuFragment) {
                       // findNavController().popBackStack()
                    }
                }
            }
            layoutGroupsMenu.setOnClickListener {
              setGroupDataMethod()
            }
            layoutJobsMenu.setOnClickListener {
             setJobDataMethod()
            }
            layoutCustomersMenu.setOnClickListener {
               setCusomerListMethod()
            }
            layoutConversationMenu.setOnClickListener {
            setConversationMethod()
            }
            layoutTeamMenu.setOnClickListener {
               setTeamMethod()
            }
            layoutLeadMenu.setOnClickListener {
              setLeadMethod()
            }
            layoutSettingsMenu.setOnClickListener {
               setSettingMethod()
            }
            etSearchMenu.setOnClickListener {
              //  Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }
            layoutBoatMenu.setOnClickListener {
              //  Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }
            layoutAddNewCustomersMenu.setOnClickListener {
             //   Toast.makeText(requireContext(), "Add new member", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSettingMethod() {
        settingListClicked = !settingListClicked
        if (settingListClicked) {
            binding.imgArrowSettingsMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                )
            )
            binding.rvSettingsListMenu.visibility = View.VISIBLE
            setSettingListAdapter()
        } else {
            binding.imgArrowSettingsMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_right_24
                )
            )
            binding.rvSettingsListMenu.visibility = View.GONE
        }
    }

    private fun setLeadMethod() {

        leadListClicked = !leadListClicked
        if (leadListClicked) {
            binding.imgArrowLeadMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                )
            )
            binding. rvLeadsListMenu.visibility = View.VISIBLE
            rvLeadsListMenu()
        } else {
            binding.imgArrowLeadMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_right_24
                )
            )
            binding.rvLeadsListMenu.visibility = View.GONE
        }
    }

    private fun setTeamMethod() {

        teamListClicked = !teamListClicked
        if (teamListClicked) {
            binding.imgArrowTeamMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                )
            )
            binding.rvTeamsListMenu.visibility = View.VISIBLE
            setTeamListAdapter()
        } else {
            binding.imgArrowTeamMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_right_24
                )
            )
            binding.rvTeamsListMenu.visibility = View.GONE
        }
    }

    private fun setConversationMethod() {

        conversationListClicked = !conversationListClicked
        if (conversationListClicked) {
            binding.imgArrowConversationMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                )
            )
            binding.rvConversationListMenu.visibility = View.VISIBLE
            setConversationListAdapter()
        } else {
            binding.imgArrowConversationMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_right_24
                )
            )
            binding.rvConversationListMenu.visibility = View.GONE
        }
    }

    private fun setCusomerListMethod() {
        customerListClicked = !customerListClicked
        if (customerListClicked) {
            binding.imgArrowCustomersMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                )
            )
            binding.rvCustomersListMenu.visibility = View.VISIBLE
            setCustomerListAdapter()
        } else {
            binding.imgArrowCustomersMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_right_24
                )
            )
            binding.rvCustomersListMenu.visibility = View.GONE
        }
    }

    private fun setJobDataMethod() {

        jobListClicked = !jobListClicked
        if (jobListClicked) {
            binding.imgArrowJobsMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                )
            )
            binding.rvJobsListMenu.visibility = View.VISIBLE
            setJobsListAdapter()
        } else {
            binding.imgArrowJobsMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_right_24
                )
            )
            binding.rvJobsListMenu.visibility = View.GONE
        }
    }

    private fun setGroupDataMethod() {
        groupListClicked = !groupListClicked
        if (groupListClicked) {
            binding.imgArrowGroupMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                )
            )
            binding.rvGroupListMenu.visibility = View.VISIBLE
            setGroupListAdapter()
        } else {
            binding.imgArrowGroupMenu.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_right_24
                )
            )
            binding.rvGroupListMenu.visibility = View.GONE
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

                   /* if(findNavController().currentDestination?.id == R.id.menuFragment)
                        findNavController().navigate(R.id.action_menuFragment_to_chatMessageFragment)*/
                }
            })
        binding.rvSettingsListMenu.adapter = adapterSettingsList
    }

    private fun setCustomerListAdapter() {
        val list = ArrayList<PojoCustomerList>()
        val tempOnlineList = listOf(true, false)
       /* for (i in 0 until 5) {
            list.add(PojoCustomerList("ab", "Customer${i + 34}", tempOnlineList.random(), i))
        }*/

        list.add(PojoCustomerList("ab", "Omar Press", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Adison Septim...", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Talan George", tempOnlineList.random(), 0))
        list.add(PojoCustomerList("ab", "Madelyn Levin", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Zaire Stanton", tempOnlineList.random(), 0))
        list.add(PojoCustomerList("ab", requireActivity().getString(R.string.add_customers), tempOnlineList.random(), -1))

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
                 /*   if(findNavController().currentDestination?.id == R.id.menuFragment)
                    findNavController().navigate(R.id.action_menuFragment_to_chatMessageFragment)*/
                }
            })
        binding.rvCustomersListMenu.adapter = adapterCustomerList
    }
    private fun setConversationListAdapter() {
        val list = ArrayList<PojoCustomerList>()
        val tempOnlineList = listOf(true, false)
       /* for (i in 0 until 5) {
            list.add(PojoCustomerList("ab", "Customer${i + 34}", tempOnlineList.random(), i))
        }*/

        list.add(PojoCustomerList("ab", "Daman", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Sahil sir", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Amit sir", tempOnlineList.random(), 0))
        list.add(PojoCustomerList("ab", "Arundeep sir", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Khem sir", tempOnlineList.random(), 0))
        list.add(PojoCustomerList("ab", requireActivity().getString(R.string.add_conversation), tempOnlineList.random(), -1))

        val fixImageHeight =
            requireContext().resources.getDimension(com.intuit.sdp.R.dimen._45sdp).toInt()
        val rvHeight = fixImageHeight * list.size
        binding.rvConversationListMenu.layoutParams.height = rvHeight

        adapterConversationList =
            AdapterCustomerList(requireContext(), list, object : AdapterCustomerList.Click {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSeletect(position: Int) {
                    for (i in 0 until adapterConversationList?.getListt()?.size!!) {
                        adapterConversationList?.getListt()?.get(i)?.selectedStatus = position == i
                    }
                    adapterConversationList?.notifyDataSetChanged()
                 /*   if(findNavController().currentDestination?.id == R.id.menuFragment)
                    findNavController().navigate(R.id.action_menuFragment_to_chatMessageFragment)*/
                }
            })
        binding.rvConversationListMenu.adapter = adapterConversationList
    }

    private fun rvLeadsListMenu() {
        val list = ArrayList<PojoCustomerList>()
        val tempOnlineList = listOf(true, false)
       /* for (i in 0 until 5) {
            list.add(PojoCustomerList("ab", "Customer${i + 34}", tempOnlineList.random(), i))
        }*/

        list.add(PojoCustomerList("ab", "Bardi", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Lincoln Rosser", tempOnlineList.random(), 2))
        list.add(PojoCustomerList("ab", "Jakob Vetrovs", tempOnlineList.random(), 2))
        list.add(PojoCustomerList("ab", "John C Flood", tempOnlineList.random(), 0))
        list.add(PojoCustomerList("ab", requireActivity().getString(R.string.add_lead), tempOnlineList.random(), -1))

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

                   /* if(findNavController().currentDestination?.id == R.id.menuFragment)
                        findNavController().navigate(R.id.action_menuFragment_to_chatMessageFragment)*/
                }
            })
        binding.rvLeadsListMenu.adapter = adapterLeadsList
    }

    private fun setTeamListAdapter() {
        val list = ArrayList<PojoCustomerList>()
        val tempOnlineList = listOf(true, false)
    /*    for (i in 0 until 5) {
            list.add(PojoCustomerList("ab", "Customer${i + 34}", tempOnlineList.random(), i))
        }*/

        list.add(PojoCustomerList("ab", "Omar Press", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Adison Septim...", tempOnlineList.random(), 2))
        list.add(PojoCustomerList("ab", "Talan George", tempOnlineList.random(), 1))
        list.add(PojoCustomerList("ab", "Madelyn Levin", tempOnlineList.random(), 0))
        list.add(PojoCustomerList("ab", "Zaire Stanton", tempOnlineList.random(), 0))
        list.add(PojoCustomerList("ab", requireActivity().getString(R.string.add_teammates), tempOnlineList.random(), -1))

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

                    /*if(findNavController().currentDestination?.id == R.id.menuFragment)
                        findNavController().navigate(R.id.action_menuFragment_to_chatMessageFragment)*/
                }
            })
        binding.rvTeamsListMenu.adapter = adapterTeamList
    }

    private fun setGroupListAdapter() {
        val list = ArrayList<PojoGroupMembersList>()
       /* for (i in 0 until 5) {
            list.add(PojoGroupMembersList("Group ${i + 2}", i, false))
        }*/
        list.add(PojoGroupMembersList("Sales", 1, false))
        list.add(PojoGroupMembersList("Human Resource", 2, false))
        list.add(PojoGroupMembersList("Operations", 1, false))
        list.add(PojoGroupMembersList("Reports", 2, false))
        list.add(PojoGroupMembersList("Engineers", 2, false))
        list.add(PojoGroupMembersList(requireActivity().getString(R.string.createGroups_text), -1, false))

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

                    /*if(findNavController().currentDestination?.id == R.id.menuFragment)
                        findNavController().navigate(R.id.action_menuFragment_to_chatMessageFragment)*/
                }
            })
        binding.rvGroupListMenu.adapter = adapterGroupsList
    }

    private fun setJobsListAdapter() {
        val list = ArrayList<PojoGroupMembersList>()
       /* for (i in 0 until 5) {
            list.add(PojoGroupMembersList("Jobs ${i + 2}", i, false))
        }*/

        list.add(PojoGroupMembersList("Discount_SH1h73", 1, false))
        list.add(PojoGroupMembersList("Palosi_39875", 2, false))
        list.add(PojoGroupMembersList("Skiffington_h90", 1, false))
       // list.add(PojoGroupMembersList(requireActivity().getString(R.string.createGroups_text), -2, false))

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

                  /*  if(findNavController().currentDestination?.id == R.id.menuFragment)
                        findNavController().navigate(R.id.action_menuFragment_to_chatMessageFragment)*/
                }
            })
        binding.rvJobsListMenu.adapter = adapterJobsList

    }

}