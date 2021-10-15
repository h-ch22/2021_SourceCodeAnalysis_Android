package kr.ac.jbnu.moonlader.framework.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.ac.jbnu.moonlader.R
import kr.ac.jbnu.moonlader.framework.models.NavigationViewController
import kr.ac.jbnu.moonlader.userManagement.models.FragmentChangeListener

open class NavigationViewManager : AppCompatActivity() , FragmentChangeListener{
    private lateinit var helper : NavigationViewController
    private val fragmentManager  = supportFragmentManager
    private lateinit var navBar : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_navigationview)

        navBar = findViewById(R.id.navigationBar)

        helper = NavigationViewController(navBar)

        helper.initNavBar()
    }


    override fun replaceFragment(fragment: Fragment) {
        fragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.anim_translate,
                R.anim.anim_alpha
            )
            .replace(R.id.view, fragment)
            .commit()
    }
}