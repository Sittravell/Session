package com.sittravell.session

import android.app.Activity
import android.content.SharedPreferences
import com.google.gson.Gson

class Storage(activity: Activity){
    val writer: SharedPreferences
    companion object{
        const val PRIVATE_MODE = 0
        /* REPLACE: With your application id */
        const val PREF_NAME = "com.example.templates"
    }

    init {
        /* NOTE: 'PRIVATE_MODE' is underlined as error but does not throw any error */
        writer = activity.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    }

    // To get the object by key
    inline fun <reified T> get(key: String): T?{
        return writer.get<T>(key)
    }

    // To set the object with key
    fun set(key: String, data: Any){
        writer.set(key, data)
    }

    // To clear all stored objects
    fun clear(){
        writer.clear()
    }



    /*
        Shared Preference Extensions
     */
    inline fun <reified T> SharedPreferences.get(key: String): T? {
        val json: String? = this.getString(key, "")
        return Gson().fromJson(json, T::class.java)
    }

    fun SharedPreferences.set(key: String, data: Any) {
        val editor = this.edit()
        val json = Gson().toJson(data)
        editor.putString(key, json)
        editor.apply()
    }

    fun SharedPreferences.clear() {
        val editor = this.edit()
        editor.clear()
        editor.apply()
    }
}