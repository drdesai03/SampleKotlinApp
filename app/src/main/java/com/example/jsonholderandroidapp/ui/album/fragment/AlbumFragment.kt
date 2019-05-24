package com.example.jsonholderandroidapp.ui.album.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonholderandroidapp.R
import com.example.jsonholderandroidapp.base.fragment.BaseFragment
import com.example.jsonholderandroidapp.databinding.FragmentAlbumListBinding
import com.example.jsonholderandroidapp.extenstion.createViewModel
import com.example.jsonholderandroidapp.ui.album.adapter.AlbumRowAdapter
import com.example.jsonholderandroidapp.utils.Status
import com.example.jsonholderandroidapp.vm.album.AlbumViewModel

class AlbumFragment private constructor() : BaseFragment<FragmentAlbumListBinding>() {

    lateinit var adapter: AlbumRowAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_album_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL,
            false
        )
        adapter = AlbumRowAdapter(emptyList()) { albumEntity ->
            Toast.makeText(context, albumEntity.title, Toast.LENGTH_SHORT).show()
        }
        val itemDecorator = DividerItemDecoration(context, LinearLayout.VERTICAL)
        binding.recyclerView.addItemDecoration(itemDecorator)
        binding.recyclerView.adapter = adapter

        loadResult()

        binding.btnRetry.setOnClickListener { loadResult() }
    }

    private fun loadResult() {
        viewModel().getResult().observe(this, Observer { result ->
            binding.responseResource = result
            if (result.status == Status.SUCCESS || result.status == Status.LOADING) {
                adapter.updateList(result.data)
            }
        })
    }

    companion object {
        fun newInstance() = AlbumFragment()
    }

    private fun viewModel() = createViewModel(viewModelFactory, AlbumViewModel::class.java)
}