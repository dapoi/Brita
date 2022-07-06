package com.dapascript.brita.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dapascript.brita.view.BusinessFragment
import com.dapascript.brita.view.TechFragment

/**
 * @Author: Luthfi Daffa Prabowo
 * @Date: 05/07/2022 23.46
 */
class NewsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        return when (position) {
            0 -> TechFragment()
            1 -> BusinessFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}