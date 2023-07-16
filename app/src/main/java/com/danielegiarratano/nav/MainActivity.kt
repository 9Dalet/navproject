package com.danielegiarratano.nav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.danielegiarratano.nav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Initialize fragments
    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()
    private val settingsFragment = SettingsFragment()

    // Set homeFragment as the initially active fragment
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set click listener for the bottom navigation items
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    switchFragment(homeFragment)
                    true
                }
                R.id.navigation_profile -> {
                    switchFragment(profileFragment)
                    true
                }
                R.id.navigation_settings -> {
                    switchFragment(settingsFragment)
                    true
                }
                else -> false
            }
        }

        // Add fragments to the fragment container and hide all except the active fragment
        supportFragmentManager.beginTransaction().apply {
            add(binding.fragmentContainer.id, settingsFragment, "3")
            hide(settingsFragment)
            add(binding.fragmentContainer.id, profileFragment, "2")
            hide(profileFragment)
            add(binding.fragmentContainer.id, homeFragment, "1")
        }.commit()
    }

    // Function to switch between fragments
    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }
}
