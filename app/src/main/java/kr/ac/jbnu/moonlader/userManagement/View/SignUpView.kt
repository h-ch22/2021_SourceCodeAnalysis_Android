package kr.ac.jbnu.moonlader.userManagement.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.ac.jbnu.moonlader.databinding.LayoutSignupBinding
import kr.ac.jbnu.moonlader.userManagement.models.SignUpViewModel

class SignUpView : Fragment() {
    companion object{
        fun newInstance() : SignUpView {
            return SignUpView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutSignupBinding.inflate(inflater, container, false)
        val viewModel = context?.let { SignUpViewModel(it) }

        view.signUpViewModel = viewModel
        return view.root
    }
}