package com.dapascript.brita.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dapascript.brita.R
import com.dapascript.brita.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, HomeFragment(), HomeFragment::class.java.simpleName)
            .commit()
    }
}