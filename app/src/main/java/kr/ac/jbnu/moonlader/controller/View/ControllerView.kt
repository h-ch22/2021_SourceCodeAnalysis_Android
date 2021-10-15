package kr.ac.jbnu.moonlader.controller.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.controlwear.virtual.joystick.android.JoystickView
import kr.ac.jbnu.moonlader.R
import kr.ac.jbnu.moonlader.controller.models.ControllerViewModel
import kr.ac.jbnu.moonlader.databinding.LayoutControllerBinding

class ControllerView : Fragment() {
    lateinit var controller : JoystickView

    companion object{
        fun newInstance() : ControllerView {
            return ControllerView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutControllerBinding.inflate(inflater, container, false)
        view?.setLifecycleOwner(this)
        val viewModel = context?.let { ControllerViewModel(it, this) }

        controller = view.root.findViewById(R.id.controller)

        view.viewModel = viewModel

        viewModel?.controllerHandler()

        return view.root
    }
}