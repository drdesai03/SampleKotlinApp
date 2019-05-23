package com.example.jsonholderandroidapp.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jsonholderandroidapp.di.component.Injectable
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), Injectable {

    lateinit var binding: T

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    @LayoutRes
    protected abstract fun getLayout(): Int
}