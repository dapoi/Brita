package com.dapascript.brita.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapascript.brita.adapter.TechAdapter
import com.dapascript.brita.data.NewsRepository
import com.dapascript.brita.data.model.ArticlesItem
import com.dapascript.brita.data.network.ApiConfig
import com.dapascript.brita.databinding.FragmentTechBinding
import com.dapascript.brita.utils.Resource
import com.dapascript.brita.viewmodel.NewsViewModel
import com.dapascript.brita.viewmodel.ViewModelFactory

class TechFragment : Fragment() {

    private lateinit var binding: FragmentTechBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var techAdapter: TechAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTechBinding.inflate(inflater, container, false)
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
        newsViewModel.getTechNews()
        newsViewModel.techNews.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressbar.visibility = View.GONE
                    techAdapter.addList(it.data as ArrayList<ArticlesItem>)
                }
                is Resource.Error -> binding.progressbar.visibility = View.GONE

            }
        }
    }

    private fun setAdapter() {
        techAdapter = TechAdapter()
        binding.rvTechNews.apply {
            adapter = techAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }
}