package com.example.jsonholderandroidapp.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class CustomViewHolder<out V : ViewDataBinding> constructor(val bindingObject: V) :
    RecyclerView.ViewHolder(bindingObject.root)