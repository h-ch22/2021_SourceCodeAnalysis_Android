package kr.ac.jbnu.moonlader.framework.models

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.ac.jbnu.moonlader.R
import kr.ac.jbnu.moonlader.activity_start
import kr.ac.jbnu.moonlader.controller.View.ControllerView
import kr.ac.jbnu.moonlader.framework.View.NavigationViewManager
import kr.ac.jbnu.moonlader.home.View.HomeView
import kr.ac.jbnu.moonlader.more.View.MoreView
import kr.ac.jbnu.moonlader.userManagement.View.SignUpView

class NavigationViewController(navBar : BottomNavigationView){
    private val navBar = navBar

    fun initNavBar(){
        navBar.run{
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.action_home -> {
                        (context as NavigationViewManager).replaceFragment(HomeView.newInstance())
                    }

                    R.id.action_controller -> {
                        (context as NavigationViewManager).replaceFragment(ControllerView.newInstance())
                    }

                    R.id.action_more -> {
                        (context as NavigationViewManager).replaceFragment(MoreView.newInstance())
                    }

                    else -> {
                        (context as NavigationViewManager).replaceFragment(HomeView.newInstance())
                    }

                }

                true
            }

            selectedItemId = R.id.action_home
        }
    }
}