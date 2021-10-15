package kr.ac.jbnu.moonlader

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kr.ac.jbnu.moonlader.framework.View.NavigationViewManager
import kr.ac.jbnu.moonlader.userManagement.View.SignInView
import kr.ac.jbnu.moonlader.userManagement.models.FragmentChangeListener

class activity_start : AppCompatActivity(), FragmentChangeListener {
    private lateinit var auth : FirebaseAuth
    private lateinit var fragmentManager : FragmentManager
    private lateinit var fragment_signIn : SignInView

    override fun onStart() {
        super.onStart()

        FirebaseApp.initializeApp(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_main)

        auth = Firebase.auth
        fragment_signIn = SignInView.newInstance()
        fragmentManager = supportFragmentManager

        if (auth.currentUser == null){
            replaceFragment(fragment_signIn)
        }

        else{
            val intent = Intent(this, NavigationViewManager :: class.java)
            startActivity(intent)

            finish()
        }
    }

    override fun replaceFragment(fragment: Fragment) {
        fragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.anim_translate,
                R.anim.anim_alpha
            )
            .replace(R.id.View, fragment)
            .commit()
    }
}