package com.pablogarcia.my_movies.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.pablogarcia.my_movies.R
import com.pablogarcia.my_movies.databinding.FragmentSplashBinding
import com.pablogarcia.my_movies.ui.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment: BaseFragment<FragmentSplashBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(context = Dispatchers.Main) {

            delay(SPLASH_TIME_IN_FRONT)
            val action = SplashFragmentDirections.actionSplashToMoviesListMenu()
            findNavController().navigate(action)
        }
    }

    companion object {

        private const val SPLASH_TIME_IN_FRONT = 3500L
    }
}
