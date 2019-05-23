package com.example.jsonholderandroidapp.extenstion

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.jsonholderandroidapp.vm.CustomViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment) {
    supportFragmentManager.transact(fragment::class.simpleName) { replace(containerId, fragment) }
}

private inline fun FragmentManager.transact(fragmentName: String?, action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
        addToBackStack(fragmentName)
    }.commit()
}

fun <T : ViewModel> Fragment.createViewModel(factory: ViewModelProvider.Factory, viewModelClass: Class<T>) =
    ViewModelProviders.of(this, factory).get(viewModelClass)