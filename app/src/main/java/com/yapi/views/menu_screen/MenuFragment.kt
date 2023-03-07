package com.yapi.views.menu_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var binding:FragmentMenuBinding
    private var groupListClicked = false
    private var jobListClicked = false
    private var customerListClicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMenuBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            etSearchMenu.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }
            layoutBoatMenu.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }
            layoutTeamMenu.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }
            layoutLeadMenu.setOnClickListener {
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

 /*   private fun setCustomerListAdapter() {
        val list = ArrayList<PojoCustomerList>()
        val tempOnlineList = listOf(true, false)
        for (i in 0 until 5) {
            list.add(PojoCustomerList("ab", "Customer${i + 34}", tempOnlineList.random(), i))
        }
        val fixImageHeight = requireContext().resources.getDimension(com.intuit.sdp.R.dimen._45sdp).toInt()
        val rvHeight = fixImageHeight * list.size
        binding.rvCustomersListMenu.layoutParams.height = rvHeight

        val adapter = AdapterCustomerList(requireContext(), list)
        binding.rvCustomersListMenu.adapter = adapter
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

        val adapter = AdapterGroupsList(requireContext(), list)
        binding.rvGroupListMenu.adapter = adapter
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

        val adapter = AdapterGroupsList(requireContext(), list)
        binding.rvJobsListMenu.adapter = adapter

    }
*/

}