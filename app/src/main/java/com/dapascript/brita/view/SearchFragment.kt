package com.dapascript.brita.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapascript.brita.adapter.SearchAdapter
import com.dapascript.brita.data.NewsRepository
import com.dapascript.brita.data.network.ApiConfig
import com.dapascript.brita.databinding.FragmentSearchBinding
import com.dapascript.brita.utils.Resource
import com.dapascript.brita.viewmodel.NewsViewModel
import com.dapascript.brita.viewmodel.ViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotEmpty()) {
                        binding.tvPleaseSearch.visibility = View.GONE
                        setViewModel(it)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        setAdapter()
    }

    private fun setViewModel(q: String) {
        val factory = ViewModelFactory(NewsRepository(ApiConfig.provideApiService()))
        newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        newsViewModel.getNewsSearch(q)
        newsViewModel.searchNews.observe(viewLifecycleOwner) {
            binding.apply {
                when (it) {
                    is Resource.Loading -> {
                        rvSearch.visibility = View.GONE
                        progressbar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        rvSearch.visibility = View.VISIBLE
                        progressbar.visibility = View.GONE
                        searchAdapter.addList(it.data!!)
                    }
                    is Resource.Error -> {
                        rvSearch.visibility = View.GONE
                        progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        searchAdapter = SearchAdapter()
        binding.rvSearch.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
}