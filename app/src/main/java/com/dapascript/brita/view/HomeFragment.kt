package com.dapascript.brita.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dapascript.brita.R
import com.dapascript.brita.adapter.NewsPagerAdapter
import com.dapascript.brita.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pager = NewsPagerAdapter(activity as AppCompatActivity)
        binding.viewPager.apply {
            adapter = pager
            offscreenPageLimit = 2

            TabLayoutMediator(binding.tabsNews, this) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        binding.imgSearch.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.frame_container,
                    SearchFragment(),
                    SearchFragment::class.java.simpleName
                )
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Remove all fragments from the childFragmentManager,
                // but exclude the first added child fragment.
                // This child fragment will be deleted with its parent.
                if (childFragmentManager.backStackEntryCount >= 1) {
                    childFragmentManager.popBackStack()
                    return
                }
                // Delete parent fragment
                parentFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, backCallback)
    }

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.tech, R.string.business)
    }
}