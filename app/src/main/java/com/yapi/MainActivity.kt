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
import com.yapi.module.DeleteGroupFragment
import com.yapi.module.LeaveGroupFragment
import com.yapi.pref.PreferenceFile
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var month: String
    private lateinit var dataBinding: ActivityMainBinding
    private var btnSave: Button? = null
    private var tvName: TextView? = null

    @Inject
    @Named("abed")
    lateinit var nameValue: String

    @Inject
    lateinit var prefUtil: PreferenceFile
    val viewModel: DataViewModel by viewModels()

    @Inject
    lateinit var retrofitAPI: RetrofitAPI

    companion object{
        var activity:WeakReference<Activity>?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(dataBinding.root)
        activity=WeakReference<Activity>(this)
        month = "02"
        Log.e("efmekfmkefe===", ::nameValue.isInitialized.toString())
        Log.e("fgwegggwgwe===", nameValue)

        initUI()
    }

    override fun onStart() {
        super.onStart()

    }

    private  fun initUI() {

      //  supportFragmentManager.beginTransaction().replace(R.id.frameLayout,DeleteGroupFragment()).commit()
        //supportFragmentManager.beginTransaction().replace(R.id.frameLayout,LeaveGroupFragment()).commit()


        dataBinding.apply {
            vModel = viewModel
            btnfetch.setOnClickListener {
                var dateValue=prefUtil.fetchStringValue("name")
                var milisecondValue=convertFromDateToMiliSeconds(dateValue)
                //hideProgress()
                Log.e("efefefef===",Constants.BASEURL)
                Log.e("show_dataaa===",milisecondValue.toString())
            }
        }
        dataBinding.tvName.text = prefUtil.fetchStringValue("name")
        dataBinding.btnSave.setOnClickListener {
            if(isNetworkConnected()) {
                viewModel.getCategoryMethod()
            }else
            {
                showMessage(getString(R.string.check_internet_connection))
            }
            var mili=System.currentTimeMillis()
            Log.e("show_dataaa222===",mili.toString())
            var dateValue=convertMiliToDateFormat(mili)
            prefUtil.saveStringValue("name", dateValue)
            Log.e("show_dataaa111===",dateValue.toString())
            hideKeyboard()
            showMessage(dataBinding.etNameee.text.toString().trim())
            //showProgress()
        }
    }
}