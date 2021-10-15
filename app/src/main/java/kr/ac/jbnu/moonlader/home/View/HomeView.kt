package kr.ac.jbnu.moonlader.home.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.jbnu.moonlader.R
import kr.ac.jbnu.moonlader.databinding.LayoutHomeBinding
import kr.ac.jbnu.moonlader.home.models.HomeViewModel

class HomeView : Fragment(){
    private lateinit var binding : LayoutHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewModelFactory : ViewModelProvider.AndroidViewModelFactory

    companion object{
        fun newInstance() : HomeView {
            return HomeView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_home, container, false)
        binding.setLifecycleOwner(this)

        viewModelFactory = activity?.let {
            ViewModelProvider.AndroidViewModelFactory.getInstance(it.application)
        }!!

        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel :: class.java)

        binding.viewModel = homeViewModel
        homeViewModel.getRank()

        return binding.root
    }
}