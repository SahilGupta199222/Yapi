package com.yapi.pref

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceFile @Inject constructor(
     val editor: SharedPreferences.Editor,
     val pref: SharedPreferences,
) {
    fun saveStringValue(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun fetchStringValue(key:String):String
    {
        return pref.getString(key,"").toString()
    }

    fun saveBooleanValue(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun fetchBooleanValue(key:String):Boolean
    {
        return pref.getBoolean(key,false)
    }

    fun saveIntValue(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun fetchIntValue(key:String):Int
    {
        return pref.getInt(key,0).toInt()
    }

    fun saveLongValue(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    fun fetchLongValue(key:String):Long
    {
        return pref.getLong(key,0)
    }

    fun saveFloatValue(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    fun fetchFloatValue(key:String):Float
    {
        return pref.getFloat(key,0f)
    }
}