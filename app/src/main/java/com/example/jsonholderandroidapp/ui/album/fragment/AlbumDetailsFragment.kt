package com.example.jsonholderandroidapp.ui.album.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.jsonholderandroidapp.R
import com.example.jsonholderandroidapp.base.fragment.BaseFragment
import com.example.jsonholderandroidapp.databinding.FragmentAlbumDetailsBinding
import com.example.jsonholderandroidapp.extenstion.createViewModel
import com.example.jsonholderandroidapp.utils.Status
import com.example.jsonholderandroidapp.vm.album.AlbumDetailsViewModel

class AlbumDetailsFragment constructor(val albumId: Int) : BaseFragment<FragmentAlbumDetailsBinding>() {


    override fun getLayout(): Int {
        return R.layout.fragment_album_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAlbumDetails()
    }

    private fun loadAlbumDetails() {
//        val albumId = arguments?.let { it.getInt("albumId") } ?: run {0}
        viewModel().getAlbumDetails(albumId).observe(this, Observer { result ->
            if (result.status == Status.SUCCESS) {
                binding.txtAlbumName.text = result.data!!.title
            }
        })
    }

    companion object {

        fun newInstance(albumId: Int) = AlbumDetailsFragment(albumId)
//        fun newInstance(albumId: Int): AlbumDetailsFragment {
//            val fragment = AlbumDetailsFragment()
//            val bundle = Bundle()
//            bundle.putInt("albumId", albumId)
//            fragment.arguments = bundle
//            return fragment
//        }
    }

    private fun viewModel() = createViewModel(viewModelFactory, AlbumDetailsViewModel::class.java)

}