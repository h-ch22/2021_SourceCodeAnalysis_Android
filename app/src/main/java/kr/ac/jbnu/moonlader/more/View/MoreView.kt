package kr.ac.jbnu.moonlader.more.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.ac.jbnu.moonlader.databinding.LayoutMoreBinding
import kr.ac.jbnu.moonlader.more.models.MoreViewModel

class MoreView : Fragment() {
    companion object{
        fun newInstance() : MoreView {
            return MoreView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutMoreBinding.inflate(inflater, container, false)
        val viewModel = context?.let { MoreViewModel(it) }

        view.viewModel = viewModel
        return view.root
    }
}