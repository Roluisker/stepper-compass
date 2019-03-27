package com.be.stepper.ui.view.home

import com.be.stepper.R
import com.be.stepper.core.BaseFragment
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: HomeModel

    override fun onFinishedViewLoad() {
        viewModel.test()
    }

    override fun fragmentLayout(): Int = R.layout.home_fragment

}