package com.swkang.playground.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.swkang.model.base.BaseViewModel

abstract class BaseFragment : Fragment() {
    private lateinit var viewModel: BaseViewModel
    private var _binding: ViewDataBinding? = null
    private val binding get() = _binding!!

    abstract fun getLayoutId(): Int

    abstract fun createViewModel(): BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = createViewModel()

        _binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
//        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}