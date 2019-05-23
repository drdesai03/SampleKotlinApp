package com.example.jsonholderandroidapp.ui.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.jsonholderandroidapp.R
import com.example.jsonholderandroidapp.base.adapter.BaseRecyclerAdapter
import com.example.jsonholderandroidapp.databinding.RowAlbumBinding
import com.example.jsonholderandroidapp.db.entity.AlbumEntity

class AlbumRowAdapter constructor(
    private var albumList: List<AlbumEntity>,
    private val itemClickListener: ((AlbumEntity) -> Unit)?
) :
    BaseRecyclerAdapter<RowAlbumBinding>() {

    override fun buildBinding(parent: ViewGroup): RowAlbumBinding {
        val rowBinding = DataBindingUtil.inflate<RowAlbumBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_album,
            parent,
            false
        )
        rowBinding.root.setOnClickListener {
            rowBinding.albumDetails?.let {
                itemClickListener?.invoke(it)
            }
        }
        return rowBinding
    }

    override fun updateRow(binding: RowAlbumBinding, position: Int) {
        binding.albumDetails = albumList.get(position)
        binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    fun updateList(updatedAlbumList: List<AlbumEntity>?) {
        albumList = updatedAlbumList ?: emptyList()
        notifyDataSetChanged()
    }

}