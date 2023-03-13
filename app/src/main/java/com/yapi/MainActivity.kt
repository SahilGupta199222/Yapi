package com.yapi

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.yapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(dataBinding.root)
        activity = WeakReference<Activity>(this)
            initUI()
    }


    private fun initUI() {

      /*  val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.signupTeam)*/
      //  findNavController(R.id.signupTeam)
        dataBinding.apply {
            vModel = viewModel
        }
    }


    override fun onStart() {
        super.onStart()
        activity = WeakReference(this@MainActivity)
    }
    companion object {
        var activity: WeakReference<Activity>? = null
    }
}