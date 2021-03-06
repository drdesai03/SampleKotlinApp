package com.example.jsonholderandroidapp.extenstion

import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment) {
    supportFragmentManager.transact(fragment::class.simpleName) { replace(containerId, fragment) }
}

private inline fun FragmentManager.transact(fragmentName: String?, action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
        addToBackStack(fragmentName)
    }.commit()
}

fun AppCompatActivity.setupActionBar(@IdRes toolbarId: Int, action: ActionBar.() -> Unit) {
    setSupportActionBar(findViewById(toolbarId))
    supportActionBar?.run {
        action()
    }
}

fun <T : ViewModel> Fragment.createViewModel(factory: ViewModelProvider.Factory, viewModelClass: Class<T>) =
    ViewModelProviders.of(this, factory).get(viewModelClass)