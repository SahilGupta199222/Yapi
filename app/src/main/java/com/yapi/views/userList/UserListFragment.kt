package com.yapi.views.userList

import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yapi.MainActivity
import com.yapi.R
import com.yapi.common.checkDeviceType
import com.yapi.databinding.UserListFragmentLayoutBinding

class UserListFragment : Fragment(), UserClickEvent {
    private lateinit var rvAdapter: RVUserListAdapter
    private var dataBinding: UserListFragmentLayoutBinding? = null
    var screenWidth: Int? = 0
    val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = UserListFragmentLayoutBinding.inflate(layoutInflater)
        dataBinding!!.mViewModel = viewModel
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels
        //  viewModel.screenWidth = width
        initUI()

        return dataBinding!!.root
    }

    private fun initUI() {
        var userList = ArrayList<UserDataList>()
        userList.clear()
        userList.add(UserDataList("AA", false))
        userList.add(UserDataList("BB", false))
        userList.add(UserDataList("CC", false))
        userList.add(UserDataList("DD", false))
        userList.add(UserDataList("EE", false))
        userList.add(UserDataList("FF", false))
        userList.add(UserDataList("FF", false))
        userList.add(UserDataList("FF", false))
        userList.add(UserDataList("FF", false))
        userList.add(UserDataList("FF", false))
        userList.add(UserDataList("FF", false))
        userList.add(UserDataList("FF", false))
        rvAdapter = RVUserListAdapter(requireActivity(), userList, this)
        dataBinding!!.rvUserList.layoutManager = LinearLayoutManager(requireActivity())
        dataBinding!!.rvUserList.adapter = rvAdapter

    }

    fun showEditDetailMethod(position: Int, imageview: ImageView) {
        val mView: View = LayoutInflater.from(MainActivity.activity!!.get())
            .inflate(R.layout.menu_popup_options, null, false)
        var newWidth = screenWidth!! / 1.5

        //   val popUp = PopupWindow(mView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, false)
        //val popUp = PopupWindow(mView, newWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)
        val popUp =
            PopupWindow(mView, newWidth.toInt(), LinearLayout.LayoutParams.WRAP_CONTENT, false)
        // popUp.showAtLocation(mView, Gravity.RIGHT,0,0);
        popUp.isTouchable = true
        popUp.isFocusable = true
        popUp.isOutsideTouchable = true
        val btnViewProfile =
            //Solution
            popUp.showAsDropDown(imageview)

        var constraintsProfile = mView.findViewById<ConstraintLayout>(R.id.constraintsProfile)
        var editMemberInfoTV = mView.findViewById<TextView>(R.id.tvProfile)
        var deactivateMemberTV = mView.findViewById<TextView>(R.id.tvmenuSetting)
        var logoutTV = mView.findViewById<TextView>(R.id.tvmenuLogout)


        var ivEditMember = mView.findViewById<ImageView>(R.id.ivProfile_photo)
        var ivDeactiveMember = mView.findViewById<ImageView>(R.id.ivmenuSetting)
        var ivmenulogout = mView.findViewById<ImageView>(R.id.ivmenulogout)

        editMemberInfoTV.text = requireActivity().getString(R.string.edit_member_info_text)
        deactivateMemberTV.text = requireActivity().getString(R.string.deactivate_member_text)
        logoutTV.text = requireActivity().getString(R.string.remove_member_text)
        ivEditMember.setImageResource(R.drawable.edit_message_icon)
        ivDeactiveMember.setImageResource(R.drawable.deactivate_user)
        ivmenulogout.setImageResource(R.drawable.delete_account)

        constraintsProfile.setOnClickListener {
            popUp.dismiss()
                showEditMemberMethod()
        }
        var constraintsSettings =
            mView.findViewById<ConstraintLayout>(R.id.constraintsSettings)
        constraintsSettings.setOnClickListener {
            popUp.dismiss()
            showDeactivateMemberMethod()
        }
        var constraintsLogout = mView.findViewById<ConstraintLayout>(R.id.constraintsLogout)
        constraintsLogout.setOnClickListener {
            popUp.dismiss()
                showRemoveMemberMethod()
        }
    }

    override fun onClick(position: Int, imageview: AppCompatImageView) {
        showEditDetailMethod(position, imageview)
    }

    fun showEditMemberMethod() {

        var dividedValue=0.0
        if(checkDeviceType())
        {
            dividedValue=1.3
        }else
        {
            dividedValue=1.05
        }
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.edit_memeber_info_popup)
        dialog.show()

        var linearEditMember = dialog.findViewById<LinearLayout>(R.id.linearEditMember)
        linearEditMember.layoutParams.width = (screenWidth!!.toDouble() / dividedValue).toInt()
        var ivInnerBack = dialog.findViewById<ImageView>(R.id.ivInnerBack)
        var btnCancelTemplate = dialog.findViewById<AppCompatButton>(R.id.btnCancelTemplate)
        var ivOutsideCloseGroup = dialog.findViewById<ImageView>(R.id.ivOutsideCloseGroup)

        if(checkDeviceType())
        {
            ivOutsideCloseGroup.visibility=View.VISIBLE
            ivInnerBack.visibility=View.GONE
        }else
        {
            ivOutsideCloseGroup.visibility=View.GONE
            ivInnerBack.visibility=View.VISIBLE
        }

        ivInnerBack.setOnClickListener {
            dialog.dismiss()
        }
        ivOutsideCloseGroup.setOnClickListener {
            dialog.dismiss()
        }

        btnCancelTemplate.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun showRemoveMemberMethod() {

        var dividedValue=0.0
        if(checkDeviceType())
        {
            dividedValue=1.3
        }else
        {
            dividedValue=1.05
        }

        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.remove_member_layout)
        dialog.show()
        var ivInnerBack = dialog.findViewById<ImageView>(R.id.ivInnerBack)
        var ivOutsideCloseGroup = dialog.findViewById<ImageView>(R.id.ivOutsideCloseGroup)

        var linearRemoveMember = dialog.findViewById<LinearLayout>(R.id.linearRemoveMember)
        linearRemoveMember.layoutParams.width = (screenWidth!!.toDouble() / dividedValue).toInt()

        if (checkDeviceType()) {
            ivOutsideCloseGroup.visibility = View.VISIBLE
            ivInnerBack.visibility = View.GONE
        } else {
            ivOutsideCloseGroup.visibility = View.GONE
            ivInnerBack.visibility = View.VISIBLE
        }
        ivOutsideCloseGroup.setOnClickListener {
            dialog.dismiss()
        }

        ivInnerBack.setOnClickListener {
            dialog.dismiss()
        }
    }


    fun showDeactivateMemberMethod() {

        var dividedValue=0.0
        if(checkDeviceType())
        {
            dividedValue=1.3
        }else
        {
            dividedValue=1.05
        }

        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.remove_member_layout)
        dialog.show()
        var ivInnerBack = dialog.findViewById<ImageView>(R.id.ivInnerBack)
        var ivOutsideCloseGroup = dialog.findViewById<ImageView>(R.id.ivOutsideCloseGroup)
        var tvTitle = dialog.findViewById<AppCompatTextView>(R.id.tvTitle)
        tvTitle.setText(requireActivity().getString(R.string.are_you_sure_deactivate_user))

        var tvDescription = dialog.findViewById<AppCompatTextView>(R.id.tvDescription)
        tvDescription.setText(requireActivity().getString(R.string.if_deactivate_account_receive))

        var btnTemplateSave = dialog.findViewById<AppCompatButton>(R.id.btnTemplateSave)
        btnTemplateSave.setText(requireActivity().getString(R.string.deactivate_user_text))

        btnTemplateSave.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.darkBlueBtn)));

        var linearRemoveMember = dialog.findViewById<LinearLayout>(R.id.linearRemoveMember)
        linearRemoveMember.layoutParams.width = (screenWidth!!.toDouble() /dividedValue).toInt()

        if (checkDeviceType()) {
            ivOutsideCloseGroup.visibility = View.VISIBLE
            ivInnerBack.visibility = View.GONE
        } else {
            ivOutsideCloseGroup.visibility = View.GONE
            ivInnerBack.visibility = View.VISIBLE
        }
        ivOutsideCloseGroup.setOnClickListener {
            dialog.dismiss()
        }

        ivInnerBack.setOnClickListener {
            dialog.dismiss()
        }
    }


    fun showAddMemberMethod() {

        var dividedValue=0.0
        if(checkDeviceType())
        {
            dividedValue=1.3
        }else
        {
            dividedValue=1.05
        }
        var dialog = Dialog(MainActivity.activity!!.get()!!)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.edit_memeber_info_popup)
        dialog.show()

        var linearEditMember = dialog.findViewById<LinearLayout>(R.id.linearEditMember)
        linearEditMember.layoutParams.width = (screenWidth!!.toDouble() / dividedValue).toInt()
        var ivInnerBack = dialog.findViewById<ImageView>(R.id.ivInnerBack)
        var btnCancelTemplate = dialog.findViewById<AppCompatButton>(R.id.btnCancelTemplate)
        var ivOutsideCloseGroup = dialog.findViewById<ImageView>(R.id.ivOutsideCloseGroup)

        if(checkDeviceType())
        {
            ivOutsideCloseGroup.visibility=View.VISIBLE
            ivInnerBack.visibility=View.GONE
        }else
        {
            ivOutsideCloseGroup.visibility=View.GONE
            ivInnerBack.visibility=View.VISIBLE
        }

        ivInnerBack.setOnClickListener {
            dialog.dismiss()
        }
        ivOutsideCloseGroup.setOnClickListener {
            dialog.dismiss()
        }

        btnCancelTemplate.setOnClickListener {
            dialog.dismiss()
        }
    }

}