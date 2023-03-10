package com.yapi.common

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.yapi.MainActivity
import java.text.SimpleDateFormat
import java.util.*


fun Activity.hideKeyboard() {
    this.currentFocus?.let { view ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
fun Activity.isEmailValid(email: String): String {
     if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
         return ""
     }else{
         return "Email is not valid"
     }
}

fun Activity.showMessage(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

//Convert from milisecond to date format
fun Activity.convertMiliToDateFormat(milisecond: Long): String {
    var formatter = SimpleDateFormat("dd-MM-yyyy hh:mm a")
    var dateString = formatter.format(Date(milisecond))
    return dateString
}

//Convert from Date to Milisecond
fun Activity.convertFromDateToMiliSeconds(date: String): Long {
    val formatter =
        SimpleDateFormat("dd-MM-yyyy hh:mm a") // I assume d-M, you may refer to M-d for month-day instead.
    val date = formatter.parse(date) // You will need try/catch around this
    val millis = date.time
    return millis
}

//For check internet connection
fun Activity.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}

fun getTextSizeValue(text1:Float)
{
    var text=10
    var showText="ssp"
    var newTextSize="_$text$showText"
//  MainActivity.activity.get().resources.getDimension(com.intuit.ssp.R.dimen._10ssp)
}

fun showToastMessage(message: String) {
    Toast.makeText(MainActivity.activity!!.get(), message, Toast.LENGTH_SHORT).show()
}

fun isValidEmail(target: CharSequence?): Boolean {
    return if (TextUtils.isEmpty(target)) {
        false
    } else {
        Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}
