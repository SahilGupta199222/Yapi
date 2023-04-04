package com.yapi

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.yapi.common.Constants
import com.yapi.common.MyMessageEvent
import com.yapi.common.Repository_Factory.newInstance
import com.yapi.databinding.ActivityMainBinding
import com.yapi.views.chat.ChatMessagesFragment
import com.yapi.views.chat.chatGroupInfo.ChatGroupInfoFragment
import com.yapi.views.chat.chatUserInfo.ChatUserInfoFragment
import com.yapi.views.chat_empty.ChatEmptyFragment
import com.yapi.views.create_group.CreateGroupFragment
import com.yapi.views.menu_screen.MenuFragment
import com.yapi.views.userList.UserListFragment
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var dataBinding: ActivityMainBinding
    val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(dataBinding.root)
        activity = WeakReference<Activity>(this)
        EventBus.getDefault().register(this)
        initUI()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun initUI() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
         navController = navHostFragment.navController
        /*  val navHostFragment =
              supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
          val navController = navHostFragment.navController
          navController.navigate(R.id.signupTeam)*/
        //  findNavController(R.id.signupTeam)
        dataBinding.apply {
            vModel = viewModel
        }
    }

    fun changeTypeOfDevice() {
        if (resources.getBoolean(R.bool.isTab)) {
            System.out.println("phone========tablet")

            supportFragmentManager.beginTransaction().replace(R.id.firstFrame, MenuFragment())
                .commit()
            supportFragmentManager.beginTransaction().replace(R.id.secondFrame, ChatEmptyFragment())
                .commit()

            showTabsLayoutMethod()
            hideContainerMethod()

        } else {
            System.out.println("phone========mobile")
            hideTabsLayoutMethod()
            showContainerMethod()
        }
    }

    override fun onStart() {
        super.onStart()
        activity = WeakReference(this@MainActivity)
    }

    companion object {
        var activity: WeakReference<Activity>? = null
    }

    fun showTabsLayoutMethod() {
        dataBinding.llLayoutsForTabs.visibility = View.VISIBLE
    }

    fun hideTabsLayoutMethod() {
        dataBinding.llLayoutsForTabs.visibility = View.GONE
    }

    fun showContainerMethod() {
        dataBinding.linearNormalLayout.visibility = View.VISIBLE
    }

    fun hideContainerMethod() {
        dataBinding.linearNormalLayout.visibility = View.GONE
    }

    fun showThirdFrame() {
        dataBinding.thirdFrame.visibility = View.VISIBLE
    }

    fun hideThirdFrame() {
        dataBinding.thirdFrame.visibility = View.GONE
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MyMessageEvent?) {
        // Do something
        Log.e("gsegegsgsgs===", System.currentTimeMillis().toString())
        if (event!!.screenName == Constants.MENU_KEY) {
            Handler().postDelayed(object : Runnable {
                override fun run() {
                    changeTypeOfDevice()
                }
            }, 200)

        } else
            if (event.screenName == Constants.CHAT_MESSAGE_KEY) {
                createChatMethod()
            } else
                if (event.screenName == Constants.USER_PROFILE) {
                    showTabsMethod(3, event.screenName)
                } else
                    if (event.screenName == Constants.USER_PROFILE_BACK) {
                        showTabsMethod(2, event.screenName)
                    } else
                        if (event.screenName == Constants.GROUP_PROFILE) {
                            showTabsMethod(3, event.screenName)
                        } else
                            if (event.screenName == Constants.GROUP_PROFILE_BACK) {
                                showTabsMethod(2, event.screenName)
                            }else
                                if(event.screenName==Constants.USER_MANAGEMENT)
                                {
                                    openUserMamangementScreen()
                                }

    }

    fun openUserMamangementScreen()
    {
        var fragment = UserListFragment()
        supportFragmentManager.beginTransaction().replace(R.id.secondFrame, fragment).commit()
    }

    fun createChatMethod() {
        //   ChatMessagesFragment.newInstanceChatMethod("").show(supportFragmentManager," SimpleDialog.TAG")
        var bundle = Bundle()
        bundle.putString("userType", "")
        var fragment = ChatMessagesFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.secondFrame, fragment).commit()
    }

    fun showTabsMethod(tabsValue: Int, screenType: String) {
        if (tabsValue == 3) {
            dataBinding.llLayoutsForTabs.weightSum = 4.2f
            // dataBinding.firstFrame.layoutParams
            var lParamsFirst =
                dataBinding.firstFrame.layoutParams as LinearLayout.LayoutParams
            var lParamsSecond =
                dataBinding.secondFrame.layoutParams as LinearLayout.LayoutParams
            var lParamsThird =
                dataBinding.thirdFrame.layoutParams as LinearLayout.LayoutParams
            lParamsFirst.weight = 1.2f
            lParamsSecond.weight = 1.8f
            lParamsThird.weight = 1.2f
            dataBinding.thirdFrame.visibility = View.VISIBLE
        } else {
            dataBinding.llLayoutsForTabs.weightSum = 3f

            var lParamsFirst =
                dataBinding.firstFrame.layoutParams as LinearLayout.LayoutParams
            var lParamsSecond =
                dataBinding.secondFrame.layoutParams as LinearLayout.LayoutParams
            var lParamsThird =
                dataBinding.thirdFrame.layoutParams as LinearLayout.LayoutParams
            lParamsFirst.weight = 1.2f
            lParamsSecond.weight = 1.8f
            lParamsThird.weight = 0f
            dataBinding.thirdFrame.visibility = View.GONE
        }
        if (screenType.equals(Constants.USER_PROFILE)) {
            var bundle = Bundle()
            bundle.putString("userType", "")
            var fragment = ChatUserInfoFragment()
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.thirdFrame, fragment).commit()
        } else
            if (screenType.equals(Constants.GROUP_PROFILE)) {
                var bundle = Bundle()
                bundle.putString("userType", "")
                var fragment = ChatGroupInfoFragment()
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction().replace(R.id.thirdFrame, fragment)
                    .commit()
            }
    }

        override fun onBackPressed() {
            if (navController.currentDestination?.id == R.id.signInFragment) {
                finishAffinity()
            }
            else
                if(navController.currentDestination!!.id==R.id.menuFragment)
                {
                    finish()
                }
            else {
                super.onBackPressed()
            }
    }
}