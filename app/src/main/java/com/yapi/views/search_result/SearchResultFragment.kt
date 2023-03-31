package com.yapi.views.search_result

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yapi.R


class SearchResultFragment : Fragment() {
    private lateinit var binding:com.yapi.databinding.FragmentSearchResultBinding
    private lateinit var adapterr:AdapterTabSearchResult
    private lateinit var tabList: ArrayList<PojoTabSearchResult>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= com.yapi.databinding.FragmentSearchResultBinding.inflate(LayoutInflater.from(requireActivity()))
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
init()

    }

    private fun init() {
        binding.apply {

            txtTempResultSearch.setOnClickListener {
                findNavController().popBackStack()
            }
            imgCancelSearch.setOnClickListener {
                findNavController().popBackStack()
            }
            imgCancelTabSearchResult.setOnClickListener {
                findNavController().popBackStack()
            }
            val check=arguments?.getBoolean("empty")?:false
            if(check==true){
                setTabLayout(true)
                rvSearchResult.visibility = View.GONE
                layoutNoDataSearchResult.visibility = View.VISIBLE
            }else {
                setTabLayout(false)
//                viewLineAllSearchResult.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.blueColor))
                layoutNoDataSearchResult.visibility = View.GONE
                rvSearchResult.visibility = View.VISIBLE
                val resentSearchList = ArrayList<PojoSearchScreenData>()
                resentSearchList.add(PojoSearchScreenData(3,
                    "Messeges",
                    "VIEW MESSAGES",
                    "Kaylynn",
                    null,
                    ContextCompat.getDrawable(requireContext(), R.drawable.demo_photo),
                    "Human Resource",
                    "JANUARY 13, 2023",
                    "13:25",
                    "Consulting a Latin dictionary Job Page Design to a passage from Do Finibus Bonorum"
                ))
                resentSearchList.add(PojoSearchScreenData(3,
                    "Messeges",
                    "VIEW MESSAGES",
                    "Zaire",
                    null,
                    ContextCompat.getDrawable(requireContext(), R.drawable.demo_photo),
                    "DesignGig",
                    "JANUARY 05, 2023",
                    "01:05",
                    "Documentation is very important task. It is needed to be finished by this week."))

                resentSearchList.add(PojoSearchScreenData(1,
                    "File",
                    "VIEW FILES",
                    "Documentation.pdf",
                    "0.4mb",
                    ContextCompat.getDrawable(requireContext(), R.drawable.file)))
                resentSearchList.add(PojoSearchScreenData(1,
                    "File",
                    "VIEW FILES",
                    "Document_drafter.pdf",
                    "1.5mb",
                    ContextCompat.getDrawable(requireContext(), R.drawable.file)))
                searchList(resentSearchList)

            }   }
    }
    private fun searchList(list:ArrayList<PojoSearchScreenData>) {
        binding.apply {
            rvSearchResult.adapter = AdapterSearch(requireContext(), list)
        }
    }
    private fun setTabLayout(empty:Boolean) {
        tabList = ArrayList<PojoTabSearchResult>()
        if (empty) {
            tabList.add(PojoTabSearchResult("All", 0, 2))
            tabList.add(PojoTabSearchResult("Messages", 0, 2))
            tabList.add(PojoTabSearchResult("Files", 0, 2))
            tabList.add(PojoTabSearchResult("People", 0, 2))
            tabList.add(PojoTabSearchResult("Groups", 0, 2))

        } else {
            tabList.add(PojoTabSearchResult("All", 12, 0))
            tabList.add(PojoTabSearchResult("Messages", 2, 1))
            tabList.add(PojoTabSearchResult("Files", 24, 1))
            tabList.add(PojoTabSearchResult("People", 4, 1))
            tabList.add(PojoTabSearchResult("Groups", 45, 1))

        }
        adapterr = AdapterTabSearchResult(requireContext(),
            tabList,
            object : AdapterTabSearchResult.Click {
                override fun onClick(position: Int) {
                    if (!empty) {
                        for (i in 0 until tabList.size) {
                            if (i == position) {
                                tabList[i].selected = 0
                            } else {
                                tabList[i].selected = 1
                            }
                        }
                        adapterr.changeList(tabList)
                    }
                }
            })
        binding.rvTabLayoutSearchResult.adapter = adapterr
    }

}