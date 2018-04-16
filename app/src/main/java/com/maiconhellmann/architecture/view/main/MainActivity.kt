package com.maiconhellmann.architecture.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.maiconhellmann.architecture.R
import com.maiconhellmann.architecture.view.ViewConstants
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel


class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()

    var menuIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getLatestRate()

        setupBottomNavigationMenun()
        showEuroFragment()
    }

    private fun setupBottomNavigationMenun() {
        bottomMenu.setOnNavigationItemSelectedListener {
            when {
                it.itemId == R.id.navigation_eur -> {
                    showEuroFragment()
                }
                it.itemId == R.id.navigation_usd -> {
                    showUsdFragment()
                }
                it.itemId == R.id.navigation_brl -> {
                    showBrlFragment()
                }
            }
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ViewConstants.BOTTOM_NAVIGATION_MENU_INDEX, bottomMenu.selectedItemId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.let {
            menuIndex = it.get(ViewConstants.BOTTOM_NAVIGATION_MENU_INDEX) as Int
            bottomMenu.selectedItemId = menuIndex
        }
    }

    private fun showBrlFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, BrlFragment())
                .commit()
    }

    private fun showUsdFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, UsdFragment())
                .commit()
    }

    private fun showEuroFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, EuroFragment())
                .commit()
    }
}
