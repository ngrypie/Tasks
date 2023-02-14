package com.sergeygolovkin.tasks.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.sergeygolovkin.tasks.R
import com.sergeygolovkin.tasks.databinding.ActivityMainBinding
import com.sergeygolovkin.tasks.ui.tasklist.TaskListFragment

/**
 * Активити приложения Tasks. Предполагается подход Single Activity Application
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState ?:
        supportFragmentManager
            .beginTransaction()
            .add(binding.rootContainer.id, TaskListFragment.newInstance(), null)
            .commit()
    }
}