package com.yapi

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yapi.common.*
import com.yapi.databinding.ActivityMainBinding
import com.yapi.views.leaveGroup.LeaveGroupFragment
import com.yapi.pref.PreferenceFile
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Named

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