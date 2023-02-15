package com.miguelaboliveira.svpolitician

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miguelaboliveira.svpolitician.databinding.ActivityMainBinding
import com.miguelaboliveira.svpolitician.feature.history.ui.HistoryFragment
import com.miguelaboliveira.svpolitician.feature.home.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
public class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewPager.apply {
            isUserInputEnabled = false
            adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
                override fun getItemCount(): Int = 2
                override fun createFragment(position: Int): Fragment = when (position) {
                    0 -> HomeFragment()
                    1 -> HistoryFragment()
                    else -> error("Invalid position: $position")
                }
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    binding.viewPager.setCurrentItem(0, false)
                    true
                }

                R.id.nav_history -> {
                    binding.viewPager.setCurrentItem(1, false)
                    true
                }

                else -> error("Invalid itemId: ${it.itemId}")
            }
        }

        setContentView(binding.root)
    }
}
