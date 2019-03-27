package com.be.stepper.core

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.app.caliwood.core.rx.DisposeOnLifecycleFragment
import com.app.caliwood.core.rx.LifecycleDisposables

import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment(), DisposeOnLifecycleFragment {

    override val lifecycleDisposables = LifecycleDisposables()
    lateinit var process: ProgressDialog

    private fun createProgressDialog() {
        try {

            //TODO: Put strins resources
            activity?.let {
                process = ProgressDialog(it)
                process.setMessage("Loading..")
                process.setCancelable(false)
                process.isIndeterminate = true
            }

        } catch (e: Exception) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        if (!::process.isInitialized) {
            createProgressDialog()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFinishedViewLoad()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(fragmentLayout(), container, false)


    public fun switchProgressDialog(show: Boolean) {
        try {

            process?.let {
                if (show && !it.isShowing) {
                    it.show()
                } else if (!show && it.isShowing) {
                    it.hide()
                }
            }
        } catch (e: Exception) {

        }
    }

    override fun onPause() {
        super<Fragment>.onPause()
        super<DisposeOnLifecycleFragment>.onPause()
    }

    override fun onStop() {
        super<Fragment>.onStop()
        super<DisposeOnLifecycleFragment>.onStop()
    }

    override fun onDestroyView() {
        super<Fragment>.onDestroyView()
        super<DisposeOnLifecycleFragment>.onDestroyView()
    }

    override fun onDestroy() {
        super<Fragment>.onDestroy()
        super<DisposeOnLifecycleFragment>.onDestroy()
    }

    @LayoutRes
    abstract fun fragmentLayout(): Int

    abstract fun onFinishedViewLoad()

    protected fun navController(): NavController? {
        return view?.let { Navigation.findNavController(it) }
    }

}