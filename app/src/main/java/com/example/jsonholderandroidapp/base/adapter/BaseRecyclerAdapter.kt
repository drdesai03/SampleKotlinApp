package com.example.jsonholderandroidapp.base.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T : ViewDataBinding> :
    RecyclerView.Adapter<CustomViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder<T> {
        val binding = buildBinding(parent)
        return CustomViewHolder(binding)
    }

    abstract fun buildBinding(parent: ViewGroup): T

    override fun onBindViewHolder(holder: CustomViewHolder<T>, position: Int) {
        updateRow(holder.bindingObject, position)
    }

    abstract fun updateRow(binding: T, position: Int)

}