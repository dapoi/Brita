package com.dapascript.brita.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapascript.brita.adapter.BusinessAdapter
import com.dapascript.brita.data.NewsRepository
import com.dapascript.brita.data.network.ApiConfig
import com.dapascript.brita.databinding.FragmentBusinessBinding
import com.dapascript.brita.utils.Resource
import com.dapascript.brita.viewmodel.NewsViewModel
import com.dapascript.brita.viewmodel.ViewModelFactory

class BusinessFragment : Fragment() {

    private lateinit var binding: FragmentBusinessBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var businessAdapter: BusinessAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBusinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        setAdapter()
    }

    private fun setViewModel() {
        val factory = ViewModelFactory(NewsRepository(ApiConfig.provideApiService()))
        newsViewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
        newsViewModel.getBusinessNews()
        newsViewModel.businessNews.observe(viewLifecycleOwner) {
            with(binding) {
                when (it) {
                    is Resource.Loading -> progressbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progressbar.visibility = View.GONE
                        businessAdapter.addList(it.data!!)
                    }
                    is Resource.Error -> progressbar.visibility = View.GONE
                }
            }
        }
    }

    private fun setAdapter() {
        businessAdapter = BusinessAdapter()
        binding.rvBusinessNews.apply {
            adapter = businessAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
}