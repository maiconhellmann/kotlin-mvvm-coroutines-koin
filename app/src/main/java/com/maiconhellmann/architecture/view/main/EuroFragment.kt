package com.maiconhellmann.architecture.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maiconhellmann.architecture.R
import com.maiconhellmann.architecture.misc.ViewLifecycleFragment
import com.maiconhellmann.architecture.misc.ext.gone
import com.maiconhellmann.architecture.misc.ext.observe
import com.maiconhellmann.architecture.misc.ext.visible
import kotlinx.android.synthetic.main.fragment_rate.*
import org.koin.android.architecture.ext.viewModel

class EuroFragment : ViewLifecycleFragment() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rate, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observe(viewModel.rate, {
            rateValue.text = it?.euro?.toString()
        })
        observe(viewModel.isDataLoading, {
            if(it == true){
                viewProgressBar.visible()
            }else{
                viewProgressBar.gone()
            }
        })
    }
}