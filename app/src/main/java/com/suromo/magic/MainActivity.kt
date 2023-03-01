package com.suromo.magic

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.suromo.magic.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(0xffFBFCF5.toInt()))
        val title = ImageView(this)
        title.setImageResource(R.drawable.ic_title)
        val actionbarLayout = LinearLayout(this)
        supportActionBar?.setCustomView(actionbarLayout,ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT))
        val lp = actionbarLayout.layoutParams as ActionBar.LayoutParams
        lp.gravity = lp.gravity and Gravity.HORIZONTAL_GRAVITY_MASK.inv() or Gravity.CENTER_HORIZONTAL
        actionbarLayout.addView(title)
        supportActionBar?.setCustomView(actionbarLayout,lp)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_recommend, R.id.navigation_history, R.id.navigation_statistics
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null
    }
}