package com.yapi.views.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.yapi.R
import com.yapi.databinding.FragmentSearchBinding
import com.yapi.views.search_result.AdapterSearch
import com.yapi.views.search_result.PojoSearchScreenData


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewmodel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(LayoutInflater.from(requireContext()))
        binding.mViewModel = viewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            recentList()

            etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(
                    v: TextView?,
                    actionId: Int,
                    event: KeyEvent?,
                ): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        if (etSearch.text.toString().isEmpty()) {

                            if (findNavController().currentDestination?.id == R.id.searchFragment) {
                                val bundel = Bundle()
                                bundel.putBoolean("empty", true)
                                findNavController()
                                    .navigate(R.id.action_searchFragment_to_searchResultFragment,
                                        bundel)
                            }

                        } else {
                            if (findNavController().currentDestination?.id == R.id.searchFragment)
                                findNavController().navigate(R.id.action_searchFragment_to_searchResultFragment)

                        }
                    }
                    return true
                }
            })
            etSearch.doAfterTextChanged { t ->

                if (t.toString() == "do") {
                    txtTempViewHistorySearch.visibility = View.GONE
                    txtTempClearSearch.visibility = View.GONE
                    txtTempRecentSearch.visibility = View.GONE
                    val resentSearchList = ArrayList<PojoSearchScreenData>()
                    resentSearchList.add(PojoSearchScreenData(1,
                        "File",
                        "VIEW FILES",
                        "Documentation.pdf",
                        "0.4mb",
                        ContextCompat.getDrawable(requireContext(), R.drawable.person)))
                    resentSearchList.add(PojoSearchScreenData(1,
                        "File",
                        "VIEW FILES",
                        "Document_drafter.pdf",
                        "1.5mb",
                        ContextCompat.getDrawable(requireContext(), R.drawable.person)))
                    resentSearchList.add(PojoSearchScreenData(0,
                        "People",
                        "VIEW ALL",
                        "Donald Sutherland",
                        "Frontend Engineer",
                        ContextCompat.getDrawable(requireContext(), R.drawable.demo_photo)))
                    resentSearchList.add(PojoSearchScreenData(0,
                        "People",
                        "VIEW ALL",
                        "Michelle Dochery",
                        "Designer",
                        ContextCompat.getDrawable(requireContext(), R.drawable.demo_photo)))
                    searchList(resentSearchList)
                } else {

                    txtTempViewHistorySearch.visibility = View.VISIBLE
                    txtTempClearSearch.visibility = View.VISIBLE
                    txtTempRecentSearch.visibility = View.VISIBLE
                    recentList()

                }
            }
        }
    }

    private fun searchList(list: ArrayList<PojoSearchScreenData>) {
        binding.apply {
            rvSearch.adapter = AdapterSearch(requireContext(), list)
        }
    }

    private fun recentList() {
        val resentSearchList = ArrayList<PojoSearchScreenData>()
        resentSearchList.add(PojoSearchScreenData(1, null, null, "Dashboard designs", null,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_replay_24)))
        resentSearchList.add(PojoSearchScreenData(0,
            null,
            null,
            "Omar Calzoni",
            "Frontend Engineer",
            ContextCompat.getDrawable(requireContext(), R.drawable.demo_photo)))
        resentSearchList.add(PojoSearchScreenData(1,
            null,
            null,
            "Meeting schedule.pdf",
            "0.4mb",
            ContextCompat.getDrawable(requireContext(), R.drawable.file), endViewLine = true))
        searchList(resentSearchList)
    }
}