package com.example.jsonholderandroidapp.ui.album

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jsonholderandroidapp.R
import com.example.jsonholderandroidapp.extenstion.replaceFragment
import com.example.jsonholderandroidapp.extenstion.setupActionBar
import com.example.jsonholderandroidapp.ui.album.fragment.AlbumFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(R.id.container, AlbumFragment.newInstance())

        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.album_title)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
            return
        }
        super.onBackPressed()

    }
}
