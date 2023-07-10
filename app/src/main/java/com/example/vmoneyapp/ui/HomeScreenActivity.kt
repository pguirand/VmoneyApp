package com.example.vmoneyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vmoneyapp.R
import com.example.vmoneyapp.databinding.ActivityHomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeScreenBinding
    lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_home_screen)

        val navView : BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_people,
                R.id.navigation_rooms
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id) {
                R.id.navigation_home -> {
                    navView.visibility = View.VISIBLE
                }
                R.id.navigation_people -> {
                    navView.visibility = View.VISIBLE

                }
                R.id.navigation_rooms -> {
                    navView.visibility = View.VISIBLE

                }
                else -> {
                    navView.visibility = View.GONE
                }
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}