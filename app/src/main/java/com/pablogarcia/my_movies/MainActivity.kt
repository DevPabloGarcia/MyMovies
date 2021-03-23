package com.pablogarcia.my_movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pablogarcia.my_movies.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        this.setupNavigation()
    }

    //region PRIVATE_METHODS

    /**
     * Setup navigation
     */
    private fun setupNavigation() {

        binding.navigationView.itemIconTintList = null
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment
        ) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->

            this.changeBottomBarVisibility(destination)
        }
        binding.navigationView.setupWithNavController(navController)
    }

    /**
     * Change bottom navigation bar visibility
     *
     * @param destination - navigation destination
     */
    private fun changeBottomBarVisibility(destination: NavDestination) {

        binding.navigationView.visibility = when (destination.id) {

            R.id.splash -> View.GONE
            else -> View.VISIBLE
        }
    }

    //endregion
}
