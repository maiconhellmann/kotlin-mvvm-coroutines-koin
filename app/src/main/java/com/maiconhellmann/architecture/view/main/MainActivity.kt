package com.maiconhellmann.architecture.view.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.maiconhellmann.architecture.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()

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
