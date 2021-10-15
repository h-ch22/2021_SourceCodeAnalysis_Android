package kr.ac.jbnu.moonlader.userManagement.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.ac.jbnu.moonlader.userManagement.models.SignInViewModel
import kr.ac.jbnu.moonlader.databinding.LayoutSigninBinding

class SignInView : Fragment() {
    companion object{
        fun newInstance() : SignInView {
            return SignInView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutSigninBinding.inflate(inflater, container, false)
        val viewModel = context?.let { SignInViewModel(it) }

        view.viewModel = viewModel
        return view.root
    }
}