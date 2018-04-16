package com.maiconhellmann.architecture.view.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.maiconhellmann.architecture.R
import com.maiconhellmann.architecture.misc.ext.hideKeyboard
import com.maiconhellmann.architecture.view.BaseActivity
import com.maiconhellmann.architecture.view.ViewConstants
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel


class MainActivity : BaseActivity() {

    val viewModel: MainViewModel by viewModel()

    /**
     * Selected menu id. Used to maintain state during the configuration changing
     */
    var menuItemId = R.id.navigation_movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEditTextSearchMovie()
        setupBottomNavigationMenun()

        viewModel.start()
    }

    private fun setupEditTextSearchMovie() {
        editTextSearchMovie.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = editTextSearchMovie.text.toString()
                viewModel.getMovieList(query)
                editTextSearchMovie.hideKeyboard()
                true
            } else {
                false
            }
        }
    }

    private fun setupBottomNavigationMenun() {
        bottomMenu.setOnNavigationItemSelectedListener {
            when {
                it.itemId == R.id.navigation_episode -> {
                    showEpisodeFragment()
                }
                it.itemId == R.id.navigation_movie -> {
                    showMovieFragment()
                }
                it.itemId == R.id.navigation_series -> {
                    showSeriesFragment()
                }
            }
            true
        }
        bottomMenu.selectedItemId = menuItemId
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ViewConstants.BOTTOM_NAVIGATION_MENU_INDEX, bottomMenu.selectedItemId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            menuItemId = it.get(ViewConstants.BOTTOM_NAVIGATION_MENU_INDEX) as Int
            bottomMenu.selectedItemId = menuItemId
        }
    }

    private fun showSeriesFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, SeriesFragment())
                .commit()
    }

    private fun showMovieFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieFragment())
                .commit()
    }

    private fun showEpisodeFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, EpisodeFragment())
                .commit()
    }
}
